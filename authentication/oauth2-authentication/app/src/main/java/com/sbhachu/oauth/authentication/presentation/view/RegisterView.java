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

/**
 * Created by sbhachu on 13/12/2014.
 */
@EViewGroup(R.layout.view_register)
public class RegisterView extends RelativeLayout {

    @Bean
    public EditTextValidator validator;

    @ViewById(R.id.rv_et_first_name)
    public EditText firstNameEditText;

    @ViewById(R.id.rv_et_last_name)
    public EditText lastNameEditText;

    @ViewById(R.id.rv_et_email_address)
    public EditText emailAddressEditText;

    @ViewById(R.id.rv_et_password)
    public EditText passwordEditText;

    public RegisterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditText getFirstNameEditText() {
        return firstNameEditText;
    }

    public EditText getLastNameEditText() {
        return lastNameEditText;
    }

    public EditText getEmailAddressEditText() {
        return emailAddressEditText;
    }

    public EditText getPasswordEditText() {
        return passwordEditText;
    }

    public boolean isRegistrationFormValid() {
        return (validator.hasText(firstNameEditText))
                && (validator.hasText(lastNameEditText))
                && (validator.isValidEmail(emailAddressEditText, true))
                && (validator.hasText(passwordEditText));
    }

    public void resetFieldsAndErrorState() {
        firstNameEditText.setText("");
        firstNameEditText.setError(null);

        lastNameEditText.setText("");
        lastNameEditText.setError(null);

        emailAddressEditText.setText("");
        emailAddressEditText.setError(null);

        passwordEditText.setText("");
        passwordEditText.setError(null);
    }
}
