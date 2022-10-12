package org.example.kotlin.android.app.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import kotlinx.coroutines.launch
import org.example.kotlin.android.app.databinding.FragmentLoginBinding
import org.example.kotlin.android.app.data.repository.AuthRepository
import org.example.kotlin.android.app.data.requestsBody.SignIn
import org.example.kotlin.android.app.data.restapi.AuthApi
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.ui.base.BaseFragment
import org.example.kotlin.android.app.ui.handleApiError
import org.example.kotlin.android.app.ui.home.HomeActivity
import org.example.kotlin.android.app.ui.startNewActivity
import org.example.kotlin.android.app.ui.visible
import kotlin.math.log


class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {


    // logic for our fragment is inside the onViewCreated.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.progressbar.visible(false);


        // to hit the api we need to use the viewModel
        // Observe the login response live data
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(it is Resource.Loading);
            when(it) {
                is Resource.Success -> {
                        lifecycleScope.launch { viewModel.saveAuthToken(it.value.token) }
                        lifecycleScope.launch { viewModel.saveUserId(it.value.id.toString()) }
                        requireActivity().startNewActivity(HomeActivity::class.java)
                }
                is Resource.Failure -> handleApiError(it) { login() }
            }
        })

        /*
        binding.etLoginPassword.addTextChangedListener {
            val email = binding.etLoginEmail.text.toString().trim()
            binding.loginBtn.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

         */


        binding.tvSignUp.setOnClickListener() {
            val navController = it.findNavController();
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            navController.navigate(action);
        }

        binding.loginBtn.setOnClickListener() {
            login();
        }
    }

    private fun login() {
        val email = binding.etLoginEmail.text.toString().trim();
        val password = binding.etLoginPassword.text.toString().trim();
        val userLoginInfo1 = SignIn(
            userEmail = "philip.eiler@hotmail.com",
            userPassword = "missi123"
        )
        val userLoginInfo2 = SignIn(
            userEmail = "philipboy@hotmail.com",
            userPassword = "missii"
        )

        //TODO ADD INPUT VALIDATION
        viewModel.login(userLoginInfo1);
    }

    override fun getViewModel(): Class<AuthViewModel> = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildServiceApi(AuthApi::class.java), userPreferences)

}