package com.example.hw3_m5

data class PixaModel(
    val hits: List<ImageModel>
)

data class ImageModel(
    val largeImageURL: String
)