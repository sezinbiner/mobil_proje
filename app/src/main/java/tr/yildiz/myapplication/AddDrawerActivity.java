package tr.yildiz.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDrawerActivity extends AppCompatActivity {
    Button saveBtn;
    EditText drawerNameTxt;
    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drawer);

        myDB = new DBHelper(AddDrawerActivity.this);

        saveBtn = findViewById(R.id.saveDrawerButton);
        drawerNameTxt = findViewById(R.id.drawerNameTxt);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDB.insertDrawer(drawerNameTxt.getText().toString());
                Toast.makeText(AddDrawerActivity.this, "Çekmece Eklendi", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}