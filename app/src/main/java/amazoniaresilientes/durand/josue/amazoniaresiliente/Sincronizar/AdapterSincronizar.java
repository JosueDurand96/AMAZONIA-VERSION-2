package amazoniaresilientes.durand.josue.amazoniaresiliente.Sincronizar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import amazoniaresilientes.durand.josue.amazoniaresiliente.Inicio.RegisterClienteActivity;
import amazoniaresilientes.durand.josue.amazoniaresiliente.Login.SharedPrefManager;
import amazoniaresilientes.durand.josue.amazoniaresiliente.Room.nthc_Admin;
import amazoniaresilientes.durand.josue.amazoniaresiliente.R;
import amazoniaresilientes.durand.josue.amazoniaresiliente.Room.Ntch_Amazonia;
import androidx.appcompat.app.AlertDialog;

public class AdapterSincronizar extends BaseAdapter {
    private Context context;
    private  int layout;
    private ArrayList<Ntch_Amazonia> foodsList;
    public static final String UPLOAD_URL = "https://www.amazoniaresiliente.com/cliente/upload4.php";
    public static final String UPLOAD_KEY = "image";
    public static final String TAG = "MY MESSAGE";

    private String KEY_PRIMER_NOMBRE = "primer_nombre";
    private String KEY_SEGUNDO_NOMBRE = "segundo_nombre";
    private String KEY_APELLIDO_PATERNO = "apellido_paterno";
    private String KEY_APELLIDO_MATERNO = "apellido_materno";
    private String KEY_ESTADO_CIVIL = "estado_civil";
    private String KEY_DNI = "dni";
    private String KEY_REFERENCIA_PREDIO = "referencia_predio";
    private String KEY_DEPARTAMENTO_CLIENTE = "departamento_cliente";
    private String KEY_CULTIVO = "cultivo";

    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";
    private String KEY_NAME2 = "name2";
    private String KEY_NAME3 = "name3";
    private String KEY_NAME4 = "name4";
    private String KEY_NAME5 = "name5";
    private String KEY_NAME6 = "name6";
    private String KEY_NAME7 = "name7";
    private String KEY_NAME8 = "name8";
    private String KEY_ID_CLIENTE = "nthc_cliente_idnthc_cliente";
    private String KEY_ID_USUARIO = "nthc_usuario_idnthc_usuario";

    private String KEY_POLIGONO = "poligono";
    private String KEY_AREA = "area";
    private String KEY_IMAGEN1 = "imagen1";
    private String KEY_LAT1 = "lat1";
    private String KEY_LNG1 = "lng1";
    private String KEY_IMAGEN2 = "imagen2";
    private String KEY_LAT2 = "lat2";
    private  String KEY_LNG2 ="lng2";
    private String KEY_IMAGEN3="imagen3";
    private String KEY_LAT3 = "lat3";
    private  String KEY_LNG3 ="lat3";
    private String KEY_IMAGEN4 ="imagen4";
    private String KEY_LAT4 = "lat4";
    private  String KEY_LNG4 ="lng4";


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





        if(SharedPrefManager.getInstance(context).isLoggedIn()){

            nthc_Admin user = SharedPrefManager.getInstance(context).getUser();

            final String id_Usuario = String.valueOf(user.getId());




//            id2.setText(String.valueOf(user.getId()));
//            userEmail.setText(user.getCorreo());
//            gender.setText(user.getApellido_paterno());
//            userName.setText(user.getPrimer_nombre());

        //    btnLogout.setOnClickListener(this);

            final Ntch_Amazonia amazonia = foodsList.get(position);
            //Datos a sincronizar
            final String id_bd_sqlite = amazonia.getId();
            final String cultivo = amazonia.getCultivo();
            final String primer_nombre = amazonia.getPrimer_nombre();
            final String segundo_nombre = amazonia.getSegundo_nombre();
            final String apellido_paterno =amazonia.getApellido_paterno();
            final String apellido_materno = amazonia.getApellido_materno();
            final String estado_civil = amazonia.getEstado_civil();
            final String dni = amazonia.getDni();
            final String celular = "9478523";
            final String referencia_predio = amazonia.getReferencia_predio();
            final String departamento_cliente = amazonia.getDepartamento_cliente();
            final String poligono = amazonia.getPoligono();
            final String area = amazonia.getArea();
            final byte[] imagen1 = amazonia.getImagen1();
            final String lat1 =amazonia.getLat1();
            final String lng1 = amazonia.getLng1();
            final byte[] imagen2 = amazonia.getImagen2();
            final  String lat2= amazonia.getLat2();
            final  String lng2= amazonia.getLng2();
            final byte[] imagen3 = amazonia.getImagen3();
            final String lat3=amazonia.getLat3();
            final String lng3 = amazonia.getLng3();
            final byte[] imagen4 = amazonia.getImagen4();
            final String lat4= amazonia.getLat4();
            final String lng4 = amazonia.getLng4();
            holder.txtName.setText(amazonia.getPrimer_nombre()+" "+amazonia.getApellido_paterno());
            holder.txtPrice.setText(amazonia.getDni());

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RegisterClienteActivity.sqLiteHelper.deleteData(Integer.parseInt(id_bd_sqlite));
                    final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(context);

                    dialogDelete.setTitle("Amazonía Resiliente!!");
                    dialogDelete.setMessage("¿Desea Sincronizar?");
                    dialogDelete.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                class UploadImage extends AsyncTask<Bitmap,Void,String> {

                                    ProgressDialog loading;
                                    RequestHandler rh = new RequestHandler();

                                    @Override
                                    protected void onPreExecute() {
                                        super.onPreExecute();
                                        loading = ProgressDialog.show(context, "Sincronizando", "Por favor espere...",true,true);
                                    }

                                    @Override
                                    protected void onCancelled() {
                                        Toast.makeText(context, "NO SE PUDO SINCRONIZAR", Toast.LENGTH_SHORT).show();
                                        super.onCancelled();
                                    }

                                    @Override
                                    protected void onPostExecute(String s) {
                                        super.onPostExecute(s);
                                        loading.dismiss();
                                        Toast.makeText(context,"SINCRONIZACIÓN CORRECTA",Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    protected String doInBackground(Bitmap... params) {
                                        final Bitmap bitmap1 = BitmapFactory.decodeByteArray(imagen1, 0, imagen1.length);
                                        final Bitmap bitmap2 = BitmapFactory.decodeByteArray(imagen2, 0, imagen2.length);
                                        final Bitmap bitmap3 = BitmapFactory.decodeByteArray(imagen3, 0, imagen3.length);
                                        final Bitmap bitmap4 = BitmapFactory.decodeByteArray(imagen4, 0, imagen4.length);
                                        String  imagen1 = getStringImagen(bitmap1);
                                        String imagen2 = getStringImagen(bitmap2);
                                        String imagen3 = getStringImagen(bitmap3);
                                        String imagen4 = getStringImagen(bitmap4);
                                        HashMap<String,String> data = new HashMap<>();
                                        data.put(KEY_PRIMER_NOMBRE, primer_nombre);
                                        data.put(KEY_SEGUNDO_NOMBRE, segundo_nombre);
                                        data.put(KEY_APELLIDO_PATERNO,  apellido_paterno);
                                        data.put(KEY_APELLIDO_MATERNO, apellido_materno);
                                        data.put(KEY_ESTADO_CIVIL, estado_civil);
                                        data.put(KEY_DNI, dni);
                                        data.put(KEY_REFERENCIA_PREDIO, referencia_predio);
                                        data.put(KEY_DEPARTAMENTO_CLIENTE, departamento_cliente);
                                        data.put(KEY_CULTIVO, cultivo);
                                        data.put(KEY_ID_USUARIO,id_Usuario);
                                        data.put(KEY_POLIGONO, poligono);
                                        data.put(KEY_AREA, area);
                                        data.put(KEY_IMAGEN1,  imagen1);
                                        data.put(KEY_LAT1, lat1);
                                        data.put(KEY_LNG1, lng1);
                                        data.put(KEY_IMAGEN2,  imagen2);
                                        data.put(KEY_LAT2, lat2);
                                        data.put(KEY_LNG2, lng2);
                                        data.put(KEY_IMAGEN3,imagen3);
                                        data.put(KEY_LAT3, lat3);
                                        data.put(KEY_LNG3, lng3);
                                        data.put(KEY_IMAGEN4,imagen4);
                                        data.put(KEY_LAT4, lat4);
                                        data.put(KEY_LNG4, lng4);
                                        String result = rh.sendPostRequest(UPLOAD_URL,data);
                                        return result;
                                    }
                                }
                                UploadImage ui = new UploadImage();
                                ui.execute(bitmap);

                            } catch (Exception e){
                                Log.e("error", e.getMessage());
                            }
                            Intent i = new Intent(context,Sincronizar.class);
                            context.startActivity(i);
                        }
                    });

                    dialogDelete.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dialogDelete.show();


                }
            });
        }

        return row;
    }


    private Bitmap bitmap;


    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }



}
