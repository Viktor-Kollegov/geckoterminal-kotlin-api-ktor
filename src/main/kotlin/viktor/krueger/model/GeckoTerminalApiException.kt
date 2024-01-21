package viktor.krueger.model

sealed class GeckoTerminalApiException(message: String) : Exception(message) {
    class PoolNotFoundException(message: String) : GeckoTerminalApiException(message)
    class UnsupportedEndpointException(message: String) : GeckoTerminalApiException(message)
    class NetworkNotFoundException(message: String) : GeckoTerminalApiException(message)
    class ExceededMaxAddressesException(message: String) : GeckoTerminalApiException(message)
    class TokenNotFoundException(message: String) : GeckoTerminalApiException(message)
    class DexNotFoundException(message: String) : GeckoTerminalApiException(message)
}