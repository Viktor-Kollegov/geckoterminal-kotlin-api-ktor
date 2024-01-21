package viktor.krueger.api

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import viktor.krueger.model.*


class GeckoTerminalApiAsync(
    private val client: HttpClient = HttpClient(),
    private val json: Json = Json { ignoreUnknownKeys = true },
    private val baseUrl: String = "https://api.geckoterminal.com/api/v2",
    private val headerName: String = HttpHeaders.Accept,
    private val headerValue: String = "application/json;version=20230302"
) {

    suspend fun getSimpleTokenPrices(network: String, addresses: String): SimpleTokenPriceResponse {
        return getResponse("$baseUrl/simple/networks/$network/token_price/$addresses")
    }

    suspend fun getNetworks(page: Int = 1): NetworkResponse {
        return getResponse("$baseUrl/networks", mapOf("page" to page.toString()))
    }

    suspend fun getDexes(network: String, page: Int = 1): DexResponse {
        return getResponse("$baseUrl/networks/$network/dexes", mapOf("page" to page.toString()))
    }

    suspend fun getTrendingPools(include: String? = null, page: Int = 1): PoolResponse {
        return getResponse("$baseUrl/networks/trending_pools", mapOf("page" to page.toString(), "include" to include))
    }

    suspend fun getTrendingPoolsOnNetwork(network: String, include: String? = null, page: Int = 1): PoolResponse {
        return getResponse(
            "$baseUrl/networks/$network/trending_pools",
            mapOf("page" to page.toString(), "include" to include)
        )
    }

    suspend fun getPoolOnNetwork(network: String, address: String, include: String? = null): PoolDetailResponse {
        return getResponse("$baseUrl/networks/$network/pools/$address", mapOf("include" to include))
    }

    suspend fun getMultiplePoolsOnNetwork(network: String, addresses: String, include: String? = null): PoolResponse {
        return getResponse("$baseUrl/networks/$network/pools/multi/$addresses", mapOf("include" to include))
    }

    suspend fun getTopPoolsOnNetwork(network: String, include: String? = null, page: Int = 1): PoolResponse {
        return getResponse("$baseUrl/networks/$network/pools", mapOf("page" to page.toString(), "include" to include))
    }

    suspend fun getTopPoolsOnDex(network: String, dex: String, include: String? = null, page: Int = 1): PoolResponse {
        return getResponse(
            "$baseUrl/networks/$network/dexes/$dex/pools",
            mapOf("page" to page.toString(), "include" to include)
        )
    }

    suspend fun getLatestPoolsOnNetwork(network: String, include: String? = null, page: Int = 1): PoolResponse {
        return getResponse(
            "$baseUrl/networks/$network/new_pools",
            mapOf("page" to page.toString(), "include" to include)
        )
    }

    suspend fun getLatestPools(include: String? = null, page: Int = 1): PoolResponse {
        return getResponse("$baseUrl/networks/new_pools", mapOf("page" to page.toString(), "include" to include))
    }

    suspend fun searchPools(
        query: String,
        network: String? = null,
        include: String? = null,
        page: Int = 1
    ): PoolResponse {
        return getResponse(
            "$baseUrl/search/pools",
            mapOf("query" to query, "page" to page.toString(), "network" to network, "include" to include)
        )
    }

    suspend fun getTopPoolsForToken(
        network: String,
        tokenAddress: String,
        include: String? = null,
        page: Int = 1
    ): PoolResponse {
        return getResponse(
            "$baseUrl/networks/$network/tokens/$tokenAddress/pools",
            mapOf("page" to page.toString(), "include" to include)
        )
    }

    suspend fun getTokenOnNetwork(network: String, address: String, include: String? = null): TokenResponse {
        return getResponse("$baseUrl/networks/$network/tokens/$address", mapOf("include" to include))
    }

    suspend fun getMultipleTokensOnNetwork(
        network: String,
        addresses: String,
        include: String? = null
    ): TokensResponse {
        return getResponse("$baseUrl/networks/$network/tokens/multi/$addresses", mapOf("include" to include))
    }

    suspend fun getTokenInfoOnNetwork(network: String, address: String): TokenInfoResponse {
        return getResponse("$baseUrl/networks/$network/tokens/$address/info")
    }

    suspend fun getRecentlyUpdatedTokensInfo(include: String? = null): TokensInfoResponse {
        return getResponse("$baseUrl/tokens/info_recently_updated", mapOf("include" to include))
    }

    suspend fun getOhlcvDataOfPool(
        network: String,
        poolAddress: String,
        timeframe: String,
        aggregate: String? = null,
        beforeTimestamp: String? = null,
        limit: String? = null,
        currency: String? = null,
        token: String? = null
    ): OhlcvResponse {
        return getResponse(
            "$baseUrl/networks/$network/pools/$poolAddress/ohlcv/$timeframe",
            mapOf(
                "aggregate" to aggregate,
                "before_timestamp" to beforeTimestamp,
                "limit" to limit,
                "currency" to currency,
                "token" to token
            )
        )
    }

    suspend fun getTradesFromPool(
        network: String,
        poolAddress: String,
        tradeVolumeInUsdGreaterThan: Double? = null
    ): TradesResponse {
        return getResponse(
            "$baseUrl/networks/$network/pools/$poolAddress/trades",
            mapOf("trade_volume_in_usd_greater_than" to tradeVolumeInUsdGreaterThan?.toString())
        )
    }

    private suspend inline fun <reified T> getResponse(url: String, parameters: Map<String, String?> = emptyMap()): T {
        val response: HttpResponse = client.get(url) {
            headers {
                append(headerName, headerValue)
            }
            parameters.forEach { (key, value) ->
                value?.let { parameter(key, it) }
            }
        }

        val responseText = response.bodyAsText()

        return try {
            json.decodeFromString<T>(responseText)
        } catch (e: Exception) {
            if (e is kotlinx.serialization.SerializationException && e.message?.contains("errors") == true) {
                val errorResponse = json.decodeFromString<ErrorsResponse>(responseText)
                val error = errorResponse.errors.first()
                throw when (error.status) {
                    "404" -> when (error.title) {
                        "Specified pool not found" -> GeckoTerminalApiException.PoolNotFoundException(error.title)
                        "Specified network not found" -> GeckoTerminalApiException.NetworkNotFoundException(error.title)
                        "Token for specified address not found" -> GeckoTerminalApiException.TokenNotFoundException(error.title)
                        "Specified dex not found" -> GeckoTerminalApiException.DexNotFoundException(error.title)
                        else -> Exception("Unknown error: ${error.title}")
                    }
                    "422" -> GeckoTerminalApiException.UnsupportedEndpointException(error.title)
                    "400" -> GeckoTerminalApiException.ExceededMaxAddressesException(error.title)
                    else -> Exception("Unknown error status: ${error.status}")
                }
            } else {
                throw e
            }
        }
    }

}