package com.example.tugaskuis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugaskuis.database.Kontak;

import java.util.List;

public class DaftarKontakAdapter extends RecyclerView.Adapter<DaftarKontakAdapter.ViewHolder> {

    private final List<Kontak> kontaks;

    public DaftarKontakAdapter(List<Kontak> kontaks) {
        this.kontaks = kontaks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_kontak, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Kontak kontak = kontaks.get(position);
        holder.tvNama.setText(kontak.nama);
        holder.tvNoHp.setText(kontak.noHp);
    }

    @Override
    public int getItemCount() {
        return kontaks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNama, tvNoHp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tvNama);
            tvNoHp = itemView.findViewById(R.id.tvNoHp);
        }
    }
}