package sadaka.com.example.android.sadaka.activities;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import sadaka.com.example.android.sadaka.R;

public class MainActivity extends AppCompatActivity {
    Button calculate;
    Button tasadak;
    Button skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculate=(Button)findViewById(R.id.calculate);
        tasadak=(Button)findViewById(R.id.tasadak);
        skip=(Button)findViewById(R.id.skip);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transition = new Intent(MainActivity.this, Calculate.class);
                startActivity(transition);

            }

        });
        tasadak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transition = new Intent(MainActivity.this, Calculate.class);
                startActivity(transition);

            }

        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transition = new Intent(MainActivity.this, Organizations.class);
                startActivity(transition);

            }

        });
    }
}
