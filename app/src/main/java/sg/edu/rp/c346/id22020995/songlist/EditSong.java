package sg.edu.rp.c346.id22020995.songlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class EditSong extends AppCompatActivity {
    EditText etID, etTitle, etSingers, etYear;
    Button btnUpdate, btnDelete, btnCancel;
    RadioGroup rgStars;
    DBHelper db;
    String title, singers;
    int id, year, stars;
    Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_song);
        etID = findViewById(R.id.editTextID);
        etTitle = findViewById(R.id.editTextTitle);
        etSingers = findViewById(R.id.editTextSingers);
        etYear = findViewById(R.id.editTextYear);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);
        btnCancel = findViewById(R.id.buttonCancel);
        rgStars = findViewById(R.id.radioGroupStars);

        title = "";
        singers = "";
        year = 0;
        stars = 0;

        Intent intentReceived = getIntent();
        song = (Song) intentReceived.getSerializableExtra("song");

        id = song.get_id();
        etID.setText(""+id);
        etTitle.setText(song.getTitle());
        etSingers.setText(song.getSingers());
        etYear.setText(""+song.getYear());
        if (song.getStars()==1){
            rgStars.check(R.id.radioButton1);
        } else if (song.getStars()==2){
            rgStars.check(R.id.radioButton2);
        } else if (song.getStars()==3){
            rgStars.check(R.id.radioButton3);
        } else if (song.getStars()==4){
            rgStars.check(R.id.radioButton4);
        } else {
            rgStars.check(R.id.radioButton5);
        }

        db = new DBHelper(EditSong.this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditSong.this);

                title = etTitle.getText().toString();
                singers = etSingers.getText().toString();
                year = Integer.parseInt(etYear.getText().toString());

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
                Song newSong = new Song (id, title, singers, year, stars);
                dbh.updateSong(newSong);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}