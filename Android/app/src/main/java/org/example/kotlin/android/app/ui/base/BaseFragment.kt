package org.example.kotlin.android.app.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.example.kotlin.android.app.data.UserPreferences
import org.example.kotlin.android.app.data.repository.BaseRepository
import org.example.kotlin.android.app.data.restapi.RemoteDataSource
import org.example.kotlin.android.app.data.restapi.UserApi
import org.example.kotlin.android.app.ui.auth.AuthActivity
import org.example.kotlin.android.app.ui.startNewActivity

/*
- This is just basic base fragment that is just creating the binding for our fragment
  We will extend this fragment whenever we will create our actual fragment

- for every fragment we have in out application we have some commons for each.
for example we will have create viewModel, data-binding and repository

 */
abstract class BaseFragment<VM : BaseViewModel, B : ViewBinding, R : BaseRepository> : Fragment() {

    protected lateinit var userPreferences: UserPreferences
    protected lateinit var binding: B
    protected lateinit var viewModel: VM;

    // with the help of this remote data source we can create our api instance.
    protected val remoteDataSource = RemoteDataSource()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userPreferences = UserPreferences(requireContext());
        binding = getFragmentBinding(inflater, container)

        // we cant directly initialize the view model because the viewModel will contain a constructor parameter.
        // We need to create a factory
        val factory = ViewModelFactory(getFragmentRepository());

        // our viewModel for our fragment
        viewModel = ViewModelProvider(this, factory).get(getViewModel())

        lifecycleScope.launch { userPreferences.getAccessToken.first()}
        lifecycleScope.launch { userPreferences.getUserId.first()}

        return binding.root;
    }

    fun logout() = lifecycleScope.launch {
        val authToken = userPreferences.getAccessToken.first()
        val api = remoteDataSource.buildServiceApi(UserApi::class.java, authToken)
        viewModel.logout(api);
        userPreferences.clear()
        requireActivity().startNewActivity(AuthActivity::class.java)
    }

    // abstract function for defining the viewModel
    abstract fun getViewModel(): Class<VM>


    // abstract function for defining the actual binding
    // inflater and container parameter is required for inflating the view-binding
    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B

    // abstract function for defining repository
    abstract fun getFragmentRepository(): R
}