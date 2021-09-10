package com.asrul.skripsi.ui.auth.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.asrul.skripsi.R;
import com.asrul.skripsi.ui.MainActivity;
import com.asrul.skripsi.ui.auth.register.RegisterActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    Button btnToRegister, btnLogin, btnLoginWithGoogle;
    private LoginViewModel viewModel;
    private TextInputEditText edtEmail, edtPassword;

    private static final int SIGN_IN_RESULT_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        btnToRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
        btnLoginWithGoogle = findViewById(R.id.btnLoginWithGoogle);

        isLoggedIn();

        btnLogin.setOnClickListener(view -> {

            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();

            if (email.isEmpty()) {
                edtEmail.setError("Tidak boleh kosong");
                edtEmail.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                edtPassword.setError("Tidak boleh kosong");
                edtPassword.requestFocus();
                return;
            }

            loginWithEmailFlow(email, password);
        });

        btnLoginWithGoogle.setOnClickListener(view -> {
            loginWithGoogleFlow();
        });

        btnToRegister.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }

    private void isLoggedIn() {
        viewModel.isLoggedIn().observe(this, aBoolean -> {
            if (aBoolean) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void loginWithEmailFlow(String email, String password) {
        viewModel.loginWithEmail(email, password).observe(this, stringResponseState -> {
            switch (stringResponseState.getStatus()) {
                case SUCCESS:
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                    break;
                case FAILURE:
                    Toast.makeText(LoginActivity.this, stringResponseState.getMessage(), Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    private void loginWithGoogleFlow() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        GoogleSignInClient client = GoogleSignIn.getClient(this, gso);

        Intent signInIntent = client.getSignInIntent();
        startActivityForResult(signInIntent, SIGN_IN_RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_RESULT_CODE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    observeLoginWithGoogle(account);
                }
            } catch (ApiException e) {
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void observeLoginWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        viewModel.loginWithGoogle(credential).observe(this, stringResponseState -> {
            switch (stringResponseState.getStatus()) {
                case SUCCESS:
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                    break;
                case FAILURE:
                    Toast.makeText(LoginActivity.this, stringResponseState.getMessage(), Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }
}