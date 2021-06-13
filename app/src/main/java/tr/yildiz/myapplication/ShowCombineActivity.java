package tr.yildiz.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowCombineActivity extends AppCompatActivity {

    DBHelper myDB;
    String newString;
    EditText title;
    ArrayList<String> combines;
    Bitmap bmp;
    String id1,id2,id3,id4,id5;
    ImageView vHead, vFace, vBody, vFeet, vLeg;
    Button btnDelete;
    String nameCombine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_combine);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            newString = null;
        } else {
            newString = extras.getString("drawerName");
        }

        title = findViewById(R.id.combineNameTxt2);
        vHead = findViewById(R.id.head2);
        vFace = findViewById(R.id.face2);
        vBody = findViewById(R.id.body2);
        vLeg = findViewById(R.id.legs2);
        vFeet = findViewById(R.id.feet2);
        btnDelete = findViewById(R.id.deleteCombine);

        myDB = new DBHelper(ShowCombineActivity.this);

        Cursor c = myDB.getCombine(newString);

        while (c.moveToNext()){
            nameCombine = c.getString(1);
            title.setText(nameCombine);
            id1 = c.getString(2);
            id2 = c.getString(3);
            id3 = c.getString(4);
            id4 = c.getString(5);
            id5 = c.getString(6);
        }

        c = myDB.getImage(id1);
        if(c.moveToNext())
        {
            byte[] image = c.getBlob(0);
            bmp= BitmapFactory.decodeByteArray(image, 0 , image.length);
            vHead.setImageBitmap(bmp);
        }
        c = myDB.getImage(id2);
        if(c.moveToNext())
        {
            byte[] image = c.getBlob(0);
            bmp= BitmapFactory.decodeByteArray(image, 0 , image.length);
            vFace.setImageBitmap(bmp);
        }

        c = myDB.getImage(id3);
        if(c.moveToNext())
        {
            byte[] image = c.getBlob(0);
            bmp= BitmapFactory.decodeByteArray(image, 0 , image.length);
            vBody.setImageBitmap(bmp);
        }

        c = myDB.getImage(id4);
        if(c.moveToNext())
        {
            byte[] image = c.getBlob(0);
            bmp= BitmapFactory.decodeByteArray(image, 0 , image.length);
            vLeg.setImageBitmap(bmp);
        }

        c = myDB.getImage(id5);
        if(c.moveToNext())
        {
            byte[] image = c.getBlob(0);
            bmp= BitmapFactory.decodeByteArray(image, 0 , image.length);
            vFeet.setImageBitmap(bmp);
        }

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog(nameCombine);
            }
        });
    }

    void confirmDialog(String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHelper myDB = new DBHelper(ShowCombineActivity.this);
                myDB.deleteCombine(name);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

}