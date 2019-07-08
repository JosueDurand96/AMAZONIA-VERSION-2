package amazoniaresilientes.durand.josue.amazoniaresiliente.Bd;


import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import amazoniaresilientes.durand.josue.amazoniaresiliente.Inicio.PhotoActivity;

import amazoniaresilientes.durand.josue.amazoniaresiliente.Inicio.RegisterClienteActivity;
import amazoniaresilientes.durand.josue.amazoniaresiliente.R;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SincronizarPoligono extends AppCompatActivity {
    ListView gridView;
    ArrayList<Ntch_Amazonia> list;
    SincronizarListAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sincronizar_poligono);
        gridView = (ListView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new SincronizarListAdapter(this, R.layout.sincronizar_list, list);
        gridView.setAdapter(adapter);

        // get all data from sqlite
        Cursor cursor = RegisterClienteActivity.sqLiteHelper.getData("SELECT * FROM AMAZONIA");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
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
            list.add(new Ntch_Amazonia(cultivo, primer_nombre, segundo_nombre,apellido_paterno,apellido_materno,estado_civil,dni,referencia_predio,departamento_cliente,poligono,area,precision,imagen1,lat1,lng1,imagen2,lat2,lng2,imagen3,lat3,lng3,imagen4,lat4,lng4));
        }
        adapter.notifyDataSetChanged();

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(SincronizarPoligono.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = PhotoActivity.sqLiteHelper.getData("SELECT id FROM FOOD");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                         //   showDialogUpdate(FoodList.this, arrID.get(position));

                        } else {
                            // delete
                            Cursor c = PhotoActivity.sqLiteHelper.getData("SELECT id FROM FOOD");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                          //  showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }
}
