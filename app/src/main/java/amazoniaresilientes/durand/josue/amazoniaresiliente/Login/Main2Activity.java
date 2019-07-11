package amazoniaresilientes.durand.josue.amazoniaresiliente.Login;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import amazoniaresilientes.durand.josue.amazoniaresiliente.R;
import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    TextView id,userName,userEmail,gender;
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            id = findViewById(R.id.textViewId);
            userName = findViewById(R.id.textViewUsername);
            userEmail = findViewById(R.id.textViewEmail);
            gender = findViewById(R.id.textViewGender);
            btnLogout = findViewById(R.id.buttonLogout);
            nthc_Admin user = SharedPrefManager.getInstance(this).getUser();

            id.setText(String.valueOf(user.getId()));
            userEmail.setText(user.getCorreo());
            gender.setText(user.getApellido_paterno());
            userName.setText(user.getPrimer_nombre());

            btnLogout.setOnClickListener(this);
        }
        else{
            Intent intent = new Intent(Main2Activity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnLogout)){
            SharedPrefManager.getInstance(getApplicationContext()).logout();
        }
    }
}
