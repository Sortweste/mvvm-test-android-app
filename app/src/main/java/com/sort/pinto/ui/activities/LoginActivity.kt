package com.sort.pinto.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.sort.pinto.databinding.ActivityLoginBinding
import com.sort.pinto.network.responses.AuthResponse
import com.sort.pinto.storage.AuthManager
import com.sort.pinto.utils.Resource
import com.sort.pinto.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var mSharedPreferences: AuthManager

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verifySession()
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.loginViewModel = loginViewModel
        binding.buttonGoLogin.setOnClickListener(this)
        binding.redirectSignUp.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun verifySession() {
        if (!mSharedPreferences.getAccessToken().isNullOrEmpty())
            startIntent()
    }

    private fun startIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun initObservers() {
        loginViewModel.login().observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    mSharedPreferences.storeTokens((it.data as AuthResponse))
                    startIntent()
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    binding.progressBar.visibility = View.GONE
                }
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding.buttonGoLogin.id -> {
                if (loginViewModel.performValidation())
                    lifecycleScope.launch {
                        initObservers()
                    }
            }
            binding.redirectSignUp.id -> { startIntent() }
        }
    }

}