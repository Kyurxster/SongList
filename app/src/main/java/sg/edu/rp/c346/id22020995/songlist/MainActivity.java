package sg.edu.rp.c346.id22020995.songlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etTitle, etSingers, etYear;
    RadioGroup rgStars;
    Button btnInsert, btnShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.editTextTitle);
        etSingers = findViewById(R.id.editTextSingers);
        etYear = findViewById(R.id.editTextYear);
        rgStars = findViewById(R.id.radioGroupStars);
        btnInsert = findViewById(R.id.buttonInsert);
        btnShowList = findViewById(R.id.buttonShowList);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Get inputs
                String title = etTitle.getText().toString();
                String singers = etSingers.getText().toString();
                int year =  Integer.parseInt(etYear.getText().toString());
                int stars = 0;
                if (rgStars.getCheckedRadioButtonId()==R.id.radioButton1){
                    stars = 1;
                } else if (rgStars.getCheckedRadioButtonId()==R.id.radioButton2){
                    stars = 2;
                } else if (rgStars.getCheckedRadioButtonId()==R.id.radioButton3){
                    stars = 3;
                } else if (rgStars.getCheckedRadioButtonId()==R.id.radioButton4){
                    stars = 4;
                } else if (rgStars.getCheckedRadioButtonId()==R.id.radioButton5){
                    stars = 5;
                }

                // Clear editText inputs
                etTitle.setText("");
                etSingers.setText("");
                etYear.setText("");
                rgStars.clearCheck();

                // Insert a task
                db.insertSong(title, singers, year, stars);
                Log.i("Song Database", title+" inserted into database");
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SongList.class);
                startActivity(intent);
            }
        });
    }
}