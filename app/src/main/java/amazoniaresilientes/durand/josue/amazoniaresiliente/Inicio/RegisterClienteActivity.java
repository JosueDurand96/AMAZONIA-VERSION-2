package amazoniaresilientes.durand.josue.amazoniaresiliente.Inicio;
import android.content.Intent;
import android.os.Bundle;

import android.R.*;

import android.R.layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import amazoniaresilientes.durand.josue.amazoniaresiliente.R;

public class RegisterClienteActivity extends AppCompatActivity {

    EditText etPrimerNombre,etSegundoNombre,etApellidoPaterno,etapellidomaterno,etEstadoCivil,etDni,etPredioCliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_cliente);
         etPrimerNombre=(EditText)findViewById(R.id.etPrimerNombre);
         etSegundoNombre=(EditText)findViewById(R.id.etSegundoNombre);
         etApellidoPaterno=(EditText)findViewById(R.id.etApellidoPaterno);
         etapellidomaterno=(EditText)findViewById(R.id.etapellidomaterno);
         etEstadoCivil=(EditText)findViewById(R.id.etEstadoCivil);
         etDni=(EditText)findViewById(R.id.etDni);
         etPredioCliente=(EditText)findViewById(R.id.etPredioCliente);
        final Button loginButton = findViewById(R.id.btnCliente);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        loginButton.setEnabled(true);
        String[] letra = {"Seleccionar Cultivo", "CACAO","CAFÉ","ACHOTE"};
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, letra));


         final  String primerNombre = etPrimerNombre.getText().toString().trim();
         String segundoNombre = etSegundoNombre.getText().toString();
         String apellidoPaterno = etApellidoPaterno.getText().toString();
         String apellidoMaterno = etapellidomaterno.getText().toString();
         String estadoCivil = etEstadoCivil.getText().toString();
         String dni = etDni.getText().toString();
         String predioCliente = etPredioCliente.getText().toString();







        //ENVIO DE PARAMETROS DEPENDIENDO DE LA REGION PARA LOS MAPAS
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(spinner.getSelectedItemPosition());
                if (spinner.getSelectedItemPosition() == 0) {

                } else {
                    if (spinner.getSelectedItemPosition() == 1) {
                        Intent intent = new Intent(RegisterClienteActivity.this, SeleccionActivity.class);

                        intent.putExtra("cultivo","CACAO");
                        intent.putExtra("primerNombre",etPrimerNombre.getText().toString().trim());
                        intent.putExtra("segundoNombre",etSegundoNombre.getText().toString().trim());
                        intent.putExtra("apellidoPaterno",etApellidoPaterno.getText().toString().trim());
                        intent.putExtra("apellidoMaterno",etapellidomaterno.getText().toString().trim());
                        intent.putExtra("estadoCivil",etEstadoCivil.getText().toString().trim());
                        intent.putExtra("dni",etDni.getText().toString().trim());
                        intent.putExtra("referenciaPredio",etPredioCliente.getText().toString().trim());
                        startActivity(intent);
                    }
                    if (spinner.getSelectedItemPosition() == 2) {
                        Intent intent = new Intent(RegisterClienteActivity.this, SeleccionActivity.class);
                        intent.putExtra("cultivo","CAFE");
                        intent.putExtra("primerNombre",etPrimerNombre.getText().toString().trim());
                        intent.putExtra("segundoNombre",etSegundoNombre.getText().toString().trim());
                        intent.putExtra("apellidoPaterno",etApellidoPaterno.getText().toString().trim());
                        intent.putExtra("apellidoMaterno",etapellidomaterno.getText().toString().trim());
                        intent.putExtra("estadoCivil",etEstadoCivil.getText().toString().trim());
                        intent.putExtra("dni",etDni.getText().toString().trim());
                        intent.putExtra("referenciaPredio",etPredioCliente.getText().toString().trim());

                        startActivity(intent);
                    }
                    if (spinner.getSelectedItemPosition() == 3) {
                        Intent intent = new Intent(RegisterClienteActivity.this, SeleccionActivity.class);
                        intent.putExtra("cultivo","ACHOTE");
                        intent.putExtra("primerNombre",etPrimerNombre.getText().toString().trim());
                        intent.putExtra("segundoNombre",etSegundoNombre.getText().toString().trim());
                        intent.putExtra("apellidoPaterno",etApellidoPaterno.getText().toString().trim());
                        intent.putExtra("apellidoMaterno",etapellidomaterno.getText().toString().trim());
                        intent.putExtra("estadoCivil",etEstadoCivil.getText().toString().trim());
                        intent.putExtra("dni",etDni.getText().toString().trim());
                        intent.putExtra("referenciaPredio",etPredioCliente.getText().toString().trim());

                        startActivity(intent);
                    }



                }

            }
        });
    }
}
