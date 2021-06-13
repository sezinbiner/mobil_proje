package tr.yildiz.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AllClothesAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Clothes> clothesList;

    public AllClothesAdapter(Context context, int layout, ArrayList<Clothes> clothesList) {
        this.context = context;
        this.layout = layout;
        this.clothesList = clothesList;
    }

    @Override
    public int getCount() {
        return clothesList.size();
    }

    @Override
    public Object getItem(int position) {
        return clothesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        AllClothesAdapter.ViewHolder holder= new AllClothesAdapter.ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.imageView = row.findViewById(R.id.itemView);
            row.setTag(holder);
        }else{
            holder = (AllClothesAdapter .ViewHolder) row.getTag() ;
        }

        Clothes clothes = clothesList.get(position);
        byte[] image = clothes.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}