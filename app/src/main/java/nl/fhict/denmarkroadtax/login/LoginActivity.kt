package nl.fhict.denmarkroadtax.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import nl.fhict.denmarkroadtax.R
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initLoginUsernameEditText()
        initLoginPasswordEditText()
        initLoginCheckCredentialsButton()
    }

    private fun initLoginUsernameEditText() {
        loginUsernameInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editText: Editable?) {
                if (editText.toString().trim().isNotEmpty()) {
                    loginUsernameInputLayout.error = null
                    loginCheckCredentials.isEnabled = true
                } else {
                    loginUsernameInputLayout.error =
                        getString(R.string.login_to_short_username_error)
                    loginCheckCredentials.isEnabled = false
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    private fun initLoginPasswordEditText() {
        loginPasswordInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editText: Editable?) {
                if (editText.toString().trim().isNotEmpty()) {
                    loginPasswordInputLayout.error = null
                    loginCheckCredentials.isEnabled = true
                } else {
                    loginPasswordInputLayout.error =
                        getString(R.string.login_to_short_password_error)
                    loginCheckCredentials.isEnabled = false
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    private fun initLoginCheckCredentialsButton() {
        loginCheckCredentials.setOnClickListener {
            presenter.onLoginButtonClicked(
                loginUsernameInput.text.toString(),
                loginPasswordInput.text.toString()
            )
        }
    }

    override fun onStop() {
        presenter.stopPresenting()
        super.onStop()
    }

    override fun showWrongPasswordError() {
        loginPasswordInputLayout.error = getString(R.string.login_invalid_password)
    }

    override fun showLoadingIndicator() {

    }

    override fun hideLoadingIndicator() {
    }

    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}
