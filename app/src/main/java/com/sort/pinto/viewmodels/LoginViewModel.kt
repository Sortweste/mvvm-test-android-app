package com.sort.pinto.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sort.pinto.network.dtos.LoginDTO
import com.sort.pinto.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/*ViewModel for LoginActivity*/
@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: AuthRepository): ViewModel(){

    /*Two-way databinding implementation*/
    var inputEmail = MutableLiveData<String?>()
    val inputPassword = MutableLiveData<String>()
    val errorEmail = MutableLiveData<String?>()
    val errorPassword = MutableLiveData<String?>()

    /*Send login credentials to API*/
    fun login() = repository.login(LoginDTO(inputEmail.value.toString(), inputPassword.value.toString()))


    /* Validate form */
    fun performValidation(): Boolean{

        if(inputEmail.value.isNullOrBlank() || inputPassword.value.isNullOrBlank()){

            if(inputEmail.value.isNullOrBlank()) {
                errorEmail.value = "Ingrese correo electrónico"
            }else{
                errorEmail.value = null
            }

            if(inputPassword.value.isNullOrBlank()) {
                errorPassword.value = "Ingrese contraseña"
            }else{
                errorPassword.value = null
            }
            return false

        }else {
            errorEmail.value = null
            errorPassword.value = null
        }

        return true
    }

}