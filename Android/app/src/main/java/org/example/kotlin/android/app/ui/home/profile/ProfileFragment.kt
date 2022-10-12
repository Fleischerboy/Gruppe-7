package org.example.kotlin.android.app.ui.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.example.kotlin.android.app.data.UserPreferences
import org.example.kotlin.android.app.data.repository.UserRepository
import org.example.kotlin.android.app.data.responses.LoginResponse
import org.example.kotlin.android.app.data.responses.UserResponse
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.data.restapi.UserApi
import org.example.kotlin.android.app.databinding.FragmentProfileBinding
import org.example.kotlin.android.app.ui.base.BaseFragment
import org.example.kotlin.android.app.ui.handleApiError
import org.example.kotlin.android.app.ui.visible


class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding, UserRepository>() {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.visible(false);

        // calling runBlocking this function when fragment launches (should find another way to do this!!)
        val userId = runBlocking {userPreferences.getUserId.first()}
        viewModel.getUser(userId.toString())

        viewModel.user.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Success -> {
                    binding.progressBar.visible(false);
                    updateUI(it.value)
                }
                is Resource.Loading -> {
                    binding.progressBar.visible(true);
                }
                is Resource.Failure -> {
                    handleApiError(it)
                }
            }
        })


        binding.btnLogout.setOnClickListener {
            logout()
        }

    }



    private fun updateUI(user: UserResponse) {
       with(binding) {
           tvWelcomeUserText.text = user.fullname
           tvEmail.text = user.email
           tvCreatedAt.text = user.createdAt
       }
    }

    override fun getViewModel(): Class<ProfileViewModel> = ProfileViewModel::class.java


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProfileBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking {  userPreferences.getAccessToken.first() }
        val api = remoteDataSource.buildServiceApi(UserApi::class.java, token);
        return UserRepository(api)
    }


}