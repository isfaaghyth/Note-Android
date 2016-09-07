package isfaaghyth.app.note;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Isfahani on 28-Agu-16.
 */
public class AddActivity extends AppCompatActivity {

    @BindView(R.id.txt_judul) TextView txt_judul;
    @BindView(R.id.txt_konten) TextView txt_konten;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        ButterKnife.bind(this);

        txt_judul.setText(getIntent().getStringExtra("judul"));
        txt_konten.setText(getIntent().getStringExtra("konten"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!txt_konten.getText().toString().isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss, dd-MM-yyyy");
            String time_now = sdf.format(new Date());
            SQLiteNote db_note = new SQLiteNote(this);
            db_note.addData(time_now, txt_judul.getText().toString(), txt_konten.getText().toString(), "null", "null");
        }
    }
}
