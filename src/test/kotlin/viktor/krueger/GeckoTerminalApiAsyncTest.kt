package viktor.krueger

import kotlinx.coroutines.test.runTest
import viktor.krueger.api.GeckoTerminalApiAsync
import java.time.Instant
import kotlin.test.Test
import kotlin.test.assertNotNull


class GeckoTerminalApiAsyncTest {
    private val api = GeckoTerminalApiAsync()

    @Test
    fun testGetSimpleTokenPrices() = runTest {
        val network = "eth"
        val addresses = "0xc02aaa39b223fe8d0a0e5c4f27ead9083c756cc2,0xa0b86991c6218b36c1d19d4a2e9eb0ce3606eb48"

        val result = api.getSimpleTokenPrices(network, addresses)

        assertNotNull(result)
        println(result)
    }

    @Test
    fun testGetNetworks() = runTest {
        val page = 1

        val result = api.getNetworks(page)

        assertNotNull(result)
        println(result)
    }

    @Test
    fun testGetDexes() = runTest {
        val network = "eth"
        val page = 1

        val result = api.getDexes(network, page)

        assertNotNull(result)
        println(result)
    }

    @Test
    fun testGetTrendingPools() = runTest {
        val include = "base_token,quote_token"
        val page = 1

        val result = api.getTrendingPools(include, page)

        assertNotNull(result)
        println(result)
    }

    @Test
    fun testGetTrendingPoolsOnNetwork() = runTest {
        val network = "eth"
        val include = "base_token,quote_token"
        val page = 1

        val result = api.getTrendingPoolsOnNetwork(network, include, page)

        assertNotNull(result)
        println(result)
    }

    @Test
    fun testGetPoolOnNetwork() = runTest {
        val network = "eth"
        val address = "0x60594a405d53811d3bc4766596efd80fd545a270"
        val include = "base_token,quote_token"

        val result = api.getPoolOnNetwork(network, address, include)

        assertNotNull(result)
        println(result)
    }

    @Test
    fun testGetMultiplePoolsOnNetwork() = runTest {
        val network = "eth"
        val addresses = "0x60594a405d53811d3bc4766596efd80fd545a270,0x88e6a0c2ddd26feeb64f039a2c41296fcb3f5640"
        val include = "base_token,quote_token"

        val result = api.getMultiplePoolsOnNetwork(network, addresses, include)

        assertNotNull(result)
        println(result)
    }

    @Test
    fun testGetTopPoolsOnNetwork() = runTest {
        val network = "eth"
        val include = "base_token,quote_token"
        val page = 1

        val result = api.getTopPoolsOnNetwork(network, include, page)

        assertNotNull(result)
        println(result)
    }

    @Test
    fun testGetTopPoolsOnDex() = runTest {
        val network = "eth"
        val dex = "sushiswap"
        val include = "base_token,quote_token"
        val page = 1

        val result = api.getTopPoolsOnDex(network, dex, include, page)

        assertNotNull(result)
        println(result)
    }

    @Test
    fun testGetLatestPoolsOnNetwork() = runTest {
        val network = "eth"
        val include = "base_token,quote_token"
        val page = 1

        val result = api.getLatestPoolsOnNetwork(network, include, page)

        assertNotNull(result)
        println(result)
    }

    @Test
    fun testGetLatestPools() = runTest {
        val include = "base_token,quote_token"
        val page = 1

        val result = api.getLatestPools(include, page)

        assertNotNull(result)
        println(result)
    }

    @Test
    fun testSearchPools() = runTest {
        val query = "ETH"
        val network = "eth"
        val include = "base_token,quote_token"
        val page = 1

        val result = api.searchPools(query, network, include, page)

        assertNotNull(result)
        println(result)
    }

    @Test
    fun testGetTopPoolsForToken() = runTest {
        val network = "eth"
        val tokenAddress = "0xa0b86991c6218b36c1d19d4a2e9eb0ce3606eb48"
        val include = "base_token,quote_token"
        val page = 1

        val result = api.getTopPoolsForToken(network, tokenAddress, include, page)

        assertNotNull(result)
        println(result)
    }

    @Test
    fun testGetTokenOnNetwork() = runTest {
        val network = "eth"
        val address = "0xc02aaa39b223fe8d0a0e5c4f27ead9083c756cc2"
        val include = "top_pools"

        val result = api.getTokenOnNetwork(network, address, include)

        assertNotNull(result)
        println(result)
    }

    @Test
    fun testGetMultipleTokensOnNetwork() = runTest {
        val network = "eth"
        val addresses = "0xc02aaa39b223fe8d0a0e5c4f27ead9083c756cc2,0xa0b86991c6218b36c1d19d4a2e9eb0ce3606eb48"
        val include = "top_pools"

        val result = api.getMultipleTokensOnNetwork(network, addresses, include)

        assertNotNull(result)
        println(result)
    }

    @Test
    fun getTokenInfoOnNetwork() = runTest {
        val network = "eth"
        val address = "0xa0b86991c6218b36c1d19d4a2e9eb0ce3606eb48"

        val result = api.getTokenInfoOnNetwork(network, address)

        assertNotNull(result)
        println(result)
    }

    @Test
    fun testGetRecentlyUpdatedTokensInfo() = runTest {
        val include = "network"

        val result = api.getRecentlyUpdatedTokensInfo(include)

        assertNotNull(result)
        println(result)
    }

    @Test
    fun testGetOhlcvDataOfPool() = runTest {
        val network = "eth"
        val poolAddress = "0x60594a405d53811d3bc4766596efd80fd545a270"
        val timeframe = "day"
        val aggregate = "1"
        val beforeTimestamp = Instant.now().toEpochMilli().toString()
        val limit = "100"
        val currency = "usd"
        val token = "base"

        val result = api.getOhlcvDataOfPool(network, poolAddress, timeframe, aggregate, beforeTimestamp, limit, currency, token)

        assertNotNull(result)
        println(result)
    }

    @Test
    fun testGetTradesFromPool() = runTest {
        val network = "eth"
        val poolAddress = "0x60594a405d53811d3bc4766596efd80fd545a270"
        val tradeVolumeInUsdGreaterThan = 30000.0

        val result = api.getTradesFromPool(network, poolAddress, tradeVolumeInUsdGreaterThan)

        assertNotNull(result)
        println(result)
    }

}