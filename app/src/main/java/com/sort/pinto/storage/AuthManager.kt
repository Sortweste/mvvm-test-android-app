package com.sort.pinto.storage

import com.sort.pinto.constants.ACCESS_TOKEN_KEY
import com.sort.pinto.constants.REFRESH_TOKEN_KEY
import com.sort.pinto.network.responses.AuthResponse
import javax.inject.Inject

class AuthManager @Inject constructor(private val sharedPreferencesManager: SharedPreferencesManager){
        fun getAccessToken(): String? = sharedPreferencesManager.getData(ACCESS_TOKEN_KEY)

        fun getRefreshToken(): String? = sharedPreferencesManager.getData(REFRESH_TOKEN_KEY)

        fun storeTokens(data: AuthResponse){
            sharedPreferencesManager.putData(ACCESS_TOKEN_KEY, data.accessToken)
            sharedPreferencesManager.putData(REFRESH_TOKEN_KEY, data.refreshToken)
        }
}