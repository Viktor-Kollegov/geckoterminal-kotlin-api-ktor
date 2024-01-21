package viktor.krueger.model

import kotlinx.serialization.Serializable

@Serializable
data class DexResponse(
    val data: List<Dex>
) {
    @Serializable
    data class Dex(
        val id: String,
        val type: String,
        val attributes: DexAttributes
    ) {
        @Serializable
        data class DexAttributes(
            val name: String
        )
    }
}