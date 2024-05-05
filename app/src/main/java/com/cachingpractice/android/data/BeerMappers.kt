package com.cachingpractice.android.data

import com.cachingpractice.android.data.local.BeerEntity
import com.cachingpractice.android.data.remote.BeerDto
import com.cachingpractice.android.domain.Beer

fun BeerDto.toBeerEntity(): BeerEntity {
    return BeerEntity(
        id,
        name,
        tagline,
        description,
        firstBrewed,
        imageUrl,
    )
}

fun BeerEntity.toBeer(): Beer {
    return Beer(
        id,
        name,
        tagline,
        description,
        firstBrewed,
        imageUrl,
    )
}