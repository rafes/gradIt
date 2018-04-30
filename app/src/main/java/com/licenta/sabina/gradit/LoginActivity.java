package com.licenta.sabina.gradit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import com.licenta.sabina.gradit.Views.FlexibleFrameLayout;
import com.licenta.sabina.gradit.login.LoginButton;
import com.licenta.sabina.gradit.Fragments.LoginFragment;
import com.licenta.sabina.gradit.Fragments.SignUpFragment;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.licenta.sabina.gradit.Views.FlexibleFrameLayout.ORDER_LOGIN_STATE;
import static com.licenta.sabina.gradit.Views.FlexibleFrameLayout.ORDER_SIGN_UP_STATE;


public class LoginActivity extends AppCompatActivity {

    private boolean isLogin = true;
    LoginFragment topLoginFragment = new LoginFragment();
    SignUpFragment topSignUpFragment = new SignUpFragment();
    FrameLayout loginFragment;
    FrameLayout signUpFragment;
    FlexibleFrameLayout wrapper;
    LoginButton button;
    //FragmentManager fm = this.getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.login_fragment, topLoginFragment)
                .replace(R.id.sign_up_fragment, topSignUpFragment)
                .commit();
        loginFragment=findViewById(R.id.login_fragment);
        signUpFragment=findViewById(R.id.sign_up_fragment);
        loginFragment.setRotation(-90);
        button=findViewById(R.id.button);
        button.setOnSignUpListener(topSignUpFragment);
        button.setOnLoginListener(topLoginFragment);
        wrapper=findViewById(R.id.wrapper);
        ViewGroup mRootView = ((ViewGroup)findViewById(R.id.myroot));
        button.setOnButtonSwitched(isLogin -> {
            mRootView
                    .setBackgroundColor(ContextCompat.getColor(
                            this,
                            isLogin ? R.color.colorPrimary : R.color.secondPage));
        });

        loginFragment.setVisibility(INVISIBLE);


    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        loginFragment.setPivotX(loginFragment.getWidth() / 2);
        loginFragment.setPivotY(loginFragment.getHeight());
        signUpFragment.setPivotX(signUpFragment.getWidth() / 2);
        signUpFragment.setPivotY(signUpFragment.getHeight());
    }

    public void switchFragment(View v) {
        if (isLogin) {
            loginFragment.setVisibility(VISIBLE);
            loginFragment.animate().rotation(0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    signUpFragment.setVisibility(INVISIBLE);
                    signUpFragment.setRotation(90);
                    wrapper.setDrawOrder(ORDER_LOGIN_STATE);
                }
            });
        } else {
            signUpFragment.setVisibility(VISIBLE);
            signUpFragment.animate().rotation(0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    loginFragment.setVisibility(INVISIBLE);
                    loginFragment.setRotation(-90);
                    wrapper.setDrawOrder(ORDER_SIGN_UP_STATE);
                }
            });
        }

        isLogin = !isLogin;
        button.startAnimation();
    }
}
