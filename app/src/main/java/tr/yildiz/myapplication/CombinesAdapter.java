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

public class CombinesAdapter extends RecyclerView.Adapter<CombinesAdapter.MyViewHolder> {
    Context context;
    ArrayList names;
    DBHelper myDB;

    CombinesAdapter(Context context, ArrayList names){
        this.context = context;
        this.names = names;
    }


    @NonNull
    @Override
    public CombinesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.combinerow, viewGroup, false);
        return new CombinesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CombinesAdapter.MyViewHolder myViewHolder, int i) {

        String drawerName = String.valueOf(names.get(i));
        myViewHolder.name.setText(String.valueOf(names.get(i)));
        myViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowCombineActivity.class);
                intent.putExtra("drawerName",drawerName);
                context.startActivity(intent);
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
            name = itemView.findViewById(R.id.combineName);
            mainLayout = itemView.findViewById(R.id.combineLayout);
        }

    }
}

