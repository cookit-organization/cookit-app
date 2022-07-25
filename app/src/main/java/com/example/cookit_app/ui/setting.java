package com.example.cookit_app.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cookit_app.R;
import com.example.cookit_app.generalObjects.MultiSpinner;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import java.util.ArrayList;
import java.util.List;

public class setting extends AppCompatActivity {

    MultiSpinner tags, favorite_categories;
    MaterialButton logOut, deleteAccount, changePassword;
    ShapeableImageView imageProfile;
    TextView name, bio;
    AlertDialog dialog;
    AlertDialog.Builder builder;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        tags = findViewById(R.id.tags);
        favorite_categories = findViewById(R.id.favorite_categories);
        logOut = findViewById(R.id.log_out);
        deleteAccount = findViewById(R.id.delete_account);
        imageProfile = findViewById(R.id.profile_image);
        name = findViewById(R.id.profile_name_tv);
        bio = findViewById(R.id.profile_bio_tv);
        changePassword = findViewById(R.id.change_password);

        createSpinners();

        logOut.setOnClickListener(V -> {
            builder = new AlertDialog.Builder(setting.this);

            LinearLayout layout = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(params);

            layout.setGravity(Gravity.CLIP_VERTICAL);
            layout.setPadding(2, 2, 2, 2);

            TextView tv1 = new TextView(this);
            tv1.setText("Are you sure you want log out?");
            tv1.setTextColor(Color.RED);
            tv1.setTextSize(20);
            tv1.setPadding(40, 40, 40, 40);
            tv1.setGravity(Gravity.CENTER);

            LinearLayout.LayoutParams tv1Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            tv1Params.bottomMargin = 5;
            layout.addView(tv1,tv1Params);

            builder.setView(layout);

            builder.setPositiveButton("yes", (dialogInterface, i) -> {
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                finishAffinity();
            });

            builder.setNegativeButton("no", (dialogInterface, i) -> {

            });

            dialog = builder.create();
            dialog.show();
        });

        deleteAccount.setOnClickListener(V -> {
            builder = new AlertDialog.Builder(setting.this);

            LinearLayout layout = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(params);

            layout.setGravity(Gravity.CLIP_VERTICAL);
            layout.setPadding(2, 2, 2, 2);

            TextView tv1 = new TextView(this);
            tv1.setText("Enter your password");
            tv1.setTextColor(Color.RED);
            tv1.setTextSize(20);
            tv1.setPadding(40, 40, 40, 40);
            tv1.setGravity(Gravity.CENTER);

            EditText et = new EditText(this);

            LinearLayout.LayoutParams tv1Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            tv1Params.bottomMargin = 5;
            layout.addView(tv1,tv1Params);
            layout.addView(et, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            builder.setView(layout);

            builder.setPositiveButton("yes", (dialogInterface, i) -> {
                if (et.getText().toString().equals("1234")){
                    //delete account
                    startActivity(new Intent(getBaseContext(), SignUpActivity.class));
                    finishAffinity();
                }else{
                    Toast.makeText(setting.this, "wrong password" , Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("no", (dialogInterface, i) -> {

            });

            dialog = builder.create();
            dialog.show();
        });

        imageProfile.setOnClickListener(v->{
            Toast.makeText(this, "profile", Toast.LENGTH_SHORT).show();
        });

        name.setOnClickListener(v->{
            builder = new AlertDialog.Builder(setting.this);

            LinearLayout layout = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(params);

            layout.setGravity(Gravity.CLIP_VERTICAL);
            layout.setPadding(2, 2, 2, 2);

            TextView tv1 = new TextView(this);
            tv1.setText("Change your name");
            tv1.setTextSize(20);
            tv1.setPadding(40, 40, 40, 40);
            tv1.setGravity(Gravity.CENTER);

            EditText et = new EditText(this);
            et.setText(name.getText().toString());

            LinearLayout.LayoutParams tv1Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            tv1Params.bottomMargin = 5;
            layout.addView(tv1,tv1Params);
            layout.addView(et, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            builder.setView(layout);

            builder.setPositiveButton("yes", (dialogInterface, i) -> name.setText(et.getText().toString()));

            builder.setNegativeButton("no", (dialogInterface, i) -> {

            });

            dialog = builder.create();
            dialog.show();
        });

        bio.setOnClickListener(v->{
            builder = new AlertDialog.Builder(setting.this);

            LinearLayout layout = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(params);

            layout.setGravity(Gravity.CLIP_VERTICAL);
            layout.setPadding(2, 2, 2, 2);

            TextView tv1 = new TextView(this);
            tv1.setText("Change your bio");
            tv1.setTextSize(20);
            tv1.setPadding(40, 40, 40, 40);
            tv1.setGravity(Gravity.CENTER);

            EditText et = new EditText(this);
            et.setText(bio.getText().toString());

            LinearLayout.LayoutParams tv1Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            tv1Params.bottomMargin = 5;
            layout.addView(tv1,tv1Params);
            layout.addView(et, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            builder.setView(layout);

            builder.setPositiveButton("yes", (dialogInterface, i) -> bio.setText(et.getText().toString()));

            builder.setNegativeButton("no", (dialogInterface, i) -> {

            });

            dialog = builder.create();
            dialog.show();
        });

        changePassword.setOnClickListener(v->{
            builder = new AlertDialog.Builder(setting.this);

            LinearLayout layout = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(params);

            layout.setGravity(Gravity.CLIP_VERTICAL);
            layout.setPadding(2, 2, 2, 2);

            TextView tv1 = new TextView(this);
            tv1.setText("Change your password");
            tv1.setTextSize(20);
            tv1.setPadding(40, 40, 40, 40);
            tv1.setGravity(Gravity.CENTER);

            LinearLayout currentPassword = new LinearLayout(this);
            TextView currentPassword2 = new TextView(this);
            currentPassword2.setText("current password: ");
            EditText currentPassword1 = new EditText(this);
            currentPassword.addView(currentPassword2, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            currentPassword.addView(currentPassword1, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            LinearLayout newPassword = new LinearLayout(this);
            TextView newPassword2 = new TextView(this);
            newPassword2.setText("new password: ");
            EditText newPassword1 = new EditText(this);
            newPassword.addView(newPassword2, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            newPassword.addView(newPassword1, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            LinearLayout.LayoutParams tv1Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            tv1Params.bottomMargin = 5;
            layout.addView(tv1,tv1Params);
            layout.addView(currentPassword, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            layout.addView(newPassword, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));


            builder.setView(layout);

            builder.setPositiveButton("yes", (dialogInterface, i) -> {
                //check if this current password
                if (currentPassword1.getText().toString().equals("1234")){
                    //change password
                    Toast.makeText(this, "password change to "+ newPassword1.getText().toString(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(setting.this, "wrong password" , Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("no", (dialogInterface, i) -> {

            });

            dialog = builder.create();
            dialog.show();
        });

    }

    private void createSpinners(){

        List<String> listTags = new ArrayList<>();
        listTags.add("meat");
        listTags.add("soup");
        listTags.add("fish");

        List<String> listFavoriteCategories = new ArrayList<>();
        listFavoriteCategories.add("Morning");
        listFavoriteCategories.add("Afternoon");
        listFavoriteCategories.add("Night");

        tags.setItems(listTags, "disable tags", selected -> {});
        favorite_categories.setItems(listFavoriteCategories, "favorite categories", selected -> {});
    }
}
