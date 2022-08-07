package com.github.ancamulus.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginActivityViewModel extends ViewModel {

    MutableLiveData<String> username = new MutableLiveData<>("");
    MutableLiveData<String> password = new MutableLiveData<>("");
    MutableLiveData<Boolean> usernameValid  = new MutableLiveData<>(false);
    MutableLiveData<Boolean> passwordValid = new MutableLiveData<>(false);
    MutableLiveData<Boolean> loginButtonEnabled = new MutableLiveData<>(false);

    public void setUsername(String s) {
        username.postValue(s);
        //TODO: proper validation
        boolean isNewValueValid = (s.length() > 3);
        usernameValid.postValue(isNewValueValid);
        //Enable login button only when username AND password are valid
        loginButtonEnabled.postValue(isNewValueValid && (passwordValid.getValue() != null ? passwordValid.getValue() : false));
    }

    public void setPassword(String s) {
        password.postValue(s);
        //TODO: proper validation
        boolean isNewValueValid = (s.length() > 3);
        passwordValid.postValue(isNewValueValid);
        //Enable login button only when username AND password are valid
        loginButtonEnabled.postValue(isNewValueValid && (usernameValid.getValue() != null ? usernameValid.getValue() : false));
    }

    public LiveData<Boolean> isLoginButtonEnabled() {
        return loginButtonEnabled;
    }
}
