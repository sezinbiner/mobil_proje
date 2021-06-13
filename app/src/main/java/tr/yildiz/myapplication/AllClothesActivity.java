package tr.yildiz.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AllClothesActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<Clothes> list;
    AllClothesAdapter clothesListAdapter;
    DBHelper myDB;
    private String newString, namePart;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_clothes);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            newString = null;
        } else {
            newString = extras.getString("drawerName");
            namePart = extras.getString("name");
        }
        title = findViewById(R.id.drawerNameTitleTxt);
        title.setText(newString);
        gridView = findViewById(R.id.gridview2);
        list = new ArrayList<>();
        clothesListAdapter = new AllClothesAdapter(this, R.layout.item, list);
        gridView.setAdapter(clothesListAdapter);
        myDB = new DBHelper(AllClothesActivity.this);

        Cursor cursor = myDB.getImages(newString);
        list.clear();
        while(cursor.moveToNext()){
            byte[] img = cursor.getBlob(0);
            list.add(new Clothes(img));
        }
        clothesListAdapter.notifyDataSetChanged();


        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                CharSequence[] selections = {"Seçme İşlemi"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(AllClothesActivity.this);
                dialog.setTitle("Seç");
                dialog.setItems(selections, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            Cursor c = myDB.getImagesId(newString);
                            ArrayList<Integer> arrID = new ArrayList<>();
                            while(c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            Intent returnIntent = new Intent();
                            String new_id = String.valueOf(arrID.get(0));
                            returnIntent.putExtra("image_id",new_id);
                            returnIntent.putExtra("name",namePart);
                            setResult(2,returnIntent);
                            finish();
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }

}