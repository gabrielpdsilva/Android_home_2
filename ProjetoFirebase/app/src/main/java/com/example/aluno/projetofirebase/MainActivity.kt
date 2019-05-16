package com.example.aluno.projetofirebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    lateinit var btnSignUp: Button
    lateinit var btnLogin: Button
    lateinit var lbEmailDoUsuarioLogado: TextView
    lateinit var fbAuth : FirebaseAuth
    lateinit var txtEmail: EditText
    lateinit var txtPass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    this.lbEmailDoUsuarioLogado = findViewById<TextView>(R.id.lbEmailDoUsuarioLogado)
    this.fbAuth = FirebaseAuth.getInstance()
    this.txtEmail = findViewById<EditText>(R.id.txtEmail)
    this.txtPass = findViewById<EditText>(R.id.txtPass)
    this.btnSignUp = findViewById<Button>(R.id.btnSignUp)
    this.btnLogin = findViewById<Button>(R.id.btnLogin)

        public override fun onStart(){
            val currentUser = this.fbAuth.currentUser
            updateUI(currentUser)
        }

        private fun updateUI(user: FirebaseUser?){
            if(user != null){
                this.lbEmailDoUsuarioLogado.text = user.email
            }else{
                this.lbEmailDoUsuarioLogado.text = "Nenhum User Logado"
            }
        }

        private fun signUp(){
            this.fbAuth.createUserWithEmailAndPassword(this.txtEmail, this.txtPass)
                    .addOnCompleteListener(this){ task ->
                        if(task.isSuccessful){
                            Log.d(Tag, "createUserWithEmail:success")
                            val user = auth.currentUser
                            updateUI(user)
                        }else{
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                            updateUI(null)
                        }
                    }
        }
    }
}
