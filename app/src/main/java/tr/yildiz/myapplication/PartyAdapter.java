package tr.yildiz.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PartyAdapter extends RecyclerView.Adapter<PartyAdapter.MyViewHolder>{

    Context context;
    ArrayList names;
    DBHelper myDB;
    String flag;

    PartyAdapter(Context context, ArrayList names, String flag){
        this.context = context;
        this.names = names;
        this.flag = flag;
    }


    @NonNull
    @Override
    public PartyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.drawer_row, viewGroup, false);
        return new PartyAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartyAdapter.MyViewHolder myViewHolder, int i) {

        String drawerName = String.valueOf(names.get(i));
        myViewHolder.name.setText(String.valueOf(names.get(i)));
        myViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdatePartyActivity.class);
                intent.putExtra("drawerName",drawerName);
                context.startActivity(intent);
            }
        });

        myViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRow(drawerName);
            }
        });

    }

    void confirmDialog(String drawerName){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Silme");
        builder.setMessage("Emin misiniz?");
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteRow(drawerName);
            }
        });

        builder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public void deleteRow(String drawerName){
        myDB = new DBHelper(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Silme");
        builder.setMessage("Emin misiniz?");
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDB.deleteDrawer(drawerName);

            }
        });

        builder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
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

