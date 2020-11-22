package com.example.finaldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUsername, editTextEmail,editTextPassword;

    private Button buttonRegister;
    public String stringEmail;
    public String stringUsername;

    private ProgressDialog progressDialog;
    private Object JsonObjectRequest;
    private TextView textViewlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,ProfileActivity.class));
            return;


        }


        editTextEmail = (EditText) findViewById(R.id.email);
        editTextUsername = (EditText)findViewById(R.id.username);
        editTextPassword = (EditText)findViewById(R.id.password);

        textViewlogin = (TextView)findViewById(R.id.textviewLogin);

        buttonRegister = (Button)findViewById(R.id.button);


        progressDialog = new ProgressDialog(this);

        buttonRegister.setOnClickListener(this);

            textViewlogin.setOnClickListener(this);

    }

    private void registerUser(){

final String email= editTextEmail.getText().toString().trim();
final String username = editTextUsername.getText().toString().trim();
final String password = editTextPassword.getText().toString().trim();
stringEmail=email;
if(email.length() == 0)
{
    editTextEmail.setError("ENTER EMAIL ID");
    return;

}

        stringUsername=username;
        if(username.length() == 0)
        {
            editTextUsername.setError("ENTER USER NAME");
            return;

        }


progressDialog.setMessage("Registering user...");
progressDialog.show();


       StringRequest stringRequest = new StringRequest(
               Request.Method.POST,
               Constants.URL_REGISTER,
               new Response.Listener<String>()
               {
                   @Override
                   public void onResponse(String response) {
                       progressDialog.dismiss();

                       try {

                           JSONObject jsonObject = new JSONObject(response);
                           Toast.makeText(getApplicationContext(), jsonObject.getString(("message")),Toast.LENGTH_LONG).show();
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                   }

                   /*@Override
                   public void onResponse(String response) {

                        progressDialog.dismiss();

                        try {
                                JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString(("message")),Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                },*/
               },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        progressDialog.hide();
                        makeText(getApplicationContext(),error.getMessage(), LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
          Map<String,String> params = new HashMap<>();

          params.put("username",username);
          params.put("email",email);
          params.put("password",password);

          return params;
            }
        };


        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);


/*
RequestQueue.add(JsonObjectRequest);
*/

    }


    @Override
    public void onClick(View v) {
       if ( v == buttonRegister){
           registerUser();

       }
       if( v == textViewlogin){
          startActivity(new Intent(this,LoginActivity.class));

       }



    }
}