package com.cachingpractice.android.data.remote

class BeerApiMockImpl : BeerApi {

    override suspend fun getBeers(page: Int, pageSize: Int): List<BeerDto> {
        val beers = arrayListOf<BeerDto>()
        for (i in 1..pageSize)
            beers.add(sampleBeer(i + ((page - 1) * 10)))
        return beers
    }

    private fun sampleBeer(id: Int): BeerDto {
        return BeerDto(
            id,
            "Beer Title $id",
            "Beer Tagline",
            "Beer description Beer description Beer description Beer description Beer description Beer description ",
            "10/1991",
            "https://i5.walmartimages.com/asr/8e207c86-7884-4bb9-9181-70ca0928e7ad.a67bd9ad17b4329905b247e14f739690.jpeg",
        )
    }
}