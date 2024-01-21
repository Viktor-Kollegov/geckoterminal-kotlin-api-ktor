package viktor.krueger.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokensResponse(
    val data: List<Token>
)

@Serializable
data class TokenResponse(
    val data: Token
)

@Serializable
data class Token(
    val id: String,
    val type: String,
    val attributes: Attributes,
    val relationships: Relationships? = null
) {
    @Serializable
    data class Attributes(
        val name: String,
        val address: String,
        val symbol: String,
        val decimals: Int,
        @SerialName("total_supply")
        val totalSupply: String? = null,
        @SerialName("coingecko_coin_id")
        val coingeckoCoinId: String? = null,
        @SerialName("price_usd")
        val priceUsd: String? = null,
        @SerialName("fdv_usd")
        val fdvUsd: String? = null,
        @SerialName("total_reserve_in_usd")
        val totalReserveInUsd: String? = null,
        @SerialName("volume_usd")
        val volumeUsd: VolumeUsd? = null,
        @SerialName("market_cap_usd")
        val marketCapUsd: String? = null
    )

    @Serializable
    data class VolumeUsd(
        @SerialName("h24")
        val h24: String
    )

    @Serializable
    data class Relationships(
        @SerialName("top_pools")
        val topPools: TopPools
    ) {
        @Serializable
        data class TopPools(
            val data: List<Data>
        ) {
            @Serializable
            data class Data(
                val id: String,
                val type: String
            )
        }
    }
}