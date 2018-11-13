package com.example.mentor.debitmaneger;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterTake extends ArrayAdapter<Planettake> implements Filterable {
    private final List<Planettake> orgplanetList;
    private Context mcontext;
    private List<Planettake> Planetlist;
    private PlanetFilter filter;


    public AdapterTake(Context context, List<Planettake> Planetlist) {
        super(context, R.layout.takelist);
        this.mcontext = context;
        this.Planetlist = Planetlist;
        this.orgplanetList = Planetlist;
    }

    @Override
    public int getCount() {
        return Planetlist.size();
    }

    @Nullable
    @Override
    public Planettake getItem(int position) {

        return Planetlist.get(position);
    }


    @Override
    public long getItemId(int position) {
        return Planetlist.get(position).getId();
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        View v = View.inflate(mcontext, R.layout.takelist, null);
        TextView txttname = (TextView) v.findViewById(R.id.tvtname);
        TextView txttamount = (TextView) v.findViewById(R.id.tvtamt);
        TextView txtdest = (TextView) v.findViewById(R.id.tvtdescrip);
        TextView txtdtt = (TextView) v.findViewById(R.id.tvdatet);


        txttname.setText(Planetlist.get(position).getName1());
        txttamount.setText(Planetlist.get(position).getAmount1());
        txtdest.setText(Planetlist.get(position).getDescription1());
        txtdtt.setText(Planetlist.get(position).getTdate());


        return v;
    }

    private class PlanetFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = orgplanetList;
                results.values = orgplanetList.size();
            } else {
                String perfixString = constraint.toString().toLowerCase();
                List<Planettake> nPlanetList = new ArrayList<Planettake>();
                List<Planettake> nPlanetListLocal = new ArrayList<Planettake>();
                nPlanetListLocal.addAll(orgplanetList);
                final int count = nPlanetListLocal.size();
                for (int i = 0; i < count; i++) {
                    final Planettake item = nPlanetListLocal.get(i);
                    final String itemName = item.getName1().toLowerCase();
                    if (itemName.contains(perfixString)) {
                        nPlanetList.add(item);
                    }

                }
                results.values = nPlanetList;
                results.count = nPlanetList.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0)

            {
                notifyDataSetInvalidated();
            } else {
                Planetlist = (List<Planettake>) results.values;
                notifyDataSetInvalidated();
            }

        }
    }
}
