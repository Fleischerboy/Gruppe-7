package org.example.kotlin.android.app.ui.base




import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.example.kotlin.android.app.data.repository.*
import org.example.kotlin.android.app.ui.auth.AuthViewModel
import org.example.kotlin.android.app.ui.home.explore.ExploreViewModel
import org.example.kotlin.android.app.ui.home.inbox.InboxViewModel
import org.example.kotlin.android.app.ui.home.inbox.bid_system.bids.BidsViewModel
import org.example.kotlin.android.app.ui.home.inbox.chat_system.chat.ChatViewModel
import org.example.kotlin.android.app.ui.home.inbox.chat_system.chats.ChatsViewModel
import org.example.kotlin.android.app.ui.home.productOverview.ProductViewModel
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
            modelClass.isAssignableFrom(ProductViewModel::class.java) -> ProductViewModel(repository as ProductRepository) as T
            modelClass.isAssignableFrom(InboxViewModel::class.java) -> InboxViewModel(repository as InboxRepository) as T
            modelClass.isAssignableFrom(ChatsViewModel::class.java) -> ChatsViewModel(repository as ChatsRepository) as T
            modelClass.isAssignableFrom(ChatViewModel::class.java) -> ChatViewModel(repository as ChatRepository) as T
           modelClass.isAssignableFrom(BidsViewModel::class.java) -> BidsViewModel(repository as BidRepository) as T
            else -> throw  IllegalArgumentException("ViewModelClass Not Found");
        }
    }

}