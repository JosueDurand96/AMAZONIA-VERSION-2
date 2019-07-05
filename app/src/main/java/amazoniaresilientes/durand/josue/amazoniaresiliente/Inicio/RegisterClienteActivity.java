package amazoniaresilientes.durand.josue.amazoniaresiliente.Inicio;
import android.content.Intent;
import android.os.Bundle;

import android.R.*;

import android.R.layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import amazoniaresilientes.durand.josue.amazoniaresiliente.R;

public class RegisterClienteActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_cliente);

        final Button loginButton = findViewById(R.id.btnCliente);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        loginButton.setEnabled(true);
        String[] letra = {"Seleccione: ", "CACAO","CAFÉ","ACHOTE"};
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, letra));
        //ENVIO DE PARAMETROS DEPENDIENDO DE LA REGION PARA LOS MAPAS
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(spinner.getSelectedItemPosition());
                if (spinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(getApplicationContext(), "Seleccionar lugar", Toast.LENGTH_SHORT);
                    Intent intent = new Intent(RegisterClienteActivity.this, SeleccionActivity.class);
                    intent.putExtra("region","AMAZONAS");
                    intent.putExtra("regionTitulo","AMAZONAS");
                    startActivity(intent);
                } else {
                    if (spinner.getSelectedItemPosition() == 1) {
                        Intent intent = new Intent(RegisterClienteActivity.this, SeleccionActivity.class);
                        intent.putExtra("region","AMAZONAS");
                        intent.putExtra("regionTitulo","AMAZONAS");
                        startActivity(intent);
                    }
                    if (spinner.getSelectedItemPosition() == 2) {
                        Intent intent = new Intent(RegisterClienteActivity.this, SeleccionActivity.class);
                        intent.putExtra("region","AYACUCHO");
                        intent.putExtra("regionTitulo","AYACUCHO");
                        startActivity(intent);
                    }
                    if (spinner.getSelectedItemPosition() == 3) {
                        Intent intent = new Intent(RegisterClienteActivity.this, SeleccionActivity.class);
                        intent.putExtra("region","CAJAMARCA");
                        intent.putExtra("regionTitulo","CAJAMARCA");
                        startActivity(intent);
                    }
                    if (spinner.getSelectedItemPosition() == 4) {
                        Intent intent = new Intent(RegisterClienteActivity.this, SeleccionActivity.class);
                        intent.putExtra("region","CUSCO");
                        intent.putExtra("regionTitulo","CUSCO");
                        startActivity(intent);
                    }


                }

            }
        });
    }
}
