package com.example.cryptoapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cryptoapp.R;
import com.example.cryptoapp.database.UserRepository;
import com.example.cryptoapp.session.SessionManager;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText login = findViewById(R.id.loginInput);
        EditText pass = findViewById(R.id.passInput);

        findViewById(R.id.loginBtn).setOnClickListener(v -> {

            UserRepository repo = new UserRepository(this);
            SessionManager session = new SessionManager(this);

            String name = repo.login(
                    login.getText().toString(),
                    pass.getText().toString()
            );

            if (name != null) {
                session.saveUser(name);
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Wrong login", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
