package tr.yildiz.myapplication;

import android.app.Activity;
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

public class SelectImageDrawerActivity extends AppCompatActivity {

    Button addDrawer;
    DBHelper myDB;
    RecyclerView rvDrawers;
    ArrayList<String> drawerNames;
    SelectImageAdapter drawersAdapter;
    String newString, namePart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image_drawer);
        myDB = new DBHelper(SelectImageDrawerActivity.this);
        drawerNames = new ArrayList<>();
        rvDrawers = findViewById(R.id.rvDrawers2);
        storeData();
        drawersAdapter = new SelectImageAdapter(SelectImageDrawerActivity.this,drawerNames, newString);
        rvDrawers.setAdapter(drawersAdapter);
        rvDrawers.setLayoutManager(new LinearLayoutManager(SelectImageDrawerActivity.this));
        drawersAdapter.notifyDataSetChanged();
    }

    public  void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == 2){
            newString = (String) data.getExtras().get("image_id");
            namePart = (String) data.getExtras().get("name");
            Intent returnIntent = new Intent();
            returnIntent.putExtra("image_id",newString);
            returnIntent.putExtra("name",namePart);
            setResult(1, returnIntent);
            finish();
        }
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