package com.asrul.skripsi.ui.auth.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.asrul.skripsi.utils.ResponseState;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterViewModel extends ViewModel {
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private MutableLiveData<ResponseState<String>> registerStatus;
    public LiveData<ResponseState<String>> registerStatus() {
        if (registerStatus == null) {
            registerStatus = new MutableLiveData<>();
        }
        return registerStatus;
    }

    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    public void registerFlow(String name, String email, String password) {
        isLoading.postValue(true);
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .build();

                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null) {
                            user.updateProfile(request);
                        }
                        firebaseAuth.signOut();

                        registerStatus.postValue(new ResponseState<String>().success("Register berhasil, silahkan Login dengan email!"));
                    } else {
                        registerStatus.postValue(new ResponseState<String>().failure(task.getException().getMessage()));
                    }
                    isLoading.postValue(false);
                });
    }
}
