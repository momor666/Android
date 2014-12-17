package com.sbhachu.oauth.authentication.presentation.validator;

import android.widget.EditText;

import org.androidannotations.annotations.EBean;

import java.util.regex.Pattern;

import static org.androidannotations.annotations.EBean.Scope;

/**
 * Created by sbhachu on 15/12/2014.
 */
@EBean(scope = Scope.Singleton)
public class EditTextValidator {

    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final String REQUIRED_ERROR = "required";
    private static final String EMAIL_ERROR = "invalid email";

    public boolean isValidEmail(EditText editText, boolean required) {
        editText.setError(null);

        String text = editText.getText().toString().trim();

        if (required && !hasText(editText)) return false;

        if (required && !Pattern.matches(EMAIL_REGEX, text)) {
            editText.setError(EMAIL_ERROR);
            return false;
        }

        return true;
    }

    public boolean hasText(EditText editText) {
        editText.setError(null);

        String text = editText.getText().toString().trim();

        if (text.length() == 0) {
            editText.setError(REQUIRED_ERROR);
            return false;
        }

        return true;
    }
}
