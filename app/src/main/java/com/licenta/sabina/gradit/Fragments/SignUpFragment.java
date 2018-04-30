package com.licenta.sabina.gradit.Fragments;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.licenta.sabina.gradit.Listeners.OnSignUpListener;
import com.licenta.sabina.gradit.MainActivity;
import com.licenta.sabina.gradit.R;



/**
 * Created by Sabina on 12/04/2018.
 */

public class SignUpFragment extends Fragment implements OnSignUpListener {
    EditText input_password, input_check_password, input_email;
    //Button sign_up_button;
    TextView errorMessage;
    private FirebaseAuth mAuth;
    Button sign_up_button;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_signup, container, false);
        //inflate.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        sign_up_button = inflate.findViewById(R.id.sign_up_button);
        input_password = inflate.findViewById(R.id.input_password_sign_up);
        input_check_password = inflate.findViewById(R.id.input_password_confirm_sign_up);
        input_email = inflate.findViewById(R.id.input_email_sign_up);
        errorMessage = inflate.findViewById(R.id.invalid_message_2);
        mAuth = FirebaseAuth.getInstance();
        setupListeners(inflate);
        return inflate;
    }

    void setupListeners(View view) {
        sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
        input_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        input_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorMessage.setVisibility(View.INVISIBLE);

            }
        });

    }

    void emailIsValid() {
        String email = input_email.getText().toString();
        if (TextUtils.isEmpty(email)) {
            sign_up_button.setEnabled(false);
        }


        //check if email is valid (scs)
        //check if email is correct
        //check if email is in database
    }

    @Override
    public void signUp() {
        //validateEmail();
        //validatePassword();
        String email = input_email.getText().toString();
        String password = input_password.getText().toString();
        String check_password = input_check_password.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            errorMessage.setVisibility(View.VISIBLE);
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                    if (!task.isSuccessful()) {
                        errorMessage.setVisibility(View.VISIBLE);
                        Toast.makeText(getContext(), task.getException().toString() + " " + task.getResult().toString(), Toast.LENGTH_SHORT).show();


                    }
                }
            });
            //
        }
    }

}
