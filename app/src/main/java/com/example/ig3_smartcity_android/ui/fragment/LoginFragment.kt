package com.example.ig3_smartcity_android.ui.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.ig3_smartcity_android.R
import com.example.ig3_smartcity_android.databinding.FragmentLoginBinding
import com.example.ig3_smartcity_android.model.LoginUser
import com.example.ig3_smartcity_android.model.NetworkError
import com.example.ig3_smartcity_android.model.Token
import com.example.ig3_smartcity_android.ui.actitvity.MainActivity
import com.example.ig3_smartcity_android.ui.actitvity.RegistrationActivity
import com.example.ig3_smartcity_android.ui.viewModel.LoginUserViewModel


class LoginFragment : Fragment() {

    private lateinit var usernameText : EditText
    private lateinit var passwordText: EditText
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginUserViewModel: LoginUserViewModel
    private var areAllFieldsChecked :Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

       loginUserViewModel = ViewModelProvider(this).get(LoginUserViewModel::class.java)
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        binding.viewModel = loginUserViewModel
        binding.lifecycleOwner = this

        sharedPreferences = requireActivity().getSharedPreferences(getString(R.string.sharedPref),Context.MODE_PRIVATE)

        usernameText = binding.username
        passwordText = binding.password

        loginUserViewModel.error.observe(viewLifecycleOwner){
                error : NetworkError -> this.showError(error)
        }
        binding.loginButtonID.setOnClickListener{
            areAllFieldsChecked = areFiledsNotEmpty()
            if(areAllFieldsChecked){
                loginUser()
            }
        }
        binding.signupID.setOnClickListener{
            goToRegisterActivity()
        }

        loginUserViewModel.jwt.observe(viewLifecycleOwner){
            token :Token ->this.preferencesSaved(token)
            goToMainActivity()
        }
        return binding.root

    }

    private fun preferencesSaved(token : Token){
        var editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(getString(R.string.token),token.token).apply()

    }

    private fun showError(error: NetworkError){
        when(error){
            NetworkError.TECHNICAL_ERROR ->Toast.makeText(activity,R.string.technical_error,Toast.LENGTH_LONG).show()
            NetworkError.NO_CONNECTION_ERROR ->Toast.makeText(activity,R.string.connection_error,Toast.LENGTH_LONG).show()
            NetworkError.REQUEST_ERROR ->Toast.makeText(activity,R.string.request_error,Toast.LENGTH_LONG).show()
            NetworkError.BAD_CREDENTIALS_ERROR ->Toast.makeText(activity,R.string.credentials_problem,Toast.LENGTH_LONG).show()
            else ->{
                NetworkError.NO_ERROR_DETECTED
            }
        }
    }

    fun areFiledsNotEmpty(): Boolean {
        if (usernameText!!.length() == 0) {
            usernameText!!.error = resources.getText(R.string.username_empty_message)
            return false
        }
        if (passwordText!!.length() == 0) {
            passwordText!!.error = resources.getText(R.string.password_empty_message)
            return false
        }
        return true
    }

    private fun goToMainActivity(){
        var intent : Intent = Intent(requireActivity().applicationContext,MainActivity::class.java)
        startActivity(intent)
    }

    private fun goToRegisterActivity(){
        var intent :Intent = Intent(requireActivity().applicationContext,RegistrationActivity::class.java)
        startActivity(intent)
    }

    /**
     * calls the loginUserViewModel to log in the user.
     */
    private fun loginUser(){
        var login = LoginUser(
            usernameText.text.toString(),
            passwordText.text.toString()
        )
        loginUserViewModel.loginUser(login)
    }

}