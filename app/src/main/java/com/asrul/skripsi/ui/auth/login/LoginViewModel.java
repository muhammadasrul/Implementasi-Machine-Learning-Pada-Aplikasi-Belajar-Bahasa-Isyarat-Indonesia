package com.asrul.skripsi.ui.auth.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.asrul.skripsi.utils.ResponseState;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginViewModel extends ViewModel {
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private MutableLiveData<Boolean> isLoggedIn;
    public LiveData<Boolean> isLoggedIn() {
        if (isLoggedIn == null) {
            isLoggedIn = new MutableLiveData<>();
            isLoggedIn.postValue(firebaseAuth.getCurrentUser() != null);
        }
        return isLoggedIn;
    }

    private MutableLiveData<ResponseState<String>> loginWithEmailStatus;
    public LiveData<ResponseState<String>> loginWithEmail(String email, String password) {
        if (loginWithEmailStatus == null) {
            loginWithEmailStatus = new MutableLiveData<>();
            loginWithEmailFlow(email, password);
        }
        return loginWithEmailStatus;
    }

    private MutableLiveData<ResponseState<String>> loginWithGoogleStatus;
    public LiveData<ResponseState<String>> loginWithGoogle(String idToken) {
        if (loginWithGoogleStatus == null) {
            loginWithGoogleStatus = new MutableLiveData<>();
            loginWithGoogleFlow(idToken);
        }
        return loginWithGoogleStatus;
    }

    private void loginWithEmailFlow(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        loginWithEmailStatus.postValue(new ResponseState<String>().success("Login success"));
                    } else {
                        loginWithEmailStatus.postValue(new ResponseState<String>().failure("Login Failed: " + task.getException()));
                    }
                });
    }

    private void loginWithGoogleFlow(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        loginWithGoogleStatus.postValue(new ResponseState<String>().success("Login with Google success"));
                    } else {
                        loginWithGoogleStatus.postValue(new ResponseState<String>().failure("Login with Google failed"));
                    }
                });
    }
}
