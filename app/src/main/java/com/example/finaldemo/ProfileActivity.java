package com.example.finaldemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    private TextView textViewUsername,textViewUserEmail;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
        finish();
        startActivity(new Intent(this,LoginActivity.class));

        }


        textViewUsername = (TextView)findViewById(R.id.textViewUsername);
        textViewUserEmail = (TextView)findViewById(R.id.textViewemail);


            textViewUserEmail.setText(SharedPrefManager.getInstance(this).getUserEmail());
            textViewUsername.setText(SharedPrefManager.getInstance(this).getUsername());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId()){
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.menuSettings:
                Toast.makeText(this,"You clicked setttings ",Toast.LENGTH_LONG).show();
                break;
        }
        return  true;
    }



}