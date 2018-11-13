package com.example.mentor.debitmaneger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ListGiveActivity extends AppCompatActivity {

    ListView list;
    EditText edit;
    double total;
    TextView textViewtotamt;
    private List<Planet> mPlanetlist;
    private ListAdapter listAdapter;
    DatabaseHelpher helpher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_give);

        helpher = new DatabaseHelpher(this);

        list = (ListView) findViewById(R.id.list);
        edit = (EditText)findViewById(R.id.serch);
        textViewtotamt = (TextView)findViewById(R.id.tvtotamt);

        mPlanetlist = helpher.getList();
        listAdapter = new ListAdapter(this, mPlanetlist);
        list.setAdapter(listAdapter);

        getTotal();

        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listAdapter.getFilter().filter(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
    public void getTotal()
    {
        total = 0.0;
        for(int i=0; i<mPlanetlist.size(); i++){
            total = total +Double.parseDouble(mPlanetlist.get(i).getAmount());
            textViewtotamt.setText(Integer.toString((int) total) );

        }
        //return total;
    }

    public void passing()
    {
        Intent intent = new Intent(ListGiveActivity.this,userdashboard.class);
        intent.putExtra("t1",total);
        startActivity(intent);
    }



}
