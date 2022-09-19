package org.example.kotlin.android.app.ui.base




import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.example.kotlin.android.app.data.repository.AuthRepository
import org.example.kotlin.android.app.data.repository.BaseRepository
import org.example.kotlin.android.app.data.repository.UserRepository
import org.example.kotlin.android.app.ui.auth.AuthViewModel
import org.example.kotlin.android.app.ui.home.profile.ProfileViewModel
import java.lang.IllegalArgumentException


class ViewModelFactory(
    private val repository: BaseRepository
    ): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(repository as UserRepository) as T
            else -> throw  IllegalArgumentException("ViewModelClass Not Found");
        }
    }

}