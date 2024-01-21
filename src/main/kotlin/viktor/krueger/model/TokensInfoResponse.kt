package viktor.krueger.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokensInfoResponse(
    val data: List<TokenInfo>
)

@Serializable
data class TokenInfoResponse(
    val data: TokenInfo
)

@Serializable
data class TokenInfo(
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
        @SerialName("coingecko_coin_id")
        val coingeckoCoinId: String? = null,
        @SerialName("image_url")
        val imageUrl: String? = null,
        val websites: List<String>? = null,
        val description: String? = null,
        @SerialName("discord_url")
        val discordUrl: String? = null,
        @SerialName("telegram_handle")
        val telegramHandle: String? = null,
        @SerialName("twitter_handle")
        val twitterHandle: String? = null,
        @SerialName("gt_score")
        val gtScore: Float? = null,
        @SerialName("metadata_updated_at")
        val metadataUpdatedAt: String?  = null
    )

    @Serializable
    data class Relationships(
        val network: Data? = null
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