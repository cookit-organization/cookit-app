package com.example.cookit_app.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cookit_app.R;
import com.example.cookit_app.backend.Retrofit2Init;
import com.example.cookit_app.utils.SharedPreferencesObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    final String fieldIsEmpty = "Field is Empty";
    EditText name_et, email_et, username_et, password_et, confirm_password_et;
    ProgressBar progressBar;
    SharedPreferencesObject spo;

    @Override
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        spo = new SharedPreferencesObject(this);

        name_et = findViewById(R.id.name);
        email_et = findViewById(R.id.email);
        username_et = findViewById(R.id.username);
        password_et = findViewById(R.id.password);
        confirm_password_et = findViewById(R.id.confirm_password);
        progressBar = findViewById(R.id.progress_bar);

        findViewById(R.id.submit_signup).setOnClickListener(view -> {

            progressBar.setVisibility(View.VISIBLE);

            String name = name_et.getText().toString();
            String email = email_et.getText().toString();
            String username = username_et.getText().toString();
            String password = password_et.getText().toString();
            String confirmPassword = confirm_password_et.getText().toString();

            boolean isValidInput = validateUserInput(name, username, password, confirmPassword);

            if (isValidInput) {
                HashMap<String, String> userData = new HashMap<>();
                userData.put("name", name);
                userData.put("email", email);
                userData.put("username", username);
                userData.put("password", password);
                sendRequest(userData);
            }
        });

        findViewById(R.id.go_to_login).setOnClickListener(v -> {
            startActivity(new Intent(getBaseContext(), LoginActivity.class));
            finish();
        });
    }

    private void sendRequest(HashMap<String, String> userData) {
        Call<Void> call = new Retrofit2Init().retrofitInterface.newUser(userData);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    makeToast("Signed up successfully.");
                    spo.getPreferences().edit().putBoolean(spo.isSignedUp, true).apply();
                    spo.getPreferences().edit().putString(
                            spo.username, username_et.getText().toString()).apply();
                } else {
                    makeToast(response.code() + "\n" + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                makeToast(t.getMessage());
            }
        });
    }

    private boolean validateUserInput(String name, String username,
                                      String password, String confirmPassword) {
        if (name.trim().isEmpty()) {
            name_et.setError(fieldIsEmpty);
            name_et.requestFocus();
            return false;
        } else if (!validateEmailAddress(email_et)) {
            return false;
        } else if (username.trim().isEmpty()) {
            username_et.setError(fieldIsEmpty);
            username_et.requestFocus();
            return false;
        } else if (password.trim().isEmpty()) {
            password_et.setError(fieldIsEmpty);
            password_et.requestFocus();
            return false;
        } else if (confirmPassword.trim().isEmpty()) {
            confirm_password_et.setError(fieldIsEmpty);
            confirm_password_et.requestFocus();
            return false;
        } else if (!confirmPassword.equals(password)) {
            confirm_password_et.setError("Incompatible Passwords");
            return false;
        }
        return true;
    }

    private boolean validateEmailAddress(EditText email) {
        String emailInput = email.getText().toString();
        if (emailInput.isEmpty()) {
            email_et.setError(fieldIsEmpty);
            email_et.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email_et.setError("Incorrect Email");
            email_et.requestFocus();
            return false;
        }
        return true;
    }

    private void makeToast(String text) {
        Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG).show();
    }

}