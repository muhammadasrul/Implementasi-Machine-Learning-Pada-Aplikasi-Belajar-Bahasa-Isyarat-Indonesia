package com.asrul.skripsi.ui.auth.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.asrul.skripsi.utils.EspressoIdlingResource;
import com.asrul.skripsi.utils.ResponseState;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;

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

    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    private MutableLiveData<ResponseState<String>> loginStatus;

    public LiveData<ResponseState<String>> loginStatus() {
        if (loginStatus == null) {
            loginStatus = new MutableLiveData<>();
        }
        return loginStatus;
    }

    public void loginWithEmailFlow(String email, String password) {
        isLoading.postValue(true);
        EspressoIdlingResource.increment();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        loginStatus.postValue(new ResponseState<String>().success("Login success"));
                    } else {
                        loginStatus.postValue(new ResponseState<String>().failure("Login Failed: " + task.getException()));
                    }
                    EspressoIdlingResource.decrement();
                    isLoading.postValue(false);
                });
    }

    public void loginWithGoogleFlow(AuthCredential credential) {
        isLoading.postValue(true);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        loginStatus.postValue(new ResponseState<String>().success("Login with Google success"));
                    } else {
                        loginStatus.postValue(new ResponseState<String>().failure("Login with Google failed"));
                    }
                    isLoading.postValue(false);
                });
    }
}
