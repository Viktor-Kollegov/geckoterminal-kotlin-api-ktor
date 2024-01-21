package viktor.krueger.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorsResponse(
    val errors: List<Error>
) {
    @Serializable
    data class Error(
        val status: String,
        val title: String
    )
}