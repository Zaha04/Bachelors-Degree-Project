package com.example.licenta6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class Register extends AppCompatActivity {
private View register;
    private FirebaseAuth mauth;
private TextInputEditText email,passowrd;
private ProgressBar progress_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email=findViewById(R.id.email_register);
        mauth=FirebaseAuth.getInstance();
        passowrd=findViewById(R.id.password_register);
        register=findViewById(R.id.register_button);
        progress_register=findViewById(R.id.progressBar_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registeruser();

            }
        });

    }

    private void registeruser() {
        progress_register.setVisibility(View.VISIBLE);
        String email_register=email.getText().toString();
        String password_register=passowrd.getText().toString();
        if(TextUtils.isEmpty(email_register)){
            Toast.makeText(getApplicationContext(),"Te rog introdu-ti e-mailul corect!",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password_register)){
            Toast.makeText(getApplicationContext(),"Te rog introdu-ti o parola!",Toast.LENGTH_LONG).show();
            return;
        }
        mauth.createUserWithEmailAndPassword(email_register,password_register).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mauth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                            Toast.makeText(getApplicationContext(),"Inregistrare completa,va rugam sa-ti verifici e-mailul",Toast.LENGTH_LONG).show();
                            progress_register.setVisibility(View.GONE);
                            Intent intent=new Intent(Register.this,After_register.class);
                            startActivity(intent);


                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(),"A aparuta o eroare "+ "Te rog incearca din nou!",Toast.LENGTH_LONG).show();
                    progress_register.setVisibility(View.GONE);
                }
            }
        });

    }
}