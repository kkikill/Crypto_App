package com.example.cryptoapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cryptoapp.R;
import com.example.cryptoapp.database.UserRepository;
import com.example.cryptoapp.session.SessionManager;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText name = findViewById(R.id.nameInput);
        EditText login = findViewById(R.id.loginInput);
        EditText pass = findViewById(R.id.passInput);

        findViewById(R.id.registerBtn).setOnClickListener(v -> {

            UserRepository repo = new UserRepository(this);

            boolean ok = repo.register(
                    name.getText().toString(),
                    login.getText().toString(),
                    pass.getText().toString()
            );

            if (ok) {
                SessionManager session = new SessionManager(this);
                session.saveUser(name.getText().toString());

                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "User exists", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
