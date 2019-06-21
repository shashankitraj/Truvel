package com.example.aakarsh.truvel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    //declaring ui elements
    EditText etEmail,etPassword;
    Button login;
    SignInButton googleLogin;
    TextView tvRegister;

    //declaring firebaseAuth object.
    FirebaseAuth mAuth;

    private String TAG="Login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //intializing elements
        mAuth=FirebaseAuth.getInstance();
        etEmail=findViewById(R.id.editTextEmail);
        etPassword=findViewById(R.id.editTextPassword);
        login=findViewById(R.id.buttonLogin);
        googleLogin=findViewById(R.id.googleSignIn);
        tvRegister=findViewById(R.id.textViewRegister);


        //Adding button click listener on login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        //Adding buttonClick listener on Google button
        googleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        //Going to register activity
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start the register email.
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });

    }


    void signIn(){
        String email=etEmail.getText().toString().trim();
        String password=etPassword.getText().toString();
        if (TextUtils.isEmpty(email)){

            etEmail.setError("Email should be entered");
            etEmail.requestFocus();
            Toast.makeText(getApplicationContext(), "Email Required", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password)){
            etPassword.setError("Password should be entered");
            etPassword.requestFocus();
            Toast.makeText(getApplicationContext(), "Password Required", Toast.LENGTH_SHORT).show();
        }
        else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                finish();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }
}
