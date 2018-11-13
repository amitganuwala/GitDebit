package com.example.mentor.debitmaneger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class ListTakeActivity extends AppCompatActivity {
    ListView list;
    EditText edit;


    private List<Planettake> Planetlist;
    private AdapterTake adapterTake;
    DatabaseHelpher helpher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_take);
        helpher = new DatabaseHelpher(this);

        list = (ListView) findViewById(R.id.listtake);
        edit = (EditText)findViewById(R.id.editserach);

        Planetlist = helpher.getList1();
        adapterTake = new AdapterTake(this,Planetlist);
        list.setAdapter(adapterTake);

        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapterTake.getFilter().filter(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




    }
}
