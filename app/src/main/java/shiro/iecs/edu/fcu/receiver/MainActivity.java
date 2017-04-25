package shiro.iecs.edu.fcu.receiver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.HelloText);

        Intent intent = getIntent();
        String msg =  intent.getStringExtra("GGGYYY");

        Toast.makeText(this, "R:"+ msg, Toast.LENGTH_SHORT).show();
        textView.setText(msg + " XD");
    }
}
