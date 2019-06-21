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
import android.widget.Toast;

import com.example.aakarsh.truvel.model.UserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText etEmail,etPassword,etName,etNumber;
    Button btnRegister;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail=findViewById(R.id.editTextEmailRegister);
        etPassword=findViewById(R.id.editTextPasswordRegister);
        etName=findViewById(R.id.editTextNameRegister);
        etNumber=findViewById(R.id.editTextPhoneRegister);
        btnRegister=findViewById(R.id.buttonRegister);
        mAuth=FirebaseAuth.getInstance();
        dbRef=FirebaseDatabase.getInstance().getReference("Users");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        final String email=etEmail.getText().toString().trim();
        String password=etPassword.getText().toString().trim();
        final String phone=etNumber.getText().toString();
        final String Name=etName.getText().toString();
        if (TextUtils.isEmpty(email)){

            etEmail.setError("Email should be entered");
            etEmail.requestFocus();
            Toast.makeText(getApplicationContext(), "Email Required", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password)){
            etPassword.setError("Password should be entered");
            etPassword.requestFocus();
            Toast.makeText(getApplicationContext(), "Password Required", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(Name)){
            etName.setError("Name is required");
            etName.requestFocus();
            Toast.makeText(getApplicationContext(), "Name Required", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(phone)){
            etNumber.setError("Phone number is required");
            etNumber.requestFocus();
            Toast.makeText(getApplicationContext(), "Phone Number Required", Toast.LENGTH_SHORT).show();
        }
        if(phone.length()!=10){
            etNumber.setError("Number should be 10 digits");
            etNumber.requestFocus();
            Toast.makeText(getApplicationContext(), "Number should be 10 digits", Toast.LENGTH_SHORT).show();
        }
        else{
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String TAG = "Register Activity";
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");

                            String id=dbRef.push().getKey();
                            UserInfo userInfo =new UserInfo(Name,email,phone);
                            dbRef.child(id)
                                    .setValue(userInfo);
                            finish();
                            startActivity(new Intent(getApplicationContext(),VerificationActivity.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }

        });
    }
    }
}
