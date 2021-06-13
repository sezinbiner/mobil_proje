package tr.yildiz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListCabinetActivity extends AppCompatActivity {

    String items[] = new String[] {"Kombinler", "Yeni Kombin Oluştur"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cabinet_actvity);

        ListView listView = findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(getApplicationContext(),ListCombinesActivity.class);
                    startActivity(intent);
                }else if(position ==1){
                    Intent intent = new Intent(getApplicationContext(),CabinetActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}