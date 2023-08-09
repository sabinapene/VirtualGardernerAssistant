package com.github.sabinapene;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivitySignUp extends AppCompatActivity {

    private String email="", password="";
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //initialise firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        findViewById(R.id.signinView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( getApplicationContext(), com.github.sabinapene.ActivitySignIn.class));
            }
        });


        findViewById(R.id.signUpBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

    }

    private void validateData() {

        //get data
        EditText editTextEmail = findViewById(R.id.emailEt);
        email = editTextEmail.getText().toString().trim();
        EditText editTextPassword = findViewById(R.id.passwordEt);
        password = editTextPassword.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //invalid email format

            Toast.makeText(ActivitySignUp.this, "Invalid Email Format", Toast.LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(password)){
            //no password entered

            Toast.makeText(ActivitySignUp.this, "Enter Password", Toast.LENGTH_SHORT).show();

        }
        else if(password.length()<6){
            //password too short

            Toast.makeText(ActivitySignUp.this, "Password must have at least 6 characters", Toast.LENGTH_SHORT).show();

        }
        else {
            //valid data, proceed with firebase sign up
            firebaseSignUp();
        }
    }

    private void firebaseSignUp() {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult){

                        //sign up succeeded
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        String email = firebaseUser.getEmail();
                        Toast.makeText(ActivitySignUp.this, "Account created", Toast.LENGTH_SHORT).show();

                        com.github.sabinapene.ActivitySignIn.setCurrentUserEmail(email);
                        //open main activity
                        startActivity(new Intent(ActivitySignUp.this, MainActivity.class));
                        finish();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //sign up failed
                        Toast.makeText(ActivitySignUp.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
