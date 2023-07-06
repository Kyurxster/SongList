package sg.edu.rp.c346.id22020995.songlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SongList extends AppCompatActivity {
    Button btnBack;
    ListView lvSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        btnBack = findViewById(R.id.buttonBack);
        lvSongs = findViewById(R.id.listViewSongs);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(SongList.this, MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });

        DBHelper db = new DBHelper(SongList.this);
        ArrayList<Song> songList = db.getSongs();
        ArrayAdapter aaSongs = new ArrayAdapter<>(SongList.this, android.R.layout.simple_list_item_1, songList);
        lvSongs.setAdapter(aaSongs);
    }
}