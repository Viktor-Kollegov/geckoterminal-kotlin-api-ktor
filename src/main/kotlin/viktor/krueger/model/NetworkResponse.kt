package viktor.krueger.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkResponse(
    val data: List<Network>
) {
    @Serializable
    data class Network(
        val id: String,
        val type: String,
        val attributes: NetworkAttributes
    ) {
        @Serializable
        data class NetworkAttributes(
            val name: String
        )
    }
}