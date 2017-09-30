package com.example.vlisn.m4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vlisn on 9/24/2017.
 */

public class Register extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    ProgressDialog progressDialog;
    Button bRegister;
    EditText etName,etUsername,etPassword;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        etName = (EditText) findViewById(R.id.name);
        etUsername = (EditText) findViewById(R.id.newUsername);
        etPassword = (EditText) findViewById(R.id.newPassword);
        bRegister= (Button) findViewById(R.id.buttonCreate);

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

        String Request strReq = new StringRequest(Request.Methold.POST , new Response.Listener<String>());
        public void on Response(String response) {
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
    }
}
