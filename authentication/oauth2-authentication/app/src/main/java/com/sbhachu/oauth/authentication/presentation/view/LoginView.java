package com.sbhachu.oauth.authentication.presentation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.sbhachu.oauth.authentication.R;
import com.sbhachu.oauth.authentication.presentation.validator.EditTextValidator;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.view_login)
public class LoginView extends RelativeLayout {

    @Bean
    public EditTextValidator validator;

    @ViewById(R.id.lv_et_email_address)
    public EditText emailAddressEditText;

    @ViewById(R.id.lv_et_password)
    public EditText passwordEditText;

    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditText getEmailAddressEditText() {
        return emailAddressEditText;
    }

    public EditText getPasswordEditText() {
        return passwordEditText;
    }

    public boolean isLoginFormValid() {
        return (validator.isValidEmail(emailAddressEditText, true))
                && (validator.hasText(passwordEditText));
    }

    public void resetFieldsAndErrorState() {
        emailAddressEditText.setText("");
        emailAddressEditText.setError(null);

        passwordEditText.setText("");
        passwordEditText.setError(null);
    }
}
