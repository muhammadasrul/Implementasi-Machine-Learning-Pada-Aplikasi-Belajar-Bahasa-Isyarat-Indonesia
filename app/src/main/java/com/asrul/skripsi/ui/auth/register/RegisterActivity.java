package com.asrul.skripsi.ui.auth.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.asrul.skripsi.R;
import com.asrul.skripsi.ui.auth.login.LoginActivity;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText edtName, edtEmail, edtPassword1, edtPassword2;
    private RegisterViewModel viewModel;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        progressBar = findViewById(R.id.progressBar);
        edtName = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword1 = findViewById(R.id.edtPassword);
        edtPassword2 = findViewById(R.id.edtPassword2);
        Button btnToLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);

        btnToLogin.setOnClickListener(view -> {
            onBackPressed();
        });

        btnRegister.setOnClickListener(view -> {
            registerFlow();
        });

        observeRegisterStatus();
        observeIsLoading();
    }

    private void registerFlow() {
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

        viewModel.registerFlow(name, email, password);
    }

    private void observeRegisterStatus() {
        viewModel.registerStatus().observe(this, stringResponseState -> {
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

    private void observeIsLoading() {
        viewModel.isLoading().observe(this, isLoading -> {
            if (isLoading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}