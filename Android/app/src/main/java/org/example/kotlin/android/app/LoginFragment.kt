package org.example.kotlin.android.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController


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

        val signUp = view.findViewById<TextView>(R.id.tvSignUp);

        signUp.setOnClickListener {
            val navController = it.findNavController();
            navController.navigate(R.id.action_loginFragment_to_registerFragment);
        }


    }
}