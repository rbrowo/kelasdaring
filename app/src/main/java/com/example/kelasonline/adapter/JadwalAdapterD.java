package com.example.kelasonline.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.kelasonline.CustomFilterJadwal;
import com.example.kelasonline.R;
import com.example.kelasonline.model.Jadwal;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by:
 * @rbrowo
 * @sherlynovia1711
 * @doctis67
 * on June 17'2020 for Final Assignment Mobile Computing
 */

public class JadwalAdapterD extends RecyclerView.Adapter<JadwalAdapterD.MyViewHolder> implements Filterable {
    public List<Jadwal> jadwal;
    List<Jadwal> jadwalFilter;
    private Context context;
    private RecyclerViewClickListener mListener;
    CustomFilterJadwal filter;

    public JadwalAdapterD(List<Jadwal> jadwal, Context context, RecyclerViewClickListener listener) {
        this.jadwal = jadwal;
        this.jadwalFilter = jadwal;
        this.context = context;
        this.mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_jadwal, parent, false);
        return new MyViewHolder(view, mListener);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.mKelas.setText(jadwal.get(position).getKelas());
        holder.mNmDosen.setText(jadwal.get(position).getNama_dosen());
        holder.mNmMatkul.setText(jadwal.get(position).getNama_matkul());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.logo);
        requestOptions.error(R.drawable.logo);
    }

    @Override
    public int getItemCount() {
        return jadwal.size();
    }

    @Override
    public Filter getFilter() {
        if (filter==null) {
            filter=new CustomFilterJadwal((ArrayList<Jadwal>) jadwalFilter,this);

        }
        return filter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener mListener;
        private TextView mKelas, mNmDosen, mNmMatkul;
        private RelativeLayout mRowContainer;

        public MyViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mKelas = itemView.findViewById(R.id.kelas);
            mNmDosen = itemView.findViewById(R.id.nmDosen);
            mNmMatkul = itemView.findViewById(R.id.nmMatkul);
            mRowContainer = itemView.findViewById(R.id.row_container);

            mListener = listener;
            mRowContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.row_container:
                    mListener.onRowClick(mRowContainer, getAdapterPosition());
                    break;
                default:
                    break;
            }
        }
    }

    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
    }
}