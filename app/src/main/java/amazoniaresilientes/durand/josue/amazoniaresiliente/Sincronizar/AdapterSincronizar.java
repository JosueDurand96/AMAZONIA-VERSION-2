package amazoniaresilientes.durand.josue.amazoniaresiliente.Sincronizar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import amazoniaresilientes.durand.josue.amazoniaresiliente.Inicio.PhotoActivity;
import amazoniaresilientes.durand.josue.amazoniaresiliente.Inicio.RegisterClienteActivity;
import amazoniaresilientes.durand.josue.amazoniaresiliente.Login.LoginActivity;
import amazoniaresilientes.durand.josue.amazoniaresiliente.R;
import amazoniaresilientes.durand.josue.amazoniaresiliente.Room.Ntch_Amazonia;
import amazoniaresilientes.durand.josue.amazoniaresiliente.Room.SincronizarListAdapter;
import androidx.appcompat.app.AlertDialog;

public class AdapterSincronizar extends BaseAdapter {
    private Context context;
    private  int layout;
    private ArrayList<Ntch_Amazonia> foodsList;

    public AdapterSincronizar(Context context, int layout, ArrayList<Ntch_Amazonia> foodsList) {
        this.context = context;
        this.layout = layout;
        this.foodsList = foodsList;
    }

    @Override
    public int getCount() {
        return foodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtPrice;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        AdapterSincronizar.ViewHolder holder = new AdapterSincronizar.ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = (TextView) row.findViewById(R.id.txtName);
            holder.txtPrice = (TextView) row.findViewById(R.id.txtPrice);
            holder.imageView = (ImageView) row.findViewById(R.id.imgFood);
            row.setTag(holder);
        }
        else {
            holder = (AdapterSincronizar.ViewHolder) row.getTag();
        }

        Ntch_Amazonia amazonia = foodsList.get(position);
        //Datos a sincronizar
        final String id = amazonia.getId();
        final String cultivo = amazonia.getCultivo();
        final String primer_nombre = amazonia.getPrimer_nombre();
        final String segundo_nombre = amazonia.getSegundo_nombre();
        final String apellido_paterno =amazonia.getApellido_paterno();
        final String apellido_materno = amazonia.getApellido_materno();
        final String estado_civil = amazonia.getEstado_civil();
        final String dni = amazonia.getDni();
        final String referencia_predio = amazonia.getReferencia_predio();
        final String departamento_cliente = amazonia.getDepartamento_cliente();
        final String poligono = amazonia.getPoligono();
        final String area = amazonia.getArea();
        byte[] imagen1 = amazonia.getImagen1();
        final String lat1;
        final String lng1;
        byte[] imagen2 = amazonia.getImagen2();
        final  String lat2;
        final  String lng2;
        byte[] imagen3 = amazonia.getImagen3();
        final String lat3;
        final String lng3;
        byte[] imagen4 = amazonia.getImagen4();
        final String lat4;
        final String lng4;



        holder.txtName.setText(amazonia.getPrimer_nombre()+" "+amazonia.getApellido_paterno());
        holder.txtPrice.setText(amazonia.getDni());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                builder.setTitle("SINCRONIZAR");
                builder.setMessage("¿Desea Sincronizar");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Esperando..."+id+" ok", Toast.LENGTH_SHORT).show();
                        try{
                            //ELIMINAR REGISTRO SQLITE
                            RegisterClienteActivity.sqLiteHelper.deleteData2(id);
                            Toast.makeText(context, "Registro Eliminado!!!",Toast.LENGTH_SHORT).show();


                            // imageView.setImageResource(R.mipmap.ic_launcher);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                        // finish();

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
             //   Intent in = new Intent(context, LoginActivity.class);
              //  context.startActivity(in);

            }
        });

//        byte[] foodImage = food.getImagen1();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
//        holder.imageView.setImageBitmap(bitmap);

        return row;
    }

}
