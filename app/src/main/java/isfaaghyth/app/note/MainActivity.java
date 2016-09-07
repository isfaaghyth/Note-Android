package isfaaghyth.app.note;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import isfaaghyth.app.note.Adapter.BaseAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_add_note) FloatingActionButton btn_add_note;
    @BindView(R.id.lst_note) RecyclerView lst_note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        lst_note.setLayoutManager(gridLayoutManager);

        DataItem();

        btn_add_note.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_refresh:
                DataItem();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_note:
                Intent intent = new Intent(this, AddActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void DataItem() {
        SQLiteNote db_note = new SQLiteNote(this);
        BaseAdapter adapter = new BaseAdapter(this, db_note.getData());
        lst_note.setAdapter(adapter);
    }
}
