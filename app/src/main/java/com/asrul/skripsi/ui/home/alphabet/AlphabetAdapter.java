package com.asrul.skripsi.ui.home.alphabet;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asrul.skripsi.R;
import com.asrul.skripsi.data.alphabet.Alphabet;

import java.util.ArrayList;

public class AlphabetAdapter extends RecyclerView.Adapter<AlphabetAdapter.AlphabetViewModel> {

    private final ArrayList<Alphabet> alphabetArrayList = new ArrayList<>();

    public AlphabetAdapter(ArrayList<Alphabet> alphabetList) {
        this.alphabetArrayList.addAll(alphabetList);
    }

    @NonNull
    @Override
    public AlphabetAdapter.AlphabetViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_result_item, parent, false);
        return new AlphabetViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlphabetAdapter.AlphabetViewModel holder, int position) {
        Alphabet alphabet = alphabetArrayList.get(position);

        holder.tvChar.setText(alphabet.getAlphabet().toString());
        holder.imgSign.setImageResource(alphabet.getSign());
        holder.itemView.getLayoutParams().width = getScreenWidth(holder.itemView.getContext())/2;
    }

    @Override
    public int getItemCount() {
        return alphabetArrayList.size();
    }

    public static class AlphabetViewModel extends RecyclerView.ViewHolder {
        private final TextView tvChar;
        private final ImageView imgSign;

        public AlphabetViewModel(@NonNull View itemView) {
            super(itemView);
            tvChar = itemView.findViewById(R.id.tvWord);
            imgSign = itemView.findViewById(R.id.imgWord);
        }
    }

    private static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
