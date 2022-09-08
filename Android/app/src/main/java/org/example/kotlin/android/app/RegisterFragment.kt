package org.example.kotlin.android.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.findNavController


class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //inputs
        val etSignUpFullName = view.findViewById<EditText>(R.id.etSignUpFullName);
        val etSignUpEmail = view.findViewById<EditText>(R.id.etSignUpEmail);
        val etSignUpPassword = view.findViewById<EditText>(R.id.etSignUpPassword);




        //
        val signIn = view.findViewById<TextView>(R.id.tvSignIn);

        //
        signIn.setOnClickListener {
            val navController = it.findNavController();
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment();
            navController.navigate(action);
        }
    }
}