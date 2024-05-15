package com.example.todoapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUpActivity extends AppCompatActivity {

    EditText username, password, confirm_password;
    Button signup, signin;
    DBHelper DB;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        signup = findViewById(R.id.signup_button);
        signin = findViewById(R.id.signin);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username != null && password != null && confirm_password != null) {
                    String user = username.getText().toString();
                    String pass = password.getText().toString();
                    String repass = confirm_password.getText().toString();

                    if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                        Toast.makeText(SignUpActivity.this, "All the field Required", Toast.LENGTH_SHORT).show();
                    else{
                        if (pass.equals(repass)){
                            Boolean checkuser = DB.checkusername(user);
                            if (checkuser == false){
                                Boolean insert = DB.instertData(user,pass);
                                if (insert == true){
                                    Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(SignUpActivity.this, "Registration Failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(SignUpActivity.this, "User already existing.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(SignUpActivity.this, "Password are not matching.", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                else {
                    // Handle the case where one or more EditTexts are null
                    Toast.makeText(SignUpActivity.this, "Error: EditTexts not initialized", Toast.LENGTH_SHORT).show();
                }



            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}