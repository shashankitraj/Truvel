package com.example.aakarsh.truvel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.aakarsh.truvel.model.UserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    ImageView imgProfile;
    private static final int Choose_Image=101;
    Uri uriProfileImage;
    String profileImageUrl;
    FirebaseAuth mAuth;
    Button btnLogout;
    TextView tvName,tvEmail;
    DatabaseReference databaseReference;
    Button btnSave;
    LinearLayout linearLayout;
    EditText etName;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imgProfile=findViewById(R.id.imageViewProfilePhoto);
        btnLogout=findViewById(R.id.buttonLogout);
        tvName=findViewById(R.id.textViewPofileName);
        tvEmail=findViewById(R.id.textViewProfileEmail);
        linearLayout=findViewById(R.id.linearlayoutName);
        etName=findViewById(R.id.editTextPofileNameEnter);
        mAuth=FirebaseAuth.getInstance();
        btnSave=findViewById(R.id.buttonSave);

        loadUserInformation();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInfomation();
            }
        });
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageChoser();
            }
        });

        loadUserInformation();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
        getUser();

//        final FirebaseUser user=mAuth.getCurrentUser();
//        databaseReference=FirebaseDatabase.getInstance().getReference("Users");
//        final String id=databaseReference.getKey();
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                UserInfo userInfo=dataSnapshot.child(id).getValue(UserInfo.class);
//                tvName.setText(userInfo.getName());
//                tvEmail.setText(userInfo.getEmail());
//                tvPhone.setText(userInfo.getPhone());
//                 //String usr_name=dataSnapshot.child(user.getUid()).getValue(UserInfo.class);
//                 //String usr_email=dataSnapshot.child(user.getUid()).getValue(UserInfo.class);
//                 // String usr_phone=dataSnapshot.child(user.getUid()).getValue(UserInfo.class);
//                 //UserInfo userInfo=dataSnapshot.getValue(UserInfo.class);
//                //Toast.makeText(getApplicationContext(),userInfo.getEmail(),Toast.LENGTH_SHORT).show();
//                // tvName.setText(userInfo.getName());
//                //tvEmail.setText(userInfo.getEmail());
//                //tvPhone.setText(userInfo.getPhone());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

    }
    public void getUser(){
        FirebaseUser user=mAuth.getCurrentUser();
        if (user != null) {

            String userId = user.getUid();
            String userEmail = user.getEmail();
            String name=user.getDisplayName();
            tvName.setText(name);
            tvEmail.setText(userEmail);
        }

    }
    private void loadUserInformation() {
        FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null) {

            if (user.getPhotoUrl() != null) {
                Glide.with(this).load(user.getPhotoUrl().toString()).into(imgProfile);
            }

        }
    }

    private void saveUserInfomation() {
        final FirebaseUser user=mAuth.getCurrentUser();
        String name=etName.getText().toString().trim();
        if(user!=null){
            if(TextUtils.isEmpty(name)){
                etName.setError("Name is Required");
                etName.requestFocus();
            }
            UserProfileChangeRequest profile=new UserProfileChangeRequest.Builder().setDisplayName(name).setPhotoUri(Uri.parse(profileImageUrl)).build();
            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Profile Updated",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            btnSave.setVisibility(View.GONE);
            linearLayout.setVisibility(View.GONE);
            finish();
            Toast.makeText(getApplicationContext(),"Profile Updated.",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Choose_Image && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            uriProfileImage = data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uriProfileImage);
                imgProfile.setImageBitmap(bitmap);
                uploadImageToFirebaseStorage();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
    private void uploadImageToFirebaseStorage() {
        StorageReference profileImageRef= FirebaseStorage.getInstance().getReference("profilrpics/"+ System.currentTimeMillis()+".jpg");
        if(uriProfileImage!=null){
            profileImageRef.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getApplicationContext(),"Image Added to firebase",Toast.LENGTH_SHORT).show();
                    profileImageUrl=taskSnapshot.getDownloadUrl().toString();
                }
            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
    private void showImageChoser(){

        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Profile Image"),Choose_Image);
        btnSave.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.VISIBLE);

    }
}