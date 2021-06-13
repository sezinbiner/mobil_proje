package tr.yildiz.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDrawerActivity extends AppCompatActivity {


    Button addDrawer;
    DBHelper myDB;
    RecyclerView rvDrawers;
    ArrayList<String> drawerNames;
    DrawersAdapter drawersAdapter;
    String newString;
    Button res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_drawer);

        myDB = new DBHelper(ListDrawerActivity.this);
        drawerNames = new ArrayList<>();
        rvDrawers = findViewById(R.id.rvDrawers);
        res = findViewById(R.id.restart);

        addDrawer = findViewById(R.id.addDrawer);
        addDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddDrawerActivity.class);
                startActivity(intent);
            }
        });

        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });


        storeData();
        drawersAdapter = new DrawersAdapter(ListDrawerActivity.this,drawerNames, newString);
        rvDrawers.setAdapter(drawersAdapter);
        rvDrawers.setLayoutManager(new LinearLayoutManager(ListDrawerActivity.this));
        drawersAdapter.notifyDataSetChanged();

    }

    void storeData() {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                drawerNames.add(cursor.getString(0));
            }
        }
    }
}