package org.example.kotlin.android.app.ui.base




import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.example.kotlin.android.app.data.repository.*
import org.example.kotlin.android.app.ui.auth.AuthViewModel
import org.example.kotlin.android.app.ui.home.explore.ExploreViewModel
import org.example.kotlin.android.app.ui.home.profile.ProfileViewModel
import org.example.kotlin.android.app.ui.home.sell.SellViewModel
import java.lang.IllegalArgumentException


class ViewModelFactory(
    private val repository: BaseRepository
    ): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(repository as UserRepository) as T
            modelClass.isAssignableFrom(ExploreViewModel::class.java) -> ExploreViewModel(repository as ProductRepository) as T
            modelClass.isAssignableFrom(SellViewModel::class.java) -> SellViewModel(repository as SellRepository) as T
            else -> throw  IllegalArgumentException("ViewModelClass Not Found");
        }
    }

}