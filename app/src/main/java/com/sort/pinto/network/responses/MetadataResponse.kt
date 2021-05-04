package com.sort.pinto.network.responses

data class MetadataResponse(
        val totalItems: Long,
        val itemCount: Long,
        val itemsPerPage: Int,
        val totalPages: Int,
        val currentPage: Int
)