package com.example.kelasonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kelasonline.R;
import com.example.kelasonline.model.Mahasiswa;
import java.util.List;
/**
 * Created by:
 * @rbrowo
 * @sherlynovia1711
 * @doctis67
 * on June 17'2020 for Final Assignment Mobile Computing
 */

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MyViewHolder> {
    private List<Mahasiswa> mhs;
    private Context context;

    public MahasiswaAdapter(List<Mahasiswa> mhs, Context context) {
        this.mhs = mhs;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mahasiswa, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(mhs.get(position).getNama());
        holder.nim.setText("NIM: "+mhs.get(position).getNim());
        holder.email.setText("E-mail: "+mhs.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return mhs.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,nim,email;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            nim = itemView.findViewById(R.id.nim);
            email = itemView.findViewById(R.id.email);
        }
    }
}