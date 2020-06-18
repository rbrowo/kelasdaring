package com.example.kelasonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kelasonline.R;
import com.example.kelasonline.model.Jadwal;
import java.util.List;
/**
 * Created by:
 * @rbrowo
 * @sherlynovia1711
 * @doctis67
 * on June 17'2020 for Final Assignment Mobile Computing
 */

public class JadwalAdapterM extends RecyclerView.Adapter<JadwalAdapterM.MyViewHolder> {
    private List<Jadwal> jadwal;
    private Context context;

    public JadwalAdapterM(List<Jadwal> jadwal, Context context) {
        this.jadwal = jadwal;
        this.context = context;
    }

    @Override
    public JadwalAdapterM.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_jadwal, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JadwalAdapterM.MyViewHolder holder, int position) {
        holder.kelas.setText(jadwal.get(position).getKelas());
        holder.nmdosen.setText(jadwal.get(position).getNama_dosen());
        holder.nmmatkul.setText(jadwal.get(position).getNama_matkul());
    }

    @Override
    public int getItemCount() {
        return jadwal.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView kelas, nmdosen, nmmatkul;
        public MyViewHolder(View itemView) {
            super(itemView);
            kelas = itemView.findViewById(R.id.kelas);
            nmdosen = itemView.findViewById(R.id.nmDosen);
            nmmatkul = itemView.findViewById(R.id.nmMatkul);
        }
    }
}