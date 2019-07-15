package amazoniaresilientes.durand.josue.amazoniaresiliente.Sincronizar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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
    private Bitmap bitmap;

    private String KEY_ESTADO2 = "poligono";
    private String KEY_NOMBRE_SUB = "area";
    private String KEY_IMAGEN2 = "imagen1";
    private String KEY_STOCK2 = "lat1";
    private String KEY_PRECIO2 = "lng1";
    private String KEY_DESCRIPCION2 = "imagen2";
    private String KEY_STOCK_MINIMO2 = "lat2";
    private  String KEY_ID_CATEGORIA2 ="lng2";
    private String KEY_ID_SUBCATEGORIA2="imagen3";
    private String KEY_STOCK_MINIMO22 = "lat3";
    private  String KEY_ID_CATEGOR2IA2 ="lat3";
    private String KEY_ID_SUBCAT2EGORIA2="imagen4";
    private String KEY_STOCK_MINIM2O2 = "lat4";
    private  String KEY_ID_C2ATEGORIA2 ="lng4";

    private static String URL_UPLOAD = "https://www.amazoniaresiliente.com/cliente/upload.php";

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
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, ""+id_Usuario, Toast.LENGTH_SHORT).show();
              //      UploadPicture("josue","durand","js",getStringImagen(bitmap1));

                    //   Intent in = new Intent(context, LoginActivity.class);
                    //  context.startActivity(in);

                }
            });
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
        final byte[] imagen1 = amazonia.getImagen1();
        final Bitmap bitmap1 = BitmapFactory.decodeByteArray(imagen1, 0, imagen1.length);

     //   holder.imageView.setImageBitmap(bitmap);
//        byte[] foodImage = food.getImagen1();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
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


//        byte[] foodImage = food.getImagen1();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
//        holder.imageView.setImageBitmap(bitmap);

        return row;
    }

    private void UploadPicture(final String username,final String email,final String password, final String photo){
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPLOAD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try{
                            JSONObject jsonObject =new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")){
                                Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(context, "Try again! "+e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error! "+error.toString(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username",username);
                params.put("email",email);
                params.put("password",password);
                params.put("photo",photo);
                return super.getParams();
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
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
