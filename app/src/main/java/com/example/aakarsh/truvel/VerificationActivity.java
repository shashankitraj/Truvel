package com.example.aakarsh.truvel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VerificationActivity extends AppCompatActivity {

    Button btnVer;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        mAuth=FirebaseAuth.getInstance();
        btnVer=findViewById(R.id.buttonVerification);

        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user=mAuth.getCurrentUser();
                if(user!=null){
                    if(user.isEmailVerified()){
                        Toast.makeText(getApplicationContext(),"Some Error Occoured",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),Splash.class));
                    }
                    else {
                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(),"Email sent.Please verify and Login again.",Toast.LENGTH_LONG).show();
                                finish();
                            }
                        });
                    }
                }
            }
        });
    }
}
