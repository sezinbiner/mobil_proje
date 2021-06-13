package tr.yildiz.myapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddPartyActivity extends AppCompatActivity {

    EditText partyNameTxt, partyTypeTxt, partyDateTxt;
    Button save;
    Spinner spin;
    DBHelper db;
    ArrayList<String> combines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_party);

        partyDateTxt = findViewById(R.id.partyDateTxt);
        partyTypeTxt = findViewById(R.id.partyTypeTxt);
        partyNameTxt = findViewById(R.id.partyNameTxt);
        spin = findViewById(R.id.spinner);
        save = findViewById(R.id.savePartyBtn);

        db = new DBHelper(AddPartyActivity.this);
        Cursor c = db.readCombines();

        combines = new ArrayList<String>();

        while (c.moveToNext()){
            combines.add(c.getString(0));
        }

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddPartyActivity.this, android.R.layout.simple_list_item_1,combines);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(myAdapter);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String partyDate = partyDateTxt.getText().toString();
                String partyType = partyTypeTxt.getText().toString();
                String partyName = partyNameTxt.getText().toString();
                String selected = spin.getSelectedItem().toString();

                db.insertParty(partyName, partyType,partyDate,selected);
                Toast.makeText(AddPartyActivity.this, "Etkinlik Eklendi", Toast.LENGTH_SHORT).show();
            }
        });



    }
}