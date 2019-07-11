package amazoniaresilientes.durand.josue.amazoniaresiliente.Login;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import amazoniaresilientes.durand.josue.amazoniaresiliente.R;
import amazoniaresilientes.durand.josue.amazoniaresiliente.Sincronizar.Sincronizar;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText etName, etPassword;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
//            finish();
//            startActivity(new Intent(this, LoginActivity.class));
//        }

        progressBar = findViewById(R.id.progressBar);
        etName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etUserPassword);



        //calling the method userLogin() for login the user
        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

    }
    private void userLogin() {
        //first getting the values
        final String correo = etName.getText().toString();
        final String password = etPassword.getText().toString();
        //validating inputs
        if (TextUtils.isEmpty(correo)) {
            etName.setError("Please enter your username");
            etName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Please enter your password");
            etPassword.requestFocus();
            return;
        }

        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting the user from the response
                                JSONObject userJson = obj.getJSONObject("user");

                                //creating a new user object
                                nthc_Admin user = new nthc_Admin(
                                        userJson.getInt("idnthc_usuario"),
                                        userJson.getString("primer_nombre"),
                                        userJson.getString("segundo_nombre"),
                                        userJson.getString("apellido_paterno"),
                                        userJson.getString("apellido_materno"),
                                        userJson.getString("estado_civil"),
                                        userJson.getString("dni"),
                                        userJson.getString("correo"),
                                        userJson.getString("celular")
                                );


                                //storing the user in shared preferences
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                                //starting the profile activity
                                finish();

                                startActivity(new Intent(getApplicationContext(), Sincronizar.class));
                            } else {

                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("correo", correo);
                params.put("password", password);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
