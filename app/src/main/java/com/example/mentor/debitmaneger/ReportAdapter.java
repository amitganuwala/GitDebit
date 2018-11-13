package com.example.mentor.debitmaneger;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ReportAdapter extends ArrayAdapter<RepoetPlanet> implements Filterable {
    private final List<RepoetPlanet> orgplanetList;
    private Context mContext;
    private List<RepoetPlanet> mPlanetlist;
    private PlanetFilter filter;


    public ReportAdapter(@NonNull Context mContext, List<RepoetPlanet> mPlanetlist) {
        super(mContext, R.layout.report_list);
        this.mContext = mContext;
        this.mPlanetlist = mPlanetlist;
        this.orgplanetList = mPlanetlist;


    }

    @Override
    public int getCount() {
        return mPlanetlist.size();
    }

    @Override
    public RepoetPlanet getItem(int position) {
        return mPlanetlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.report_list, null);
        TextView txtrename = (TextView) v.findViewById(R.id.tvreportname);
        TextView txtrenamet = (TextView) v.findViewById(R.id.tvnamet);
        TextView txtregiveamount = (TextView) v.findViewById(R.id.tvreportgiveamt);
        TextView txtretakeamt = (TextView) v.findViewById(R.id.tvreporttakeamt);

        txtrename.setText(mPlanetlist.get(position).getNameg());
        txtrenamet.setText(mPlanetlist.get(position).getNamet());
        txtregiveamount.setText(mPlanetlist.get(position).getGiveAmount());
        txtretakeamt.setText(mPlanetlist.get(position).getTakeAmount());

        return v;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new PlanetFilter();
        }
        return filter;

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
                List<RepoetPlanet> nPlanetList = new ArrayList<RepoetPlanet>();
                List<RepoetPlanet> nPlanetListLocal = new ArrayList<RepoetPlanet>();
                nPlanetListLocal.addAll(orgplanetList);
                final int count = nPlanetListLocal.size();
                for (int i = 0; i < count; i++) {
                    final RepoetPlanet item = nPlanetListLocal.get(i);
                    final String itemName = item.getNameg().toLowerCase();
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
        protected void publishResults(CharSequence constraints, FilterResults results) {

            if (results.count == 0)

            {
                notifyDataSetInvalidated();
            } else {
                mPlanetlist = (List<RepoetPlanet>) results.values;
                notifyDataSetInvalidated();
            }
        }
    }
}



