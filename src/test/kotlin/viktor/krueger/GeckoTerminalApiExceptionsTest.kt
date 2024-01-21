package viktor.krueger

import viktor.krueger.model.GeckoTerminalApiException
import viktor.krueger.api.GeckoTerminalApiSync
import kotlin.test.assertEquals
import kotlin.test.fail


class GeckoTerminalApiExceptionsTest {
    private val api = GeckoTerminalApiSync()

//    @Test
    fun testGetPoolOnNetworkWithInvalidAddress() {
        val network = "eth"
        val address = "invalid_address"
        val include = "base_token,quote_token"

        try {
            val result = api.getPoolOnNetwork(network, address, include)
            fail("Expected an GeckoTerminalApiException.PoolNotFoundException to be thrown")
        } catch (e: GeckoTerminalApiException.PoolNotFoundException) {
            assertEquals("Specified pool not found", e.message)
        }
    }

//    @Test
    fun testGetSimpleTokenPricesWithInvalidNetwork() {
        val network = "invalid_network"
        val addresses = "0xc02aaa39b223fe8d0a0e5c4f27ead9083c756cc2,0xa0b86991c6218b36c1d19d4a2e9eb0ce3606eb48"

        try {
            val result = api.getSimpleTokenPrices(network, addresses)
            fail("Expected an GeckoTerminalApiException.NetworkNotFoundException to be thrown")
        } catch (e: GeckoTerminalApiException.NetworkNotFoundException) {
            assertEquals("Specified network not found", e.message)
        }
    }

//    @Test
    fun testGetPoolOnNetworkWithInvalidNetwork() {
        val network = "invalid_network"
        val address = "0x60594a405d53811d3bc4766596efd80fd545a270"
        val include = "base_token,quote_token"

        try {
            val result = api.getPoolOnNetwork(network, address, include)
            fail("Expected an GeckoTerminalApiException.NetworkNotFoundException to be thrown")
        } catch (e: GeckoTerminalApiException.NetworkNotFoundException) {
            assertEquals("Specified network not found", e.message)
        }
    }

//    @Test
    fun testGetSimpleTokenPricesWithTooManyAddresses() {
        val network = "eth"
        val addresses = "0xc02aaa39b223fe8d0a0e5c4f27ead9083c756cc2,".repeat(1000) // Too many addresses

        try {
            val result = api.getSimpleTokenPrices(network, addresses)
            fail("Expected an GeckoTerminalApiException.ExceededMaxAddressesException to be thrown")
        } catch (e: GeckoTerminalApiException.ExceededMaxAddressesException) {
            assertEquals("Exceeded maximum number of addresses", e.message)
        }
    }

//    @Test
    fun testGetPoolOnNetworkWithInvalidToken() {
        val network = "eth"
        val address = "invalid_token_address"
        val include = "base_token,quote_token"

        try {
            val result = api.getPoolOnNetwork(network, address, include)
            fail("Expected an GeckoTerminalApiException.TokenNotFoundException to be thrown")
        } catch (e: GeckoTerminalApiException.TokenNotFoundException) {
            assertEquals("Token for specified address not found", e.message)
        }
    }

//    @Test
    fun testGetDexesWithInvalidNetwork() {
        val network = "invalid_network"
        val page = 1

        try {
            val result = api.getDexes(network, page)
            fail("Expected an GeckoTerminalApiException.DexNotFoundException to be thrown")
        } catch (e: GeckoTerminalApiException.DexNotFoundException) {
            assertEquals("Specified dex not found", e.message)
        }
    }

}