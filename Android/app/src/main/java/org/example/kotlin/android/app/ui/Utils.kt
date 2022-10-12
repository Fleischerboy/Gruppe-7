package org.example.kotlin.android.app.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.ui.auth.LoginFragment
import org.example.kotlin.android.app.ui.base.BaseFragment
import org.example.kotlin.android.app.ui.home.sell.SellFragment
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

fun<A: Activity> Activity.startNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it);
    }
}


fun View.visible(isVisible: Boolean) {
    visibility = if(isVisible) View.VISIBLE else View.GONE
}


fun View.enable(enabled: Boolean) {
    isEnabled = enabled;
    alpha = if(enabled) 1f else 0.5f
}


fun View.snackbar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
    snackbar.show();
}



//TODO should improve this code and make it more flexible.
fun Fragment.handleApiError(failure: Resource.Failure,
                            retry: (() -> Unit)? = null) {
    when {
         failure.isNetworkError -> requireView().snackbar("Please check your internet connection", retry)
         failure.errorCode == 401 -> {
             if(this is LoginFragment) {
                 requireView().snackbar("You've entered incorrect email or password")
             }


             else { (this as BaseFragment<*, *, *>).logout()

             }
         }
        failure.errorCode == 400 -> {
            if(this is LoginFragment || this is SellFragment) {
                requireView().snackbar("Missing required input")
            }
        }
        else -> {
            val error = failure.errorBody.toString()
            requireView().snackbar(error);
        }
    }



}

