package com.sort.pinto.repositories

import com.sort.pinto.network.dtos.AuthDTO
import com.sort.pinto.network.dtos.LoginDTO
import com.sort.pinto.network.remotes.AuthRemoteDataSource
import com.sort.pinto.utils.performPostOperation
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authRemoteDataSource: AuthRemoteDataSource){

    fun login(loginDTO: LoginDTO) = performPostOperation{ authRemoteDataSource.login(loginDTO) }

    fun logout() = performPostOperation{ authRemoteDataSource.logout() }

    fun refresh(authDTO: AuthDTO) = performPostOperation { authRemoteDataSource.refresh(authDTO) }

}