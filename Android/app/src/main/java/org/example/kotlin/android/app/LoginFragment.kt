package org.example.kotlin.android.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import org.example.kotlin.android.app.api.UserApi
import org.example.kotlin.android.app.models.SignIn
import org.example.kotlin.android.app.retrofit.RetrofitClient
import org.example.kotlin.android.app.services.UserRestApiService


class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //inputs
        val etEmail = view.findViewById<EditText>(R.id.etLoginEmail);
        val etPassword = view.findViewById<EditText>(R.id.etLoginPassword);






        // buttons
        val signInBtn = view.findViewById<Button>(R.id.loginBtn);
        val signUp = view.findViewById<TextView>(R.id.tvSignUp);


        //ClickListeners
        signInBtn.setOnClickListener { it ->
            val userApiService = UserRestApiService();
            val navController = it.findNavController();
            val userLoginInfo = SignIn(
                userEmail = "philip.eiler@hotmail.com",
                userPassword = "missi123"
            )

            userApiService.signIn(userLoginInfo) {
                println(it?.userFullName)
                if(it?.userId != null) {
                    Toast.makeText(activity,"Welcome: " + it.userFullName, Toast.LENGTH_SHORT).show()
                    val action = LoginFragmentDirections.actionLoginFragmentToProfileFragment();
                    action.email = it.userEmail.toString();
                    action.fullname = it.userFullName.toString();
                    navController.navigate(action);

                } else {
                    Toast.makeText(activity, "Login not correct, Please try again", Toast.LENGTH_SHORT).show();
                }
            }

        }


        signUp.setOnClickListener {
            val navController = it.findNavController();
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment();
            navController.navigate(action);
        }


    }
}