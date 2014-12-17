package com.sbhachu.oauth.authentication.presentation.fragment;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;

import com.sbhachu.oauth.authentication.R;
import com.sbhachu.oauth.authentication.data.model.User;
import com.sbhachu.oauth.authentication.presentation.validator.EditTextValidator;
import com.sbhachu.oauth.authentication.presentation.view.LoginView;
import com.sbhachu.oauth.authentication.presentation.view.RegisterView;
import com.sbhachu.oauth.authentication.service.AuthorizationService;
import com.sbhachu.oauth.authentication.util.Logger;
import com.sbhachu.oauth.authentication.util.Md5EncoderUtil;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_access)
public class AccessFragment extends Fragment {

    private static final String TAG = AccessFragment.class.getSimpleName();

    @Bean
    public AuthorizationService service;

    @Bean
    public EditTextValidator validator;

    @ViewById(R.id.login_view)
    public LoginView loginView;

    @ViewById(R.id.register_view)
    public RegisterView registerView;

    @ViewById(R.id.btn_login)
    public Button loginButton;

    @ViewById(R.id.btn_register)
    public Button registerButton;

    private boolean registerMode = false;

    public static AccessFragment_ newInstance() {
        AccessFragment_ fragment = new AccessFragment_();
        return fragment;
    }

    public AccessFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void afterViews() {
        PasswordTransformationMethod passwordTransformationMethod = new PasswordTransformationMethod();
        loginView.getPasswordEditText().setTransformationMethod(passwordTransformationMethod);
        loginView.getPasswordEditText().setTypeface(Typeface.DEFAULT);
        registerView.getPasswordEditText().setTransformationMethod(passwordTransformationMethod);
        registerView.getPasswordEditText().setTypeface(Typeface.DEFAULT);
    }

    @Click(R.id.btn_register)
    public void registerClickHandler() {
        if (!registerMode) {
            registerMode = true;
            fadeInRegisterView();

        } else {
            registerMode = false;
            fadeInLoginView();
        }
    }

    @Click(R.id.btn_login)
    public void loginButtonClickHandler() {
        if (registerMode) {
            if (registerView.isRegistrationFormValid()) {
                User user = new User();
                user.setFirstName(registerView.getFirstNameEditText().getText().toString());
                user.setLastName(registerView.getLastNameEditText().getText().toString());
                user.setEmail(registerView.getEmailAddressEditText().getText().toString());
                user.setPassword(Md5EncoderUtil.encode(registerView.getPasswordEditText().getText().toString()));

                service.registerUser(user);
            }
        } else {
            if (loginView.isLoginFormValid()) {
                String email = loginView.getEmailAddressEditText().getText().toString();
                String password = Md5EncoderUtil.encode(loginView.getPasswordEditText().getText().toString());

                service.login(email, password);
            }
        }
    }

    /**
     * Cross-Fade In Registration Form View
     */
    private void fadeInRegisterView() {

        // reset fields and error state of outgoing view
        loginView.resetFieldsAndErrorState();

        registerView.setAlpha(0f);
        registerView.setVisibility(View.VISIBLE);

        registerView.animate().alpha(1f).setDuration(500).setListener(null);

        loginView.animate().alpha(0f).setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        loginButton.setText("Create Account");
                        registerButton.setText("Login");
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        loginView.setVisibility(View.GONE);
                    }
                });
    }

    /**
     * Cross-Fade In Login Form View
     */
    private void fadeInLoginView() {

        // reset fields and error state of outgoing view
        registerView.resetFieldsAndErrorState();

        loginView.setAlpha(0f);
        loginView.setVisibility(View.VISIBLE);

        loginView.animate().alpha(1f).setDuration(500).setListener(null);

        registerView.animate().alpha(0f).setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        loginButton.setText("Login");
                        registerButton.setText("Create Account");
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        registerView.setVisibility(View.GONE);
                    }
                });
    }


}
