package com.example.cookit_app.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cookit_app.R;
import com.example.cookit_app.generalObjects.RSA;
import com.example.cookit_app.server.Retrofit2Init;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O) @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username_et = findViewById(R.id.username);
        EditText password_et = findViewById(R.id.password);
        Button submit_login = findViewById(R.id.submit_login);

        submit_login.setOnClickListener(view -> {

            String username = username_et.getText().toString();
            String password = password_et.getText().toString();

            if(!username.isEmpty() && !password.isEmpty()){

                //encrypting username and password with RSA
//                try{
//                    String encryptedUsername = Base64.getEncoder().encodeToString(RSA.encrypt(username, /*TODO ADD A REAL KEY*/"publicKey"));
//                    Log.d("tesTag", encryptedUsername);
//                    String encryptedPassword = Base64.getEncoder().encodeToString(RSA.encrypt(username, /*TODO ADD A REAL KEY*/"publicKey"));
//                    Log.d("tesTag", encryptedPassword);

                    //sending the request
                    Call<Void> call = new Retrofit2Init().retrofitInterface.logInUser(username, password); //change back to encrypted after testing
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.isSuccessful()){
                                startActivity(new Intent(getBaseContext(), MainActivity.class));
                            }else if (response.code() == 403){
                                Toast.makeText(getBaseContext(), "Incorrect username or password.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

//                } catch (NoSuchAlgorithmException e) {
//                    System.err.println(e.getMessage());
//                } catch (NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
//                    e.printStackTrace();
//                }

            }else if(username.isEmpty()){
                username_et.setError("Field is empty");
            }else {
                password_et.setError("Field is empty");
            }
        });

    }
}