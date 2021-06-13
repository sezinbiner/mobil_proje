package tr.yildiz.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ListPartyActivity extends AppCompatActivity {

    Button addDrawer;
    DBHelper myDB;
    RecyclerView rvDrawers;
    ArrayList<String> drawerNames;
    PartyAdapter drawersAdapter;
    String newString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_party);

        myDB = new DBHelper(ListPartyActivity.this);
        drawerNames = new ArrayList<>();
        rvDrawers = findViewById(R.id.rvParty);

        addDrawer = findViewById(R.id.addParty);
        addDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddPartyActivity.class);
                startActivity(intent);
            }
        });

        storeData();
        drawersAdapter = new PartyAdapter(ListPartyActivity.this,drawerNames, newString);
        rvDrawers.setAdapter(drawersAdapter);
        rvDrawers.setLayoutManager(new LinearLayoutManager(ListPartyActivity.this));
        drawersAdapter.notifyDataSetChanged();

    }

    void storeData() {
        Cursor cursor = myDB.readPartyData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Veri yok", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                drawerNames.add(cursor.getString(0));
            }
        }
    }
}