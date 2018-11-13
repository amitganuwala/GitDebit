package com.example.mentor.debitmaneger;



import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class Givemoney extends FragmentActivity {


    private static final int RQS_PICK_CONTACT = 1;
    EditText editTextname,amount,description;

    ImageView imageView,img,imageViewcontact;
    Button btn,btnsubmit;

    TextView txt,textViewdt;
    DatabaseHelpher helpher;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    int year,month,day;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_givemoney);

        helpher=new DatabaseHelpher(this);

        btn = (Button) findViewById(R.id.button);
        btnsubmit = (Button) findViewById(R.id.btnsub);

        txt = (TextView) findViewById(R.id.textView);
        imageView = (ImageView)findViewById(R.id.image);
        img =(ImageView)findViewById(R.id.imgstatus);
        editTextname = (EditText) findViewById(R.id.edtname);
        amount = (EditText) findViewById(R.id.edtamount);
        description = (EditText) findViewById(R.id.edtdes);
        imageViewcontact = (ImageView)findViewById(R.id.imgcontact);
        textViewdt = (TextView)findViewById(R.id.tvgivedt);
//        Display();

        imageViewcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(Givemoney.this,ContactGiveActivity.class));

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                startActivityForResult(intent, 1);
            }
        });


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Givemoney.this,ListGiveActivity.class);
                startActivity(i);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Givemoney.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,dateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                textViewdt.setText(date);
            }
        };


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NA=editTextname.getText().toString();
                int AM=Integer.parseInt(amount.getText().toString());
                String DE=description.getText().toString();
                String DT=textViewdt.getText().toString();

                if(NA.length() != 0 && AM !=0  &&  DE.length() !=0 && DT.length() !=0)
                {
                    InsertData(NA,AM,DE,DT);

                    editTextname.setText("");
                    amount.setText("");
                    description.setText("");
                    Intent intent1 = new Intent(Givemoney.this, userdashboard.class);
                    startActivity(intent1);
                    finish();
                } else {
                    Toast.makeText(Givemoney.this, "Enter Blank Feild", Toast.LENGTH_LONG).show();
                }


                }

                });
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RQS_PICK_CONTACT){
            if(resultCode == RESULT_OK){
                Uri contactData = data.getData();
                Cursor cursor =  managedQuery(contactData, null, null, null, null);
                cursor.moveToFirst();

                String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

                editTextname.setText(name);
                //name.setText(number);
                //contactEmail.setText(email);
            }
        }
    }


    private void InsertData(String na, int am, String de,String dtg) {
        boolean isInserted = helpher.saveData(na, am, de,"",0,"",dtg,"");

        if (isInserted == true) {
            Toast.makeText(Givemoney.this, "Registerd Successfully", Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(Givemoney.this, "Registerd Failed", Toast.LENGTH_LONG).show();
        }

        boolean Inserted = helpher.GIVEMONEY(na, am, de,dtg);
        if (Inserted == true) {
            Toast.makeText(Givemoney.this, "Registerd Successfully", Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(Givemoney.this, "Registerd Failed", Toast.LENGTH_LONG).show();
        }
    }

    public void Display()
    {
        Intent intent = getIntent();
        Bundle bg1 = intent.getExtras();
        if(bg1!=null) {
            String contactname = (String) bg1.get("a1");
            editTextname.setText(contactname);
        }
    }
}


