package com.asrul.skripsi;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class AndroidTestUtils {
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final Context context = ApplicationProvider.getApplicationContext();

    public void checkLogin() {
        if (firebaseAuth.getCurrentUser() != null) {
            firebaseAuth.signOut();
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(context.getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();

            GoogleSignIn.getClient(context, gso).signOut();
        }
    }
}
