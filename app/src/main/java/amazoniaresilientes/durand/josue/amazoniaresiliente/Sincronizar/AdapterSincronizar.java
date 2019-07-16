package amazoniaresilientes.durand.josue.amazoniaresiliente.Sincronizar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import amazoniaresilientes.durand.josue.amazoniaresiliente.Inicio.PhotoActivity;
import amazoniaresilientes.durand.josue.amazoniaresiliente.Inicio.RegisterClienteActivity;
import amazoniaresilientes.durand.josue.amazoniaresiliente.Login.LoginActivity;
import amazoniaresilientes.durand.josue.amazoniaresiliente.Login.SharedPrefManager;
import amazoniaresilientes.durand.josue.amazoniaresiliente.Login.nthc_Admin;
import amazoniaresilientes.durand.josue.amazoniaresiliente.R;
import amazoniaresilientes.durand.josue.amazoniaresiliente.Room.Ntch_Amazonia;
import amazoniaresilientes.durand.josue.amazoniaresiliente.Room.SincronizarListAdapter;
import androidx.appcompat.app.AlertDialog;

public class AdapterSincronizar extends BaseAdapter {
    private Context context;
    private  int layout;
    private ArrayList<Ntch_Amazonia> foodsList;
    public static final String UPLOAD_URL = "https://www.amazoniaresiliente.com/cliente/upload3.php";
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
    private String KEY_CELULAR = "celular";

    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";

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
    private String KEY_ID_CLIENTE = "nthc_cliente_idnthc_cliente";
    private String KEY_ID_USUARIO = "nthc_cliente_nthc_usuario_idnthc_usuario";
    private static String URL_UPLOAD = "https://www.amazoniaresiliente.com/cliente/Sincronizar.php";

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
                 //   Toast.makeText(context, ""+id_Usuario, Toast.LENGTH_SHORT).show();
                    class UploadImage extends AsyncTask<Bitmap,Void,String> {

                        ProgressDialog loading;
                        RequestHandler rh = new RequestHandler();

                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            loading = ProgressDialog.show(context, "Uploading Image", "Please wait...",true,true);
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            super.onPostExecute(s);
                            loading.dismiss();
                            Toast.makeText(context,s,Toast.LENGTH_LONG).show();
                        }

                        @Override
                        protected String doInBackground(Bitmap... params) {


                            final Bitmap bitmap1 = BitmapFactory.decodeByteArray(imagen1, 0, imagen1.length);
                            final Bitmap bitmap2 = BitmapFactory.decodeByteArray(imagen2, 0, imagen2.length);
                            final Bitmap bitmap3 = BitmapFactory.decodeByteArray(imagen3, 0, imagen3.length);
                            final Bitmap bitmap4 = BitmapFactory.decodeByteArray(imagen4, 0, imagen4.length);
                            String imagen = getStringImagen(bitmap1);
                            String imagen2 = getStringImagen(bitmap2);
                            String imagen3 = getStringImagen(bitmap3);
                            String imagen4 = getStringImagen(bitmap4);
                            HashMap<String,String> data = new HashMap<>();
                            data.put(UPLOAD_KEY, imagen);
                            String result = rh.sendPostRequest(UPLOAD_URL,data);
                            return result;
                        }
                    }

                    UploadImage ui = new UploadImage();
                    ui.execute(bitmap);

                }
            });
        }








//        byte[] foodImage = food.getImagen1();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
//        holder.imageView.setImageBitmap(bitmap);

        return row;
    }



    private Bitmap bitmap;

    private Uri filePath;
    private int PICK_IMAGE_REQUEST = 1;

    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    //CONVERTIDOR
    public static byte[] imageViewToByte(ImageView image) {

        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


}
