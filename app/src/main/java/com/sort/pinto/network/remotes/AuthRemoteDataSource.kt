package com.sort.pinto.network.remotes

import com.sort.pinto.network.dtos.AuthDTO
import com.sort.pinto.network.dtos.LoginDTO
import com.sort.pinto.network.services.AuthService
import com.sort.pinto.utils.SafeApiRequest
import javax.inject.Inject

/*Class to perform Auth operations to API*/
class AuthRemoteDataSource @Inject constructor(private val authService: AuthService): SafeApiRequest() {

    suspend fun login(loginDTO: LoginDTO) = apiRequest { authService.login(loginDTO) }

    suspend fun logout() = apiRequest { authService.logout() }

    suspend fun refresh(authDTO: AuthDTO) = apiRequest { authService.refresh(authDTO) }

}