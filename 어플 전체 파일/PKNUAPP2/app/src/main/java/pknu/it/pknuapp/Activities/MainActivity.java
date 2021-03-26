package pknu.it.pknuapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pknu.it.pknuapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginBT =(Button) findViewById(R.id.login);
        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(LoginIntent);
            }
        });

        Button RegBT =(Button) findViewById(R.id.join);
        RegBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RegIntent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(RegIntent);
            }
        });
    }
}
