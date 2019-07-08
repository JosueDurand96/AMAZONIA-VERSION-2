package amazoniaresilientes.durand.josue.amazoniaresiliente.Inicio;
import android.content.DialogInterface;
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

import amazoniaresilientes.durand.josue.amazoniaresiliente.Bd.SQLiteHelper2;
import amazoniaresilientes.durand.josue.amazoniaresiliente.Bd.SincronizarPoligono;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import amazoniaresilientes.durand.josue.amazoniaresiliente.R;
import androidx.appcompat.widget.AppCompatImageView;

public class RegisterClienteActivity extends AppCompatActivity {

    EditText etPrimerNombre,etSegundoNombre,etApellidoPaterno,etapellidomaterno,etEstadoCivil,etDni,etPredioCliente;
    public static SQLiteHelper2 sqLiteHelper;

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
        sqLiteHelper = new SQLiteHelper2(this, "AmazoniaDB.sqlite", null, 1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS AMAZONIA(Id INTEGER PRIMARY KEY AUTOINCREMENT, cultivo VARCHAR, primer_nombre VARCHAR, segundo_nombre VARCHAR, apellido_paterno VARCHAR, apellido_materno VARCHAR, estado_civil VARCHAR, dni VARCHAR, referencia_predio VARCHAR, departamento_cliente VARCHAR, poligono VARCHAR, area VARCHAR, precision VARCHAR, imagen1 BLOB, lat1 VARCHAR, lng1 VARCHAR, imagen2 BLOB, lat2 VARCHAR, lng2 VARCHAR, imagen3 BLOB, lat3 VARCHAR, lng3 VARCHAR, imagen4 BLOB, lat4 VARCHAR, lng4 VARCHAR)");


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

        /**/
        AppCompatImageView savePolygon = findViewById(R.id.listarPoligono);
        savePolygon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterClienteActivity.this);
                builder.setTitle("App");
                builder.setMessage("¿Desea sincronizar");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle bundle = new Bundle();
                        Intent intent = new Intent(RegisterClienteActivity.this, SincronizarPoligono.class);

                        startActivity(intent);
                        finish();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }


    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        finish();
        super.onBackPressed();  // optional depending on your needs
    }
}
