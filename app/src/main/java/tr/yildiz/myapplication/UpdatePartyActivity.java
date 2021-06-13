package tr.yildiz.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UpdatePartyActivity extends AppCompatActivity {
    Button del;
    DBHelper db;
    String newString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_party);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            newString = null;
        } else {
            newString = extras.getString("drawerName");
        }

        db = new DBHelper(UpdatePartyActivity.this);
        del = findViewById(R.id.deletePartyButton);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog(newString);
            }
        });
    }

    void confirmDialog(String drawerName){
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePartyActivity.this);
        builder.setTitle("Silme");
        builder.setMessage("Emin misiniz?");
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.deleteParty(newString);
                Toast.makeText(UpdatePartyActivity.this, "Etkinlik Silindi", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


    }
}