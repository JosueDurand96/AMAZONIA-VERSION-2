package amazoniaresilientes.durand.josue.amazoniaresiliente.Inicio;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import amazoniaresilientes.durand.josue.amazoniaresiliente.Bd.SaveClient;
import androidx.appcompat.app.AppCompatActivity;
import amazoniaresilientes.durand.josue.amazoniaresiliente.MainActivity;
import amazoniaresilientes.durand.josue.amazoniaresiliente.R;

public class SplashActivity extends AppCompatActivity {
    private final int DURACION_SPLASH = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler H=  new Handler();
        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                //Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                Intent intent = new Intent(SplashActivity.this, SaveClient.class);
                startActivity(intent);
                finish();
            }
        }, DURACION_SPLASH);
    }
}
