package com.doodle.my5plan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {
    private val TAG = "LoginActivity"
    private val RC_SIGN_IN = 100


    lateinit var auth : FirebaseAuth
    lateinit var authListener : FirebaseAuth.AuthStateListener
    lateinit var googleSigneInClient : GoogleSignInClient //구글 로그인을 관리하는 클래스



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Configure Google Sign In
        //GoogleSignInOptions 옵션을 관리해주는 클래스로 API 키값과 요청할 값이 저장되어 있다.
        var gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSigneInClient= GoogleSignIn.getClient(this,gso)

//        auth = FirebaseAuth.getInstance()


        loginButton.setOnClickListener {
            signIn()
        }

    }

    private fun signIn(){
        val signInIntent = googleSigneInClient.signInIntent
        startActivityForResult(signInIntent,RC_SIGN_IN)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                // ...
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)

//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    Log.d(TAG, "signInWithCredential:success")
//                    val user = auth?.currentUser
//                } else {
//                    Log.w(TAG, "signInWithCredential:failure", task.exception)
//
//                }
//
//                // ...
//            }
    }
}