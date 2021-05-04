package com.sort.pinto.network.responses

import com.sort.pinto.data.Category

data class PaginatedCategoriesResponse(
        val items: List<Category>,
        val metadata: MetadataResponse,
        val links: LinkResponse
)