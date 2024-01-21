package viktor.krueger.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PoolResponse(
    val data: List<Pool>
)

@Serializable
data class PoolDetailResponse(
    val data: Pool
)

@Serializable
data class Pool(
    val id: String,
    val type: String,
    val attributes: Attributes,
    val relationships: Relationships? = null
) {
    @Serializable
    data class Attributes(
        val name: String,
        val address: String,
        @SerialName("base_token_price_usd")
        val baseTokenPriceUsd: String? = null,
        @SerialName("quote_token_price_usd")
        val quoteTokenPriceUsd: String? = null,
        @SerialName("base_token_price_native_currency")
        val baseTokenPriceNativeCurrency: String? = null,
        @SerialName("quote_token_price_native_currency")
        val quoteTokenPriceNativeCurrency: String? = null,
        @SerialName("base_token_price_quote_token")
        val baseTokenPriceQuoteToken: String? = null,
        @SerialName("quote_token_price_base_token")
        val quoteTokenPriceBaseToken: String? = null,
        @SerialName("pool_created_at")
        val poolCreatedAt: String? = null,
        @SerialName("reserve_in_usd")
        val reserveInUsd: String? = null,
        @SerialName("fdv_usd")
        val fdvUsd: String? = null,
        @SerialName("market_cap_usd")
        val marketCapUsd: String? = null,
        @SerialName("price_change_percentage")
        val priceChangePercentage: Map<String, String?>? = null,
        val transactions: Map<String, Map<String, Int?>>? = null,
        @SerialName("volume_usd")
        val volumeUsd: Map<String, String?>? = null
    )

    @Serializable
    data class Relationships(
        @SerialName("base_token")
        val baseToken: Data? = null,
        @SerialName("quote_token")
        val quoteToken: Data? = null,
        val network: Data? = null,
        val dex: Data? = null
    ) {
        @Serializable
        data class Data(
            val data: Token? = null
        ) {
            @Serializable
            data class Token(
                val id: String,
                val type: String
            )
        }
    }
}