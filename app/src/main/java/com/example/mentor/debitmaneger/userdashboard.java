package com.example.mentor.debitmaneger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class userdashboard extends AppCompatActivity {

    Button btnreport,btngive,btntake;
    TextView txtmoney,textViewtotgive,textViewtottake;
    DatabaseHelpher helpher;
    int total,totalgive,totaldata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdashboard);
        helpher = new DatabaseHelpher(this);

        txtmoney = (TextView)findViewById(R.id.txtmoney);
        textViewtotgive = (TextView)findViewById(R.id.tvtotalgive);
        textViewtottake = (TextView)findViewById(R.id.tvtotaltake);
        btnreport=(Button)findViewById(R.id.btnre);
        btngive=(Button)findViewById(R.id.btngive);
        btntake=(Button)findViewById(R.id.btntake);


        btngive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent give=new Intent(userdashboard.this,Givemoney.class);
                startActivity(give);

            }
        });

        btntake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent take=new Intent(userdashboard.this,TakeMoney.class);
                startActivity(take);

            }
        });


        btnreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent report =new Intent(userdashboard.this,ReportActivity.class);
                startActivity(report);

            }
        });

        getsumdata();
        getsumdatagive();
        getsumdatatotal();
    }

    public void getsumdata()
    {
        total = helpher.sumData();
        textViewtottake.setText(String.valueOf(total));
    }

    public void getsumdatagive()
    {
        totalgive = helpher.sumDatagive();
        textViewtotgive.setText(String.valueOf(totalgive));
    }

    public void getsumdatatotal()
    {
        totaldata = total - totalgive;
        txtmoney.setText(String.valueOf(totaldata));
    }


}
