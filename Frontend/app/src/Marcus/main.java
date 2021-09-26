package com.example.logintestscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private Button Login;
    private Button ForgotLogin;
    private TextView ForgotInfo;
    private TextView IncorrectLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = (EditText)findViewById(R.id.etUsername);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button)findViewById(R.id.btnLogin);
        ForgotLogin = (Button)findViewById(R.id.btnForgot);
        ForgotInfo = (TextView)findViewById(R.id.textView);
        IncorrectLogin = (TextView)findViewById(R.id.tvIncorrectLogin);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateLogin(Username.getText().toString(), Password.getText().toString());
            }
        });

        ForgotLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgotInfo.setText("Username: Admin\nPassword: password");
            }
        });

    }

    private void validateLogin(String user, String pass) {
        if ((user.equals("Admin")) && (pass.equals("password"))) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }else{
            IncorrectLogin.setText("Incorrect Credentials! Try Again!");
        }
    }
}
