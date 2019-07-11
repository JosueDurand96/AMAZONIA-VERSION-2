package amazoniaresilientes.durand.josue.amazoniaresiliente.Sincronizar;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import amazoniaresilientes.durand.josue.amazoniaresiliente.Food;
import amazoniaresilientes.durand.josue.amazoniaresiliente.FoodList;
import amazoniaresilientes.durand.josue.amazoniaresiliente.Inicio.PhotoActivity;
import amazoniaresilientes.durand.josue.amazoniaresiliente.Inicio.RegisterClienteActivity;
import amazoniaresilientes.durand.josue.amazoniaresiliente.Login.SharedPrefManager;
import amazoniaresilientes.durand.josue.amazoniaresiliente.MainActivity;
import amazoniaresilientes.durand.josue.amazoniaresiliente.R;
import amazoniaresilientes.durand.josue.amazoniaresiliente.Room.Ntch_Amazonia;

import amazoniaresilientes.durand.josue.amazoniaresiliente.Room.SincronizarPoligono;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class Sincronizar extends AppCompatActivity {
    ListView gridView;
    ArrayList<Ntch_Amazonia> list;
    AdapterSincronizar adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sincronizar);
        gridView = (ListView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new AdapterSincronizar(this, R.layout.sincronizar_lis2, list);
        gridView.setAdapter(adapter);

        // get all data from sqlite
        Cursor cursor = RegisterClienteActivity.sqLiteHelper.getData("SELECT * FROM AMAZONIA");
        list.clear();
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String cultivo = cursor.getString(1);
            String primer_nombre = cursor.getString(2);
            String segundo_nombre = cursor.getString(3);
            String apellido_paterno = cursor.getString(4);
            String apellido_materno = cursor.getString(5);
            String estado_civil = cursor.getString(6);
            String dni = cursor.getString(7);
            String referencia_predio = cursor.getString(8);
            String departamento_cliente = cursor.getString(9);
            String poligono = cursor.getString(10);
            String area = cursor.getString(11);
            String precision = cursor.getString(12);
            byte[] imagen1 = cursor.getBlob(13);
            String lat1 = cursor.getString(14);
            String lng1 = cursor.getString(15);
            byte[] imagen2 = cursor.getBlob(16);
            String lat2 = cursor.getString(17);
            String lng2 = cursor.getString(18);
            byte[] imagen3 = cursor.getBlob(19);
            String lat3 = cursor.getString(20);
            String lng3 = cursor.getString(21);
            byte[] imagen4 = cursor.getBlob(22);
            String lat4 = cursor.getString(23);
            String lng4 = cursor.getString(24);
            list.add(new Ntch_Amazonia(id,cultivo, primer_nombre, segundo_nombre,apellido_paterno,apellido_materno,estado_civil,dni,referencia_predio,departamento_cliente,poligono,area,precision,imagen1,lat1,lng1,imagen2,lat2,lng2,imagen3,lat3,lng3,imagen4,lat4,lng4));
        }
        adapter.notifyDataSetChanged();

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                //CharSequence[] items = {"Update", "Delete"};
                CharSequence[] items = {"Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(Sincronizar.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item==0){
                            Cursor c = RegisterClienteActivity.sqLiteHelper.getData("SELECT id FROM AMAZONIA");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
//                        if (item == 0) {
//                            // update
//                            Cursor c = RegisterClienteActivity.sqLiteHelper.getData("SELECT id FROM AMAZONIA");
//                            ArrayList<Integer> arrID = new ArrayList<Integer>();
//                            while (c.moveToNext()){
//                                arrID.add(c.getInt(0));
//                            }
//                            // show dialog update at here
//                            //   showDialogUpdate(FoodList.this, arrID.get(position));
//
//                        } else {
//                            // delete
//                            Cursor c = RegisterClienteActivity.sqLiteHelper.getData("SELECT id FROM AMAZONIA");
//                            ArrayList<Integer> arrID = new ArrayList<Integer>();
//                            while (c.moveToNext()){
//                                arrID.add(c.getInt(0));
//                            }
//                              showDialogDelete(arrID.get(position));
//                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }
//
//    private void updateFoodList(){
//        // get all data from sqlite
//        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM FOOD");
//        list.clear();
//        while (cursor.moveToNext()) {
//            int id = cursor.getInt(0);
//            String name = cursor.getString(1);
//            String price = cursor.getString(2);
//            byte[] image = cursor.getBlob(3);
//
//            list.add(new Food(name, price, image, id));
//        }
//        adapter.notifyDataSetChanged();
//    }



    ImageView imageViewFood;
//    private void showDialogUpdate(Activity activity, final int position){
//
//        final Dialog dialog = new Dialog(activity);
//        dialog.setContentView(R.layout.update_food_activity);
//        dialog.setTitle("Update");
//
//        imageViewFood = (ImageView) dialog.findViewById(R.id.imageViewFood);
//        final EditText edtName = (EditText) dialog.findViewById(R.id.edtName);
//        final EditText edtPrice = (EditText) dialog.findViewById(R.id.edtPrice);
//        Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);
//
//        // set width for dialog
//        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
//        // set height for dialog
//        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
//        dialog.getWindow().setLayout(width, height);
//        dialog.show();
//
//        imageViewFood.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // request photo library
//                ActivityCompat.requestPermissions(
//                        FoodList.this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                        888
//                );
//            }
//        });
//
//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    MainActivity.sqLiteHelper.updateData(
//                            edtName.getText().toString().trim(),
//                            edtPrice.getText().toString().trim(),
//                            MainActivity.imageViewToByte(imageViewFood),
//                            position
//                    );
//                    dialog.dismiss();
//                    Toast.makeText(getApplicationContext(), "Update successfully!!!",Toast.LENGTH_SHORT).show();
//                }
//                catch (Exception error) {
//                    Log.e("Update error", error.getMessage());
//                }
//                updateFoodList();
//            }
//        });
//    }

    private void showDialogDelete(final int idFood){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(Sincronizar.this);

        dialogDelete.setTitle("Espere!!");
        dialogDelete.setMessage("¿Esta seguro eliminarlo?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    RegisterClienteActivity.sqLiteHelper.deleteData(idFood);
                    Toast.makeText(getApplicationContext(), "Registro Eliminado!!!",Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Log.e("error", e.getMessage());
                }
             Intent i = new Intent(getApplicationContext(),Sincronizar.class);
                startActivity(i);
            }
        });

        dialogDelete.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }
    @Override
    public void onBackPressed()
    {

        // code here to show dialog
        Intent intent = new Intent(Sincronizar.this, RegisterClienteActivity.class);
      //  SharedPrefManager.getInstance(getApplicationContext()).logout();
        startActivity(intent);

        finish();
        super.onBackPressed();  // optional depending on your needs
    }
}
