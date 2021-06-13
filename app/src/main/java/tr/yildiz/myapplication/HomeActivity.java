package tr.yildiz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    public CardView card1,card2,card3,card4,card5;
    public String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        card1 = (CardView) findViewById(R.id.c1);
        card2 = (CardView) findViewById(R.id.c2);
        card3 = (CardView) findViewById(R.id.c3);
        card5 = (CardView) findViewById(R.id.c5);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card5.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.c1 :
                intent = new Intent(getApplicationContext(), ListDrawerActivity.class);
                intent.putExtra("user_id",id);
                startActivity(intent);
                break;
            case R.id.c2:
                intent = new Intent(this,ListCabinetActivity.class);
                intent.putExtra("user_id",id);
                startActivity(intent);
                break;
            case R.id.c3:
                intent = new Intent(this, ListPartyActivity.class);
                intent.putExtra("user_id",id);
                startActivity(intent);
                break;
            case R.id.c5 :
                intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
