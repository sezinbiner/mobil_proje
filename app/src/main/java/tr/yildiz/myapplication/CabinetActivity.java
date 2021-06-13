package tr.yildiz.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CabinetActivity extends AppCompatActivity {

    Button btnHead, btnFace, btnBody,saveBtn, btnLeg, btnFeet;
    ImageView vHead, vFace, vBody, vFeet, vLeg;
    String img_id, namePart;
    DBHelper myDB;
    Bitmap bmp;
    String id_head,id_face, id_body, id_legs,id_feet;
    EditText nameCombine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabinet);

        myDB = new DBHelper(CabinetActivity.this);

        btnHead = findViewById(R.id.headbutton);
        btnFace = findViewById(R.id.facebutton);
        btnBody = findViewById(R.id.bodybutton);
        btnLeg = findViewById(R.id.legsbutton);
        btnFeet = findViewById(R.id.feetbutton);
        saveBtn = findViewById(R.id.saveCombine);
        vHead = findViewById(R.id.head);
        vFace = findViewById(R.id.face);
        vBody = findViewById(R.id.body);
        vLeg = findViewById(R.id.legs);
        vFeet = findViewById(R.id.feet);
        nameCombine = findViewById(R.id.combineNameTxt);


        btnHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(), SelectImageDrawerActivity.class);
                intent.putExtra("name", "head");
                startActivityForResult(intent,1);
            }
        });

        btnFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(), SelectImageDrawerActivity.class);
                intent.putExtra("name", "face");
                startActivityForResult(intent,1);
            }
        });

        btnBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(), SelectImageDrawerActivity.class);
                intent.putExtra("name", "body");
                startActivityForResult(intent,1);
            }
        });

        btnLeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(), SelectImageDrawerActivity.class);
                intent.putExtra("name", "legs");
                startActivityForResult(intent,1);
            }
        });

        btnFeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(), SelectImageDrawerActivity.class);
                intent.putExtra("name", "feet");
                startActivityForResult(intent,1);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameC = nameCombine.getText().toString();
                myDB.insertCombine(nameC, id_head,id_face, id_body, id_legs, id_feet);
                Toast.makeText(CabinetActivity.this, "Kombin Kaydı Başarılı", Toast.LENGTH_SHORT).show();
                finish();

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == 1){
            super.onActivityResult(requestCode, resultCode, data);
            img_id = (String) data.getExtras().get("image_id");
            namePart = (String) data.getExtras().get("name");
            setImage(img_id,namePart);
        }
    }

    public void setImage(String img_id, String namePart) {
        Cursor c = myDB.getImage(img_id);
        if(c.moveToNext())
        {
            byte[] image = c.getBlob(0);
            bmp= BitmapFactory.decodeByteArray(image, 0 , image.length);
        }

        if(namePart.equals("head")){
            vHead.setImageBitmap(bmp);
            id_head = img_id;
        }else if(namePart.equals("face")){
            vFace.setImageBitmap(bmp);
            id_face = img_id;
        }else if(namePart.equals("body")){
            vBody.setImageBitmap(bmp);
            id_body = img_id;
        }else if(namePart.equals("legs")){
            vLeg.setImageBitmap(bmp);
            id_legs = img_id;
        }else if(namePart.equals("feet")){
            vFeet.setImageBitmap(bmp);
            id_feet = img_id;
        }
    }
}