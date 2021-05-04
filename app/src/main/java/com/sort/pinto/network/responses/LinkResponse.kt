package com.sort.pinto.network.responses

data class LinkResponse(
        val first: String,
        val previous: String,
        val next: String,
        val last: String
)