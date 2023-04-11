package com.example.cookit_app.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cookit_app.R;
import com.example.cookit_app.backend.Retrofit2Init;
import com.example.cookit_app.utils.SharedPreferencesObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.net.HttpURLConnection.HTTP_FORBIDDEN;

public class LoginActivity extends AppCompatActivity {

    EditText username_et, password_et;
    final String fieldIsEmpty = "Field is Empty";
    SharedPreferencesObject spo;
    ProgressBar progressBar;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        spo = new SharedPreferencesObject(this);

        username_et = findViewById(R.id.username);
        password_et = findViewById(R.id.password);
        Button submit_login = findViewById(R.id.submit_login);
        Button goToSignUp = findViewById(R.id.go_to_sign_up);
        progressBar = findViewById(R.id.progress_bar);

        submit_login.setOnClickListener(view -> {

            progressBar.setVisibility(View.VISIBLE);

            String username = username_et.getText().toString();
            String password = password_et.getText().toString();

            boolean isValidInput = validateUserInput(username, password);

            if (isValidInput) {
                sendRequest(username, password);
            }
        });

        goToSignUp.setOnClickListener(v -> {
            startActivity(new Intent(getBaseContext(), SignUpActivity.class));
            finish();
        });
    }

    private void sendRequest(String username, String password) {
        new Retrofit2Init().retrofitInterface.logInUser(username, password)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        progressBar.setVisibility(View.GONE);
                        if (response.isSuccessful()) {
                            spo.getPreferences().edit().putString(spo.username, username).apply();
                            startActivity(new Intent(getBaseContext(), MainActivity.class));
                        } else if (response.code() == HTTP_FORBIDDEN) {
                            makeToast("Incorrect username or password.");
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        makeToast(t.getMessage());
                    }
                });
    }

    private boolean validateUserInput(String username, String password) {
        if (username.trim().isEmpty()) {
            username_et.setError(fieldIsEmpty);
            return false;
        } else if (password.trim().isEmpty()) {
            password_et.setError(fieldIsEmpty);
            return false;
        }
        return true;
    }

    private void makeToast(String text) {
        Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG).show();
    }
}