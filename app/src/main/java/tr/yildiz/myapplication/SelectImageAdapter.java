package tr.yildiz.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SelectImageAdapter extends RecyclerView.Adapter<SelectImageAdapter.MyViewHolder> {

    Context context;
    ArrayList names;
    DBHelper myDB;
    String flag;


    SelectImageAdapter(Context context, ArrayList names, String flag){
        this.context = context;
        this.names = names;
        this.flag = flag;
    }


    @NonNull
    @Override
    public SelectImageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.drawer_row, viewGroup, false);
        return new SelectImageAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectImageAdapter.MyViewHolder myViewHolder, int i) {

        String drawerName = String.valueOf(names.get(i));
        myViewHolder.name.setText(String.valueOf(names.get(i)));
        myViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newString;
                Bundle extras = ((Activity)context).getIntent().getExtras();
                if (extras == null) {
                    newString = null;
                } else {
                    newString = extras.getString("name");
                }
                Intent intent = new Intent(context, AllClothesActivity.class);
                intent.putExtra("drawerName",drawerName);
                intent.putExtra("name",newString);
                ((Activity) context).startActivityForResult(intent,2);
            }
        });

    }


    @Override
    public int getItemCount() {
        return names.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView delete;
        TextView name;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.drawerNameTextView);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            delete = itemView.findViewById(R.id.deleteDrawerButton);
        }

    }
}
