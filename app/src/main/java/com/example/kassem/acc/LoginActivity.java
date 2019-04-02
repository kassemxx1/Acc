package com.example.kassem.acc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
EditText  inputEmail;
EditText imputPassword;
Button Login;

String Email;
String Password;
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputEmail=findViewById(R.id.emailinpput);
        imputPassword=findViewById(R.id.passwordinpput);
        Login=findViewById(R.id.loginButton);

         Login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Email=inputEmail.getText().toString();
            Password=imputPassword.getText().toString();
        //    Toast.makeText(LoginActivity.this,Email+"",Toast.LENGTH_SHORT).show();
            signin(Email,Password);

        }
    });


    }
    void signin(String email,String password){

        email=inputEmail.getText().toString();
        password=imputPassword.getText().toString();
        Toast.makeText(LoginActivity.this,Email+"",Toast.LENGTH_SHORT).show();
      //  mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            intent.putExtra("emailll",user.getEmail().toString());
                            startActivity(intent);

                        } else {

                            Toast.makeText(LoginActivity.this,"email not found",Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}
