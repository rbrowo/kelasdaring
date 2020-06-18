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
import com.example.kelasonline.CustomFilterTanyaJawab;
import com.example.kelasonline.R;
import com.example.kelasonline.model.TanyaJawab;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by:
 * @rbrowo
 * @sherlynovia1711
 * @doctis67
 * on June 17'2020 for Final Assignment Mobile Computing
 */

public class TanyaJawabAdapter extends RecyclerView.Adapter<TanyaJawabAdapter.MyViewHolder> implements Filterable {
    public List<TanyaJawab> tjawab;
    List<TanyaJawab> tanyaFilter;
    private Context context;
    private RecyclerViewClickListener mListener;
    CustomFilterTanyaJawab filter;

    public TanyaJawabAdapter(List<TanyaJawab> tjawab, Context context, RecyclerViewClickListener listener) {
        this.tjawab = tjawab;
        this.tanyaFilter = tjawab;
        this.context = context;
        this.mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pertanyaan, parent, false);
        return new MyViewHolder(view, mListener);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.subjek.setText(tjawab.get(position).getSubjek());
        holder.matkul.setText(tjawab.get(position).getMatkul());
        holder.jawab.setText(tjawab.get(position).getJawaban());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.logo);
        requestOptions.error(R.drawable.logo);

    }

    @Override
    public int getItemCount() {
        return tjawab.size();
    }

    @Override
    public Filter getFilter() {
        if (filter==null) {
            filter=new CustomFilterTanyaJawab((ArrayList<TanyaJawab>) tanyaFilter,this);

        }
        return filter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener mListener;
        private TextView subjek, matkul, jawab;
        private RelativeLayout mRowContainer;

        public MyViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            subjek = itemView.findViewById(R.id.subjek);
            matkul = itemView.findViewById(R.id.matkul);
            jawab = itemView.findViewById(R.id.jawaban);
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