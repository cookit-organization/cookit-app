package com.example.cookit_app.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
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

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    EditText name_et, email_et, username_et, password_et, confirm_password_et;
    Button submit_signup, goToLogin;

    SharedPreferencesObject spo;

    private boolean validateEmailAddress(EditText email) {
        String emailInput = email.getText().toString();

        if (emailInput.isEmpty()) {
            email_et.setError("Field is empty");
            email_et.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email_et.setError("email isn't correct");
            email_et.requestFocus();
            return false;
        } else {
            return true;
        }
    }


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
        submit_signup = findViewById(R.id.submit_signup);
        goToLogin = findViewById(R.id.go_to_login);

        ProgressBar progressBar = findViewById(R.id.progress_bar);

        //TODO: make sure data is not empty, password == confirm_password and email with good format.

        submit_signup.setOnClickListener(view -> {

            progressBar.setVisibility(View.VISIBLE);

            String name = name_et.getText().toString();
            String email = email_et.getText().toString();
            String username = username_et.getText().toString();
            String password = password_et.getText().toString();
            String confirmPassword = confirm_password_et.getText().toString();
            boolean correct = true;

            if (name.trim().isEmpty()) {
                name_et.setError("Field is empty");
                name_et.requestFocus();
                correct = false;
            }

            if (!validateEmailAddress(email_et)) {
                correct = false;
            }
            if (username.trim().isEmpty()) {
                username_et.setError("Field is empty");
                username_et.requestFocus();
                correct = false;
            }
            if (password.trim().isEmpty()) {
                password_et.setError("Field is empty");
                password_et.requestFocus();
                correct = false;
            }
            if (confirmPassword.trim().isEmpty()) {
                confirm_password_et.setError("Field is empty");
                confirm_password_et.requestFocus();
                correct = false;
            } else if (!confirmPassword.equals(password)) {
                confirm_password_et.setError("Incompatible passwords");
                correct = false;
            }

            if (correct) {

                if (!username.isEmpty() && !password.isEmpty()) {

                    //        try {
                    //            RSA encryption username, password and email
                    //            String encryptedUsername = Base64.getEncoder().encodeToString(RSA.encrypt(username, /*TODO ADD A REAL KEY*/"publicKey"));
                    //            Log.d("tesTag", encryptedUsername);
                    //            String encryptedPassword = Base64.getEncoder().encodeToString(RSA.encrypt(password, /*TODO ADD A REAL KEY*/"publicKey"));
                    //            Log.d("tesTag", encryptedPassword);
                    //            String encryptedEmail = Base64.getEncoder().encodeToString(RSA.encrypt(email, /*TODO ADD A REAL KEY*/"publicKey"));
                    //            Log.d("tesTag", encryptedEmail);

                    //sending the request
                    HashMap<String, String> userData = new HashMap<>();

                    userData.put("name", name);
                    userData.put("email", email);
                    userData.put("username", username);
                    userData.put("password", password);

                    Call<Void> call = new Retrofit2Init().retrofitInterface.newUser(userData);

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            progressBar.setVisibility(View.GONE);
                            if (response.isSuccessful()) {
                                Toast.makeText(getBaseContext(), "Signed up successfully.", Toast.LENGTH_SHORT).show();
                                spo.getPreferences().edit().putBoolean(spo.isSignedUp, true).apply();
                                spo.getPreferences().edit().putString(spo.username, username).apply(); // remember to save without RSA
                            } else {
                                Toast.makeText(getBaseContext(), response.code() + "\n" + response.message(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

//        } catch (BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException | InvalidKeyException | NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
                }
            }
        });

        goToLogin.setOnClickListener(v -> {
            startActivity(new Intent(getBaseContext(), LoginActivity.class));
            finish();
        });


    }
}