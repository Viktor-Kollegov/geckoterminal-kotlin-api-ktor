package viktor.krueger.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SimpleTokenPriceResponse(
    val data: SimpleTokenPrice
) {
    @Serializable
    data class SimpleTokenPrice(
        val id: String,
        val type: String,
        val attributes: Attributes
    ) {
        @Serializable
        data class Attributes(
            @SerialName("token_prices")
            val tokenPrices: Map<String, String>
        )
    }
}