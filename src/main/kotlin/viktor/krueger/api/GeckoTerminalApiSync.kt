package viktor.krueger.api

import kotlinx.serialization.json.Json
import viktor.krueger.model.*
import java.net.HttpURLConnection
import java.net.URL


class GeckoTerminalApiSync(
    private val client: (URL) -> HttpURLConnection = { it.openConnection() as HttpURLConnection },
    private val json: Json = Json { ignoreUnknownKeys = true },
    private val baseUrl: String = "https://api.geckoterminal.com/api/v2",
    private val headerName: String = "Accept",
    private val headerValue: String = "application/json;version=20230302"
) {

    fun getSimpleTokenPrices(network: String, addresses: String): SimpleTokenPriceResponse {
        return getResponse("$baseUrl/simple/networks/$network/token_price/$addresses")
    }

    fun getNetworks(page: Int? = 1): NetworkResponse {
        val pageParam = page?.let { "?page=$it" } ?: ""
        return getResponse("$baseUrl/networks$pageParam")
    }

    fun getDexes(network: String, page: Int? = 1): DexResponse {
        val pageParam = page?.let { "?page=$it" } ?: ""
        return getResponse("$baseUrl/networks/$network/dexes$pageParam")
    }

    fun getTrendingPools(include: String? = null, page: Int? = 1): PoolResponse {
        val includeParam = include?.let { "&include=$it" } ?: ""
        val pageParam = page?.let { "&page=$it" } ?: ""
        return getResponse("$baseUrl/networks/trending_pools?$pageParam$includeParam")
    }

    fun getTrendingPoolsOnNetwork(network: String, include: String? = null, page: Int? = 1): PoolResponse {
        val includeParam = include?.let { "&include=$it" } ?: ""
        val pageParam = page?.let { "&page=$it" } ?: ""
        return getResponse("$baseUrl/networks/$network/trending_pools?$pageParam$includeParam")
    }

    fun getPoolOnNetwork(network: String, address: String, include: String? = null): PoolDetailResponse {
        val includeParam = include?.let { "?include=$it" } ?: ""
        return getResponse("$baseUrl/networks/$network/pools/$address$includeParam")
    }

    fun getMultiplePoolsOnNetwork(network: String, addresses: String, include: String? = null): PoolResponse {
        val includeParam = include?.let { "?include=$it" } ?: ""
        return getResponse("$baseUrl/networks/$network/pools/multi/$addresses$includeParam")
    }

    fun getTopPoolsOnNetwork(network: String, include: String? = null, page: Int? = 1): PoolResponse {
        val includeParam = include?.let { "&include=$it" } ?: ""
        val pageParam = page?.let { "&page=$it" } ?: ""
        return getResponse("$baseUrl/networks/$network/pools?$pageParam$includeParam")
    }

    fun getTopPoolsOnDex(network: String, dex: String, include: String? = null, page: Int? = 1): PoolResponse {
        val includeParam = include?.let { "&include=$it" } ?: ""
        val pageParam = page?.let { "&page=$it" } ?: ""
        return getResponse("$baseUrl/networks/$network/dexes/$dex/pools?$pageParam$includeParam")
    }

    fun getLatestPoolsOnNetwork(network: String, include: String? = null, page: Int? = 1): PoolResponse {
        val includeParam = include?.let { "&include=$it" } ?: ""
        val pageParam = page?.let { "&page=$it" } ?: ""
        return getResponse("$baseUrl/networks/$network/new_pools?$pageParam$includeParam")
    }

    fun getLatestPools(include: String? = null, page: Int? = 1): PoolResponse {
        val includeParam = include?.let { "&include=$it" } ?: ""
        val pageParam = page?.let { "&page=$it" } ?: ""
        return getResponse("$baseUrl/networks/new_pools?$pageParam$includeParam")
    }

    fun searchPools(query: String, network: String? = null, include: String? = null, page: Int? = 1): PoolResponse {
        val networkParam = network?.let { "&network=$it" } ?: ""
        val includeParam = include?.let { "&include=$it" } ?: ""
        val pageParam = page?.let { "&page=$it" } ?: ""
        return getResponse("$baseUrl/search/pools?query=$query?$pageParam$networkParam$includeParam")
    }

    fun getTopPoolsForToken(
        network: String,
        tokenAddress: String,
        include: String? = null,
        page: Int? = 1
    ): PoolResponse {
        val includeParam = include?.let { "&include=$it" } ?: ""
        val pageParam = page?.let { "&page=$it" } ?: ""
        return getResponse("$baseUrl/networks/$network/tokens/$tokenAddress/pools?$pageParam$includeParam")
    }

    fun getTokenOnNetwork(network: String, address: String, include: String? = null): TokenResponse {
        val includeParam = include?.let { "?include=$it" } ?: ""
        return getResponse("$baseUrl/networks/$network/tokens/$address$includeParam")
    }

    fun getMultipleTokensOnNetwork(network: String, addresses: String, include: String? = null): TokensResponse {
        val includeParam = include?.let { "?include=$it" } ?: ""
        return getResponse("$baseUrl/networks/$network/tokens/multi/$addresses$includeParam")
    }

    fun getTokenInfoOnNetwork(network: String, address: String): TokenInfoResponse {
        return getResponse("$baseUrl/networks/$network/tokens/$address/info")
    }

    fun getRecentlyUpdatedTokensInfo(include: String? = null): TokensInfoResponse {
        val includeParam = include?.let { "?include=$it" } ?: ""
        return getResponse("$baseUrl/tokens/info_recently_updated$includeParam")
    }

    fun getOhlcvDataOfPool(
        network: String,
        poolAddress: String,
        timeframe: String,
        aggregate: String? = null,
        beforeTimestamp: String? = null,
        limit: String? = null,
        currency: String? = null,
        token: String? = null
    ): OhlcvResponse {
        val aggregateParam = aggregate?.let { "&aggregate=$it" } ?: ""
        val beforeTimestampParam = beforeTimestamp?.let { "&before_timestamp=$it" } ?: ""
        val limitParam = limit?.let { "&limit=$it" } ?: ""
        val currencyParam = currency?.let { "&currency=$it" } ?: ""
        val tokenParam = token?.let { "&token=$it" } ?: ""
        return getResponse("$baseUrl/networks/$network/pools/$poolAddress/ohlcv/$timeframe?$aggregateParam$beforeTimestampParam$limitParam$currencyParam$tokenParam")
    }

    fun getTradesFromPool(
        network: String,
        poolAddress: String,
        tradeVolumeInUsdGreaterThan: Double? = null
    ): TradesResponse {
        val tradeVolumeParam = tradeVolumeInUsdGreaterThan?.let { "?trade_volume_in_usd_greater_than=$it" } ?: ""
        return getResponse("$baseUrl/networks/$network/pools/$poolAddress/trades$tradeVolumeParam")
    }

    private inline fun <reified T> getResponse(url: String): T {
        val connection = client(URL(url))
        return try {
            with(connection) {
                requestMethod = "GET"
                setRequestProperty(headerName, headerValue)
                val responseText = inputStream.bufferedReader().use { it.readText() }
                try {
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
        } finally {
            connection.disconnect()
        }
    }

}