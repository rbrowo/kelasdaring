package com.example.kelasonline;

import android.widget.Filter;
import com.example.kelasonline.adapter.TanyaJawabAdapter;
import com.example.kelasonline.model.TanyaJawab;
import java.util.ArrayList;
/**
 * Created by:
 * @rbrowo
 * @sherlynovia1711
 * @doctis67
 * on June 17'2020 for Final Assignment Mobile Computing
 */

public class CustomFilterTanyaJawab extends Filter {
    TanyaJawabAdapter adapter;
    ArrayList<TanyaJawab> filterList;

    public CustomFilterTanyaJawab(ArrayList<TanyaJawab> filterList, TanyaJawabAdapter adapter)
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
            ArrayList<TanyaJawab> filteredTanya=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getMatkul().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredTanya.add(filterList.get(i));
                }
            }

            results.count=filteredTanya.size();
            results.values=filteredTanya;

        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }

        return results;
    }


    @Override
    protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
        adapter.tjawab= (ArrayList<TanyaJawab>) results.values;
        //REFRESH
        adapter.notifyDataSetChanged();
    }
}
