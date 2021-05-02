package com.sort.pinto.network.responses

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String
)