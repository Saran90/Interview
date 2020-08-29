package com.application.boilerplate.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Patterns;
import com.application.boilerplate.R;

public class CommonUtils {

    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    /**
     * Email Validation.
     * @param email
     * @return True if valid email, else False
     */
    public boolean isValidEmail(String email){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.equalsIgnoreCase(null) || email.equalsIgnoreCase("")){
            return false;
        }else {
            if (email.matches(emailPattern)){
                return true;
            }else {
                return false;
            }
        }
    }

    /**
     * Password Validation. Minimum length of the password should be 6
     * @param password
     * @return True if valid else False
     */
    public boolean isValidPassword(String password){
        if (password.equalsIgnoreCase(null) || password.equalsIgnoreCase("")){
            return false;
        }else {
            if (password.length()>5){
                return true;
            }else {
                return false;
            }
        }
    }
}
