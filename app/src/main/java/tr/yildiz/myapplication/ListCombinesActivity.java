package tr.yildiz.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ListCombinesActivity extends AppCompatActivity {

    DBHelper myDB;
    RecyclerView rvDrawers;
    ArrayList<String> drawerNames;
    CombinesAdapter drawersAdapter;
    String newString;
    Button res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_combines);
        myDB = new DBHelper(ListCombinesActivity.this);
        drawerNames = new ArrayList<>();
        rvDrawers = findViewById(R.id.rvCombines);
        res= findViewById(R.id.restart2);

        storeData();
        drawersAdapter = new CombinesAdapter(ListCombinesActivity.this,drawerNames);
        rvDrawers.setAdapter(drawersAdapter);
        rvDrawers.setLayoutManager(new LinearLayoutManager(ListCombinesActivity.this));
        drawersAdapter.notifyDataSetChanged();

        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });

    }

    void storeData() {
        Cursor cursor = myDB.readCombines();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Veri Yok", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                drawerNames.add(cursor.getString(0));
            }
        }
    }
}