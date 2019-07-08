package amazoniaresilientes.durand.josue.amazoniaresiliente.Bd;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import amazoniaresilientes.durand.josue.amazoniaresiliente.FoodList;
import amazoniaresilientes.durand.josue.amazoniaresiliente.MainActivity;
import amazoniaresilientes.durand.josue.amazoniaresiliente.R;

import amazoniaresilientes.durand.josue.amazoniaresiliente.SQLiteHelper;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class SaveClient extends AppCompatActivity {
    TextView txtnombres, txtregion,txtcultivo,txtdni;
    Button btnAdd;
    ImageView imageView;

    final int REQUEST_CODE_GALLERY = 999;

    public static SQLiteHelper2 sqLiteHelper;
    String segundoNombre="";
    String area;
    String latitude, longitude,latitude2, longitude2,latitude3, longitude3,latitude4, longitude4;
    String cultivo,primerNombre,apellidoPaterno,apellidoMaterno,estadoCivil,dni,referenciaPredio,regionSeleccionado,geoJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //imagenes
        latitude=getIntent().getStringExtra("lat");


        regionSeleccionado = getIntent().getStringExtra("region");
        area=getIntent().getStringExtra("area");
        cultivo = getIntent().getStringExtra("cultivo");
        primerNombre = getIntent().getStringExtra("primerNombre");
        segundoNombre = getIntent().getStringExtra("segundoNombre");
        apellidoPaterno = getIntent().getStringExtra("apellidoPaterno");
        apellidoMaterno = getIntent().getStringExtra("apellidoMaterno");
        estadoCivil = getIntent().getStringExtra("estadoCivil");
        dni = getIntent().getStringExtra("dni");
        referenciaPredio = getIntent().getStringExtra("referenciaPredio");
        geoJson = getIntent().getStringExtra("GeoJson");
        setContentView(R.layout.activity_save_client);
        init();


        Toast.makeText(this, "LAtitud: "+latitude, Toast.LENGTH_SHORT).show();
        txtnombres =(TextView)findViewById(R.id.txtnombres);
        txtnombres.setText(primerNombre+" "+apellidoPaterno);
        txtregion =(TextView)findViewById(R.id.txtdepartamento);
        txtregion.setText(regionSeleccionado);
        txtcultivo =(TextView)findViewById(R.id.txtcultivo);
        txtcultivo.setText(cultivo);
        txtdni =(TextView)findViewById(R.id.txtdni);
        txtdni.setText(dni);

//        sqLiteHelper = new SQLiteHelper2(this, "AmazoniaDB.sqlite", null, 1);
//
//        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS AMAZONIA(Id INTEGER PRIMARY KEY AUTOINCREMENT, cultivo VARCHAR, primer_nombre VARCHAR, segundo_nombre VARCHAR, apellido_paterno VARCHAR, apellido_materno VARCHAR, estado_civil VARCHAR, dni VARCHAR, referencia_predio VARCHAR, departamento_cliente VARCHAR, poligono VARCHAR, area VARCHAR, precision VARCHAR, imagen1 BLOB, lat1 VARCHAR, lng1 VARCHAR, imagen2 BLOB, lat2 VARCHAR, lng2 VARCHAR, imagen3 BLOB, lat3 VARCHAR, lng3 VARCHAR, imagen4 BLOB, lat4 VARCHAR, lng4 VARCHAR)");
//
//
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try{
//                    sqLiteHelper.insertData(
//                            cultivo,
//                            primerNombre,
//                            segundoNombre,
//                            apellidoPaterno,
//                            apellidoPaterno,
//                            estadoCivil,
//                            dni,
//                            referenciaPredio,
//                            regionSeleccionado,
//                            geoJson,
//                            area,
//                            "1.0",
//                            imageViewToByte(imageView),
//                            "josue",
//                            "josue",
//                            imageViewToByte(imageView),
//                            "josue",
//                            "josue",
//                            imageViewToByte(imageView),
//                            "josue",
//                            "josue",
//                            imageViewToByte(imageView),
//                            "josue",
//                            "josue"
//
//                    );
//                    Toast.makeText(getApplicationContext(), "AGREGADO!", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(SaveClient.this, SincronizarPoligono.class);
//                    startActivity(intent);
//
//                    imageView.setImageResource(R.mipmap.ic_launcher);
//                }
//                catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        });


    }
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){

        btnAdd = (Button) findViewById(R.id.btnAdd);

    }

}
