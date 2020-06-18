package com.example.kelasonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kelasonline.R;
import com.example.kelasonline.model.Dosen;
import java.util.List;
/**
 * Created by:
 * @rbrowo
 * @sherlynovia1711
 * @doctis67
 * on June 17'2020 for Final Assignment Mobile Computing
 */

public class DosenAdapter extends RecyclerView.Adapter<DosenAdapter.MyViewHolder> {
    private List<Dosen> dosen;
    private Context context;

    public DosenAdapter(List<Dosen> dosen, Context context) {
        this.dosen = dosen;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_dosen, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(dosen.get(position).getNama());
        holder.id_dosen.setText("ID: "+dosen.get(position).getId_dosen());
        holder.email.setText("E-mail: "+dosen.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return dosen.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,id_dosen,email;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            id_dosen = itemView.findViewById(R.id.id_dosen);
            email = itemView.findViewById(R.id.email);
        }
    }
}