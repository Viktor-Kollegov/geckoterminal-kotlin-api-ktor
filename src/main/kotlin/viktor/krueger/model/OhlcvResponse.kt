package viktor.krueger.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
 *  example: List [ List [ 1676160000, 1538.82295287143, 1538.82295287143, 1538.82295287143, 1538.82295287143, 32365895.4425411 ] ]
 *  Each array in the list contains 6 numbers representing unix timestamp, open, high, low, close and volume in USD.
 */
@Serializable
data class OhlcvResponse(
    val data: OhlcvList
)

@Serializable
data class OhlcvList(
    val id: String,
    val type: String,
    val attributes: Attributes
) {
    @Serializable
    data class Attributes(
        @SerialName("ohlcv_list")
        val ohlcvList: List<List<Double>>
    )
}