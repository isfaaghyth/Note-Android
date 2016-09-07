package isfaaghyth.app.note.Holder;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import isfaaghyth.app.note.AddActivity;
import isfaaghyth.app.note.R;

/**
 * Created by Isfahani on 28-Agu-16.
 */
public class BaseHolder extends RecyclerView.ViewHolder {

    public TextView txt_waktu;
    public TextView txt_judul;
    public CardView card_item_note;

    public BaseHolder(View itemView) {
        super(itemView);
        txt_waktu = (TextView) itemView.findViewById(R.id.txt_waktu);
        txt_judul = (TextView) itemView.findViewById(R.id.txt_judul);
        card_item_note = (CardView) itemView.findViewById(R.id.card_item_note);
    }

}
