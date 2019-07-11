package amazoniaresilientes.durand.josue.amazoniaresiliente.Room;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import amazoniaresilientes.durand.josue.amazoniaresiliente.Login.LoginActivity;
import amazoniaresilientes.durand.josue.amazoniaresiliente.R;

public class SincronizarListAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private ArrayList<Ntch_Amazonia> foodsList;

    public SincronizarListAdapter(Context context, int layout, ArrayList<Ntch_Amazonia> foodsList) {
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
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = (TextView) row.findViewById(R.id.txtName);
            holder.txtPrice = (TextView) row.findViewById(R.id.txtPrice);
            holder.imageView = (ImageView) row.findViewById(R.id.imgFood);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        final Ntch_Amazonia food = foodsList.get(position);

        holder.txtName.setText(food.getLat2()+" "+food.getApellido_paterno());
        holder.txtPrice.setText(food.getLat1());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Por favor debe iniciar Sesión ...", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(context, LoginActivity.class);
                context.startActivity(in);

            }
        });

//        byte[] foodImage = food.getImagen1();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
//        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
