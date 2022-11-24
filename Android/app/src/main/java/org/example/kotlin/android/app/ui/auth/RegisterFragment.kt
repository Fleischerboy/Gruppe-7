package org.example.kotlin.android.app.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import org.example.kotlin.android.app.R
import org.example.kotlin.android.app.data.repository.AuthRepository
import org.example.kotlin.android.app.data.requestsBody.SignUp
import org.example.kotlin.android.app.data.restapi.AuthApi
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.databinding.FragmentRegisterBinding
import org.example.kotlin.android.app.ui.base.BaseFragment
import org.example.kotlin.android.app.ui.handleApiError


class RegisterFragment : BaseFragment<AuthViewModel, FragmentRegisterBinding, AuthRepository>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.signUpResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    Toast.makeText(context,"user successfully created!", Toast.LENGTH_SHORT).show()
                }
                is Resource.Failure -> {
                    handleApiError(it)
                }
            }
        })


        binding.signUpBtn.setOnClickListener() {
            registerUser()
            binding.etSignUpFullName.text = null
            binding.etSignUpEmail.text = null
            binding.etSignUpPassword.text = null
        }




        binding.tvSignIn.setOnClickListener {
            val navController = it.findNavController();
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment();
            navController.navigate(action);

        }
    }

    private fun registerUser() {
       val fullName = binding.etSignUpFullName.text.toString()
       val email = binding.etSignUpEmail.text.toString()
       val password = binding.etSignUpPassword.text.toString()
        val userRegisterData = SignUp(
            userFullName = fullName,
            userEmail = email,
            userPassword = password,

        )
        println(userRegisterData)
        viewModel.signUp(userRegisterData)
    }

    override fun getViewModel(): Class<AuthViewModel> {
        return AuthViewModel::class.java
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRegisterBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): AuthRepository = AuthRepository(remoteDataSource.buildServiceApi(AuthApi::class.java), userPreferences)
}