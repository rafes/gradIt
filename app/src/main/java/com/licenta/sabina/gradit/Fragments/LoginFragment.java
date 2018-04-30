package com.licenta.sabina.gradit.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.licenta.sabina.gradit.Listeners.OnLoginListener;
import com.licenta.sabina.gradit.R;

/**
 * Created by Sabina on 12/04/2018.
 */

public class LoginFragment extends Fragment implements OnLoginListener {


    Button log_in_button;
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_login, container, false);
        inflate.findViewById(R.id.forgot_password).setOnClickListener(v ->
                Toast.makeText(getContext(), "Forgot password clicked", Toast.LENGTH_SHORT).show());
        return inflate;
    }

    @Override
    public void login() {

        Toast.makeText(getContext(), "Login", Toast.LENGTH_SHORT).show();
    }
}
