package amazoniaresilientes.durand.josue.amazoniaresiliente.Inicio;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import amazoniaresilientes.durand.josue.amazoniaresiliente.Room.SincronizarPoligono;
import amazoniaresilientes.durand.josue.amazoniaresiliente.adapter.SQLiteHelper3;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import amazoniaresilientes.durand.josue.amazoniaresiliente.R;
import androidx.appcompat.widget.AppCompatImageView;

public class RegisterClienteActivity extends AppCompatActivity {

    EditText etPrimerNombre,etSegundoNombre,etApellidoPaterno,etapellidomaterno,etDni,etPredioCliente;
    public static SQLiteHelper3     sqLiteHelper2;
    EditText  powerfactorEditText ,etAsociacionProductiva ,etEdadCultivo,etEdadCliente;
    Spinner s1;
    Spinner spEstadoCivil,specotipo;
    String procedencia,txtprocedencia,ecotipo;
    String[] Items = {
            "Seleccionar Procedencia:",
            "Nativo",
            "Mestizo",
            "Otro",
    };
    String[] Items2 = {
            "Estado Civil :",
            "Casado",
            "Soltero",
            "Conviviente",
            "Divorciado",
            "Viudo",
            "Otro",
    };
    String[] ItemsCultivo = {
            "Seleccionar Cultivo :",
            "Café",
            "Cacao",
            "Achote",
    };
    String[] ItemsEcotipo = {
            "Seleccionar Ecotipo :",
            "NATIVO",
            "CCN51",
            "OTRO",
    };
    String estadoCivil,cultivo = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_cliente);
         etPrimerNombre=(EditText)findViewById(R.id.etPrimerNombre);
         etSegundoNombre=(EditText)findViewById(R.id.etSegundoNombre);
         etApellidoPaterno=(EditText)findViewById(R.id.etApellidoPaterno);
         etapellidomaterno=(EditText)findViewById(R.id.etapellidomaterno);
        spEstadoCivil=(Spinner) findViewById(R.id.etEstadoCivil);
         etDni=(EditText)findViewById(R.id.etDni);
         etPredioCliente=(EditText)findViewById(R.id.etPredioCliente);
        etAsociacionProductiva=(EditText)findViewById(R.id.etAsociacionProductiva);
        etEdadCliente=(EditText)findViewById(R.id.etEdadCliente);
        etEdadCultivo=(EditText)findViewById(R.id.etEdadCultivo);
        etAsociacionProductiva=(EditText)findViewById(R.id.etAsociacionProductiva);
        final Button loginButton = findViewById(R.id.btnCliente);
        final Spinner spinnerCultivo = (Spinner) findViewById(R.id.spinner2);
        loginButton.setEnabled(true);

        specotipo=(Spinner) findViewById(R.id.specotipo);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ItemsEcotipo);
        specotipo.setAdapter(adapter4);
        specotipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ecotipo = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ItemsCultivo);
        spinnerCultivo.setAdapter(adapter3);
        spinnerCultivo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cultivo = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        s1 = (Spinner) findViewById(R.id.spinnerAmp);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Items);


        s1.setAdapter(adapter);
        powerfactorEditText = (EditText)findViewById(R.id.powerfactorEditText);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0 :
                        int indzex = s1.getSelectedItemPosition();
                        powerfactorEditText.setFocusable(true);
                        powerfactorEditText.setEnabled(false);
                        powerfactorEditText.setCursorVisible(false);
                        powerfactorEditText.setKeyListener(null);
                        powerfactorEditText.setBackgroundColor(Color.TRANSPARENT);
                        break;
                    case 1:
                        int index = s1.getSelectedItemPosition();
                        powerfactorEditText.setEnabled(true);
                        powerfactorEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                        powerfactorEditText.setFocusable(true);
                        powerfactorEditText.setCursorVisible(true);
                        break;
                    case 2:
                        int index1 = s1.getSelectedItemPosition();
                        powerfactorEditText.setEnabled(true);
                        powerfactorEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                        powerfactorEditText.setFocusable(true);
                        powerfactorEditText.setCursorVisible(true);
                        break;
                    case 3:
                        int indsex1 = s1.getSelectedItemPosition();
                        powerfactorEditText.setEnabled(true);
                        powerfactorEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                        powerfactorEditText.setFocusable(true);
                        powerfactorEditText.setCursorVisible(true);
                        break;
                }
                procedencia = parent.getItemAtPosition(position).toString();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });












        sqLiteHelper2 = new SQLiteHelper3(this, "AmazoniaDB.sqlite", null, 1);
        sqLiteHelper2.queryData3("CREATE TABLE IF NOT EXISTS AMAZONIASS(Id INTEGER PRIMARY KEY AUTOINCREMENT, cultivo VARCHAR, primer_nombre VARCHAR, segundo_nombre VARCHAR, apellido_paterno VARCHAR, apellido_materno VARCHAR, estado_civil VARCHAR, dni VARCHAR, referencia_predio VARCHAR, departamento_cliente VARCHAR, poligono VARCHAR, area VARCHAR, precision VARCHAR, imagen1 BLOB, lat1 VARCHAR, lng1 VARCHAR, imagen2 BLOB, lat2 VARCHAR, lng2 VARCHAR, imagen3 BLOB, lat3 VARCHAR, lng3 VARCHAR, imagen4 BLOB, lat4 VARCHAR, lng4 VARCHAR, edad_cultivo VARCHAR, edad_cliente VARCHAR, procedenciaCombo VARCHAR, txtprocedencia VARCHAR, asociacionProductiva VARCHAR, ecotipo VARCHAR)");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Items2);
        spEstadoCivil.setAdapter(adapter2);
        spEstadoCivil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                estadoCivil = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //ENVIO DE PARAMETROS DEPENDIENDO DE LA REGION PARA LOS MAPAS
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),SeleccionActivity.class);
                intent.putExtra("cultivo",cultivo);
                intent.putExtra("edadCultivo",etEdadCultivo.getText().toString().trim());
                intent.putExtra("primerNombre",etPrimerNombre.getText().toString().trim());
                intent.putExtra("segundoNombre",etSegundoNombre.getText().toString().trim());
                intent.putExtra("apellidoPaterno",etApellidoPaterno.getText().toString().trim());
                intent.putExtra("apellidoMaterno",etapellidomaterno.getText().toString().trim());
                intent.putExtra("edadCliente",etEdadCliente.getText().toString().trim());
                intent.putExtra("estadoCivil",estadoCivil);
                intent.putExtra("dni",etDni.getText().toString().trim());
                intent.putExtra("referenciaPredio",etPredioCliente.getText().toString().trim());
                intent.putExtra("procedenciaCombo",procedencia);
                intent.putExtra("txtprocedencia",powerfactorEditText.getText().toString().trim());
                intent.putExtra("asociacionProductiva",etAsociacionProductiva.getText().toString().trim());
                intent.putExtra("ecotipo",ecotipo);
                startActivity(intent);
            }
        });

        //TODO: NUBE MANDA AL LISTAR LOS POLIGONOS
        AppCompatImageView savePolygon = findViewById(R.id.listarPoligono);savePolygon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterClienteActivity.this);
                builder.setTitle("Amazonía Resiliente");
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
    //Salir del App
    private boolean doubleBackToExitPressedOnce;
    private Handler mHandler = new Handler();
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };
    @Override
    protected void onDestroy() { super.onDestroy();

        if (mHandler != null) { mHandler.removeCallbacks(mRunnable); }
    }
    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea salir de la App?");
            builder.setTitle("Amazonía Resiliente!");
            builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
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

            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Por favor presione dos veces para salir", Toast.LENGTH_SHORT).show();

        mHandler.postDelayed(mRunnable, 2000);
    }
}
