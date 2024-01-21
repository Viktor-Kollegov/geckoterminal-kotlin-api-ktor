package viktor.krueger.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TradesResponse(
    val data: List<Trade>
)

@Serializable
data class Trade(
    val id: String,
    val type: String,
    val attributes: Attributes
) {
    @Serializable
    data class Attributes(
        @SerialName("block_number")
        val blockNumber: Int,
        @SerialName("block_timestamp")
        val blockTimestamp: String,
        @SerialName("tx_hash")
        val txHash: String,
        @SerialName("tx_from_address")
        val txFromAddress: String,
        @SerialName("from_token_amount")
        val fromTokenAmount: String,
        @SerialName("to_token_amount")
        val toTokenAmount: String,
        @SerialName("price_from_in_currency_token")
        val priceFromInCurrencyToken: String,
        @SerialName("price_to_in_currency_token")
        val priceToInCurrencyToken: String,
        @SerialName("price_from_in_usd")
        val priceFromInUsd: String,
        @SerialName("price_to_in_usd")
        val priceToInUsd: String,
        val kind: String,
        @SerialName("volume_in_usd")
        val volumeInUsd: String,
        @SerialName("from_token_address")
        val fromTokenAddress: String,
        @SerialName("to_token_address")
        val toTokenAddress: String
    )
}