package com.example.vlisn.m4;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.vlisn.m4.R.id.username;

/**
 * Created by vlisn on 9/24/2017.
 */

public class Register extends AppCompatActivity implements View.OnClickListener {
    AppCompatActivity activity = Register.this;
    User user;
    DatabaseHelper dbHelper;
    Button bRegister;
    EditText etName,etUsername,etPassword;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.name);
        etUsername = (EditText) findViewById(R.id.newUsername);
        etPassword = (EditText) findViewById(R.id.newPassword);
        bRegister = (Button) findViewById(R.id.buttonCreate);
        dbHelper = new DatabaseHelper(activity);
        user = new User();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCreate:
                if (etUsername.getText().toString().equals("")
                        || etPassword.getText().toString().equals("")) {
                    //error
                    Context context = getApplicationContext();
                    CharSequence text = "No empty usernames or passwords, please!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else if (!dbHelper.checkUser(etUsername.getText().toString().trim(),
                        etPassword.getText().toString().trim())) {
                    user.setUserName(etUsername.getText().toString().trim());
                    user.setActualName(etName.getText().toString());
                    user.setPassword(etPassword.getText().toString().trim());

                    dbHelper.addUser(user);
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "This username is taken, sorry!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                break;
            case R.id.cancelButton:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    public void displayMain(View view) {
        Intent intent = new Intent(Register.this, MainActivity.class);
        startActivity(intent);
    }
/*
        bRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sumbitForm();
            }
        });
    }
    private void submitForm() {
        registerUser(etName.getText().toString(), username.getText().toString(), 
            etPassword.getText.toString());
    }
    private void registerUser(final String name) {
        //Tag used to cancel the request
        String cancel_req_tag = "register";
        progressDialog.setMessage("Adding you ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST , new Response.Listener<String>());
    
        public void onResponse(String response) {
            Log.d(TAG, "Register Response: " + response.toString());
            hideDialog();
            try {
                JSONObject jObject = new JSONObject(response);
                boolean error = jObject.getBoolean("error");
                if (! error) {
                    String user = jObject.getJSONObject("user").getString("name");
                    Toast.makeText(getApplicationContext(), "Hi " + user + ", You are successfully Added!", Toast.LENGTH_SHORT).show();

                    //Launch login activity
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    String errorMessage = jObject.getString("error_msg");
                    Toast.makeText(getApplicationContext()), errorMessage, Toast.LENGTH_LONG).show();
                }
            } catch (JSONException exception) {
                exception.printStackTrace();
            }
        }
    }, newResponse.ErrorListener() {
        public void onErrorResponse(VolleyError error) {
            Log.e(TAG, "Registration Error: " + error.getMessage());
            Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
            hideDialog();
        }
    })
        protected Map<String, String> getParameters() {
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("name", etName);
            parameters.put("username", username);
            parameters.put("password", etPassword);
            return parameters;
        }
    AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);

    private void showDialog() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }
    private void hideDialog() {
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }*/
}
