package isfaaghyth.app.note.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import isfaaghyth.app.note.AddActivity;
import isfaaghyth.app.note.Holder.BaseHolder;
import isfaaghyth.app.note.ItemObject;
import isfaaghyth.app.note.R;
import isfaaghyth.app.note.SQLiteNote;

/**
 * Created by Isfahani on 28-Agu-16.
 */
public class BaseAdapter extends RecyclerView.Adapter<BaseHolder> {

    Context context;
    List<ItemObject> object_list;
    BaseAdapterClickListener clickListener;

    public interface BaseAdapterClickListener {
        void testClick(String waktu);
    }

    public BaseAdapter(Context context, List<ItemObject> object_list) {
        this.context = context;
        this.object_list = object_list;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_carditem, null);
        BaseHolder holder = new BaseHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, final int position) {
        holder.txt_waktu.setText(object_list.get(position).waktu);
        holder.txt_judul.setText(object_list.get(position).judul);

        holder.card_item_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddActivity.class);
                intent.putExtra("judul", object_list.get(position).judul);
                intent.putExtra("konten", object_list.get(position).konten);
                view.getContext().startActivity(intent);
            }
        });


        holder.card_item_note.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                SQLiteNote db_note = new SQLiteNote(context);
                                db_note.deleteItemSelected(object_list.get(position).waktu);
                                db_note.getData();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure to delete ?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return object_list.size();
    }
}
