package com.asrul.skripsi.ui.auth.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.asrul.skripsi.R;
import com.asrul.skripsi.ui.auth.login.LoginActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText edtName, edtEmail, edtPassword1, edtPassword2;
    private Button btnToLogin, btnRegister;
    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        edtName = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword1 = findViewById(R.id.edtPassword);
        edtPassword2 = findViewById(R.id.edtPassword2);
        btnToLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnToLogin.setOnClickListener(view -> {
            onBackPressed();
        });

        btnRegister.setOnClickListener(view -> {

            String name = edtName.getText().toString();
            String email = edtEmail.getText().toString();
            String password = edtPassword1.getText().toString();
            String password2 = edtPassword2.getText().toString();

            if (name.isEmpty()) {
                edtName.setError("Tidak boleh kosong");
                edtName.requestFocus();
                return;
            }

            if (email.isEmpty()) {
                edtEmail.setError("Tidak boleh kosong");
                edtEmail.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                edtPassword1.setError("Tidak boleh kosong");
                edtPassword1.requestFocus();
                return;
            }

            if (!(password2.equals(password))) {
                edtPassword2.setError("Password tidak sama");
                edtPassword2.requestFocus();
                return;
            }

            registerFlow(name, email, password);
        });
    }

    private void registerFlow(String name, String email, String password) {
        viewModel.registerWithEmail(name, email, password).observe(this, stringResponseState -> {
            switch (stringResponseState.getStatus()) {
                case SUCCESS:
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                    break;
                case FAILURE:
                    Toast.makeText(RegisterActivity.this, stringResponseState.getMessage(), Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }
}