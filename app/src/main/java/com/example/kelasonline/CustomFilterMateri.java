package com.example.kelasonline;

import android.widget.Filter;
import com.example.kelasonline.adapter.MateriAdapter;
import com.example.kelasonline.model.Materi;
import java.util.ArrayList;
/**
 * Created by:
 * @rbrowo
 * @sherlynovia1711
 * @doctis67
 * on June 17'2020 for Final Assignment Mobile Computing
 */

public class CustomFilterMateri extends Filter {
    MateriAdapter adapter;
    ArrayList<Materi> filterList;

    public CustomFilterMateri(ArrayList<Materi> filterList, MateriAdapter adapter)
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
            ArrayList<Materi> filteredMateri=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getMatkul().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredMateri.add(filterList.get(i));
                }
            }

            results.count=filteredMateri.size();
            results.values=filteredMateri;

        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }

        return results;
    }


    @Override
    protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
        adapter.materis= (ArrayList<Materi>) results.values;
        //REFRESH
        adapter.notifyDataSetChanged();
    }
}
