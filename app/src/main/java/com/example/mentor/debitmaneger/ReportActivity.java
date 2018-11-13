package com.example.mentor.debitmaneger;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class ReportActivity extends AppCompatActivity {

    private List<RepoetPlanet> mReportPlanetlist;
    private ReportAdapter reportAdapter;
    DatabaseHelpher helpher;
    ListView listView;
    private List<Planet> mPlanetgivelist;
    private List<Planettake> mPlanettakelist;
    private ListAdapter listAdapter;
    private AdapterTake adapterTake;
    String name,amountgive,amounttake,nametake;
    String givename,takename,amtgive,amttake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        helpher = new DatabaseHelpher(this);
        listView = (ListView)findViewById(R.id.lvreport);
//        mPlanettakelist = helpher.getList1();
//        adapterTake = new AdapterTake(this, mPlanettakelist);
//        listView.setAdapter(adapterTake);


       // namedatagive();
        //namedatatake();
       // filllist();

        mReportPlanetlist = helpher.getcombList();
        reportAdapter = new ReportAdapter(this, mReportPlanetlist);
        listView.setAdapter(reportAdapter);
    }

    public void filllist()
    {
        //namedatagive();
//        int ti = listAdapter.getCount();
//        for (int i = 0; i < ti; i++)
//        {
//            name = mPlanetgivelist.get(i).getName();
//            amountgive = mPlanetgivelist.get(i).getAmount();
//        }
        try  { Thread.sleep(500);}
        catch (InterruptedException e){e.printStackTrace();}
        //namedatatake();
        int t2 = adapterTake.getCount();
        for (int i = 0; i < t2; i++)
        {
            nametake = mPlanettakelist.get(i).getName1();
            amounttake = mPlanettakelist.get(i).getAmount1();
            amountgive = mPlanettakelist.get(i).getDescription1();

//            RepoetPlanet planet1 = new RepoetPlanet(nametake,amounttake,amountgive);
//            mReportPlanetlist.add(planet1);
        }




    }

    public void filldata()
    {
        Cursor c = helpher.filllist();
        if(c != null)
        {

        }
    }




}
