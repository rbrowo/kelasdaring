package com.example.kelasonline;

import android.widget.Filter;
import com.example.kelasonline.adapter.JadwalAdapterD;
import com.example.kelasonline.model.Jadwal;
import java.util.ArrayList;
/**
 * Created by:
 * @rbrowo
 * @sherlynovia1711
 * @doctis67
 * on June 17'2020 for Final Assignment Mobile Computing
 */

public class CustomFilterJadwal extends Filter {
    JadwalAdapterD adapter;
    ArrayList<Jadwal> filterList;

    public CustomFilterJadwal(ArrayList<Jadwal> filterList, JadwalAdapterD adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }

    //FILTERING OCURS
    @Override
    protected Filter.FilterResults performFiltering(CharSequence constraint) {
        Filter.FilterResults results=new Filter.FilterResults();
        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Jadwal> filteredJadwal=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getNama_matkul().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredJadwal.add(filterList.get(i));
                }
            }

            results.count=filteredJadwal.size();
            results.values=filteredJadwal;

        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }

        return results;
    }


    @Override
    protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
        adapter.jadwal= (ArrayList<Jadwal>) results.values;
        //REFRESH
        adapter.notifyDataSetChanged();
    }
}
