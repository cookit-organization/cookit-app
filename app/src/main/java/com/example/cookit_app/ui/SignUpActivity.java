package com.example.cookit_app.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cookit_app.R;
import com.example.cookit_app.generalObjects.RSA;
import com.example.cookit_app.server.Retrofit2Init;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O) @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText name_et = findViewById(R.id.name);
        EditText email_et = findViewById(R.id.email);
        EditText username_et = findViewById(R.id.username);
        EditText password_et = findViewById(R.id.password);
        EditText confirm_password_et = findViewById(R.id.confirm_password);

        //TODO: make sure data is not empty, password == confirm_password and email with good format.

        String name = name_et.getText().toString();
        String email = email_et.getText().toString();
        String username = username_et.getText().toString();
        String password = password_et.getText().toString();

        try {
            //RSA encryption username, password and email
            String encryptedUsername = Base64.getEncoder().encodeToString(RSA.encrypt(username, /*TODO ADD A REAL KEY*/"publicKey"));
            Log.d("tesTag", encryptedUsername);
            String encryptedPassword = Base64.getEncoder().encodeToString(RSA.encrypt(password, /*TODO ADD A REAL KEY*/"publicKey"));
            Log.d("tesTag", encryptedPassword);
            String encryptedEmail = Base64.getEncoder().encodeToString(RSA.encrypt(email, /*TODO ADD A REAL KEY*/"publicKey"));
            Log.d("tesTag", encryptedEmail);

            //sending the request
            HashMap<String, String> userData = new HashMap<>();

            userData.put("name", name);
            userData.put("email", encryptedEmail);
            userData.put("username", encryptedUsername);
            userData.put("password", encryptedPassword);

            Call<Void> call = new Retrofit2Init().retrofitInterface.newUser(userData);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(getBaseContext(), "Signed up successfully.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getBaseContext(),response.code() + "\n" + response.message(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        } catch (BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException | InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}