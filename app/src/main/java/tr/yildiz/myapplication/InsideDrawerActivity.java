package tr.yildiz.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class InsideDrawerActivity extends AppCompatActivity {

    Button addClothes;
    GridView gridView;
    ArrayList<Clothes> list;
    ClothesListAdapter clothesListAdapter;
    DBHelper myDB;
    private String newString, flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_drawer);

        addClothes = findViewById(R.id.addClothesBtn);
        gridView = findViewById(R.id.gridview);
        list = new ArrayList<>();
        clothesListAdapter = new ClothesListAdapter(this, R.layout.item, list);
        gridView.setAdapter(clothesListAdapter);
        myDB = new DBHelper(InsideDrawerActivity.this);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            newString = null;
        } else {
            newString = extras.getString("drawerName");
        }

        addClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),AddClothesActivity.class);
                intent.putExtra("drawerName",newString);
                startActivity(intent);
            }
        });

        //get all data
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
                CharSequence[] selections = {"Sil"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(InsideDrawerActivity.this);
                dialog.setTitle("Silme İşlemi");
                dialog.setItems(selections, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            Cursor c = myDB.getImagesId(newString);
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while(c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }

    private void showDialogDelete(int id){
        AlertDialog.Builder dialogdelete = new AlertDialog.Builder(InsideDrawerActivity.this);
        dialogdelete.setTitle("Dikkat!");
        dialogdelete.setMessage("Emin misiniz");
        dialogdelete.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try{
                    myDB.deleteImage(id);
                    Toast.makeText(InsideDrawerActivity.this, "Silme Başarılı", Toast.LENGTH_SHORT).show();
                    finish();
                }catch (Exception e){
                    Toast.makeText(InsideDrawerActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });

        dialogdelete.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogdelete.show();
    }
}