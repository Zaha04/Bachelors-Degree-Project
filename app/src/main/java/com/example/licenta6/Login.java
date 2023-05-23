package com.example.licenta6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private TextInputEditText login_email,login_password;
    private View login_button;
    private FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_email=findViewById(R.id.email_login);
        login_password=findViewById(R.id.password_login);
        mauth=FirebaseAuth.getInstance();
        login_button=findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });


    }

    private void loginUser() {
        String email,password;
        email=login_email.getText().toString();
        password=login_password.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"Please enter your e-mail",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"Please enter your password",Toast.LENGTH_LONG).show();
            return;
        }
        mauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Login succesful",Toast.LENGTH_LONG).show();

                    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        Intent intent=new Intent(Login.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Te rugam sa-ti validezi e-mailul!",Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}