package sg.edu.rp.c346.id22020995.songlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class SongList extends AppCompatActivity {
    Button btnBack, btnAll, btn5stars;
    ListView lvSongs;
    ArrayList<Song> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        btnBack = findViewById(R.id.buttonBack);
        lvSongs = findViewById(R.id.listViewSongs);
        btnAll = findViewById(R.id.buttonAll);
        btn5stars = findViewById(R.id.button5stars);

        DBHelper db = new DBHelper(SongList.this);
        songList = new ArrayList<Song>();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SongList.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songList = db.getSongs();
                ArrayAdapter aaSongs = new ArrayAdapter<>(SongList.this, android.R.layout.simple_list_item_1, songList);
                lvSongs.setAdapter(aaSongs);
            }
        });

        btn5stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songList = db.get5starSongs();
                ArrayAdapter aaSongs = new ArrayAdapter<>(SongList.this, android.R.layout.simple_list_item_1, songList);
                lvSongs.setAdapter(aaSongs);
            }
        });

        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song song = songList.get(position);
                Intent i = new Intent(SongList.this,
                        EditSong.class);
                i.putExtra("song", song);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        btnAll.performClick();
    }
}