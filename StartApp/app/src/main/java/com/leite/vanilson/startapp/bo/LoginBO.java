package com.leite.vanilson.startapp.bo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;

import com.leite.vanilson.startapp.LoginActivity;
import com.leite.vanilson.startapp.repository.LoginRepository;
import com.leite.vanilson.startapp.util.Util;
import com.leite.vanilson.startapp.validation.ValidationLogin;

/**
 * Created by vanilson on 07/06/17.
 */

public class LoginBO {

    private LoginRepository loginRepository;

    public LoginBO(Activity activity) {
        loginRepository = new LoginRepository(activity);
        loginRepository.listLogins(activity);
    }

    public boolean validateFields(ValidationLogin validationLogin){
        boolean result = true;
        if(validationLogin.getLogin() == null || validationLogin.getLogin().equals("")){
            validationLogin.getEdtLogin().setError("Campo Obrigatório!");
            result = false;
        }
        if(validationLogin.getPassword() == null || validationLogin.getPassword().equals("")) {
            validationLogin.getEdtPassword().setError("Campo Obrigatório!");
            result = false;
        }
        if(!validationLogin.getLogin().equals("a") || !validationLogin.getPassword().equals("a")) {
            Util.showMsgToast(validationLogin.getActivity(), "Login ou Senha inválidas!");
            result = false;
        }
        if(result) {
            loginRepository.deleteLogin(validationLogin.getLogin(), validationLogin.getPassword());
            SharedPreferences.Editor editor = validationLogin.getActivity().getSharedPreferences("pref",Context.MODE_PRIVATE).edit();
            editor.putString("login", validationLogin.getLogin());
            editor.putString("password", validationLogin.getPassword());
            editor.commit();
        }
        return result;
    }
}
