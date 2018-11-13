package com.example.mentor.debitmaneger;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class TakeMoney extends AppCompatActivity {

    private static final int RQS_PICK_CONTACT = 1;
    DatabaseHelpher helpher;
    static EditText date;
    EditText editTextname, amount, description;
    TextView textViewname;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    Button btn, btnsubmit1;
    private int year, month, day;
    ImageView imageView,imageViewtakecont;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    //contact list
    private static final int RESULT_PICK_CONTACT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_money);
        helpher = new DatabaseHelpher(this);

        btn = (Button) findViewById(R.id.btnsetdate);
        btnsubmit1 = (Button) findViewById(R.id.btnsub1);
        editTextname = (EditText) findViewById(R.id.edtname);
        amount = (EditText) findViewById(R.id.edtamount);
        description = (EditText) findViewById(R.id.edtdes);
        dateView = (TextView) findViewById(R.id.txtselect);

        imageView =(ImageView)findViewById(R.id.imageView);
        imageViewtakecont = (ImageView)findViewById(R.id.imgtakecontact);
//        Display();

        imageViewtakecont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(TakeMoney.this,ContactActivity.class));
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                startActivityForResult(intent, 1);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent img = new Intent(TakeMoney.this,ListTakeActivity.class);
                startActivity(img);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(TakeMoney.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,dateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                dateView.setText(date);
            }
        };

        btnsubmit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NA1 = editTextname.getText().toString();
                int AM1 = Integer.parseInt(amount.getText().toString());
                String DE1 = description.getText().toString();
                String DTT = dateView.getText().toString();

                if (NA1.length() !=0 && AM1 != 0 && DTT.length() !=0) {
                    InsertData(NA1,AM1, DE1,DTT);

                    editTextname.setText("");
                    amount.setText("");
                    description.setText("");
                    Intent intent1 = new Intent(TakeMoney.this, userdashboard.class);
                    startActivity(intent1);
                    finish();
                } else {
                    Toast.makeText(TakeMoney.this, "Enter Blank Feild", Toast.LENGTH_LONG).show();
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

    private void InsertData (String na1, int am1, String de1,String dtt)
    {
        boolean isInserted = helpher.saveData("",0,"",na1, am1, de1,"",dtt);
        if (isInserted == true) {

            Toast.makeText(TakeMoney.this, "Submit Successfully", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(TakeMoney.this, "Submit Failed", Toast.LENGTH_LONG).show();
        }

        boolean Inserted = helpher.TAKEMONEY(na1, am1, de1,dtt);
        if (Inserted == true) {

            Toast.makeText(TakeMoney.this, "Submit Successfully", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(TakeMoney.this, "Submit Failed", Toast.LENGTH_LONG).show();
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
