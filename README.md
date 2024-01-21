# GeckoTerminal Kotlin API

## Description

This library provides a convenient interface for interacting with the GeckoTerminal API V2. It allows you to fetch data from various DEXes such as Uniswap, Sushi, Pancakeswap, Curve, Balancer, and more.

## Features

- **Support for multiple DEXes and blockchains**: Fetch data from various DEXes across major blockchains.
- **Fast data updates**: Data is updated roughly every 10-20 seconds after a transaction is finalized on the blockchain.
- **Rate limit**: Free API usage is limited to 30 calls per minute.

## Usage

To use this library, you need to create an instance of `GeckoTerminalApiSync` or `GeckoTerminalApiAsync` and call the methods you need.

Example:

```kotlin code
val api = GeckoTerminalApiSync()
val result = api.getSimpleTokenPrices("eth", "0xc02aaa39b223fe8d0a0e5c4f27ead9083c756cc2,0xa0b86991c6218b36c1d19d4a2e9eb0ce3606eb48")
println(result)
```
```response
SimpleTokenPriceResponse(data=SimpleTokenPrice(id=e19a7376-0eb9-49ab-b6b5-1c93c63c65cd, type=simple_token_price, attributes=Attributes(tokenPrices={0xa0b86991c6218b36c1d19d4a2e9eb0ce3606eb48=0.999953145449764, 0xc02aaa39b223fe8d0a0e5c4f27ead9083c756cc2=2471.93947226648})))
```

## Acknowledgements

This project uses the GeckoTerminal API. More information can be found at `https://geckoterminal.com`.
