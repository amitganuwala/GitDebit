package com.example.mentor.debitmaneger;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContactGiveActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private ListView mListView;
    private ProgressDialog pDialog;
    private Handler updateBarHandler;

    List<String> contactList;
    Cursor cursor;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

//        pDialog = new ProgressDialog(this);
//        pDialog.setMessage("Reading contacts...");
//        pDialog.setCancelable(false);
//        pDialog.show();

        mListView = (ListView) findViewById(R.id.lvcontact_list);
//        updateBarHandler =new Handler();
        showContacts();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //TODO Do whatever you want with the list data
                //Toast.makeText(getApplicationContext(), "item clicked : \n"+contactList.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ContactGiveActivity.this,Givemoney.class);
                intent.putExtra("a1",contactList.get(position));
                startActivity(intent);
                finish();
            }
        });

        // Since reading contacts takes more time, let's run it on a separate thread.
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                getContacts();
//            }
//        }).start();
    }

    private void showContacts() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            // Android version is lesser than 6.0 or the permission is already granted.
            contactList = getContactNames();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactList);
            mListView.setAdapter(adapter);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                showContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private List<String> getContactNames() {
        contactList = new ArrayList<>();
        // Get the ContentResolver
        ContentResolver cr = getContentResolver();
        // Get the Cursor of all the contacts
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        // Move the cursor to first. Also check whether the cursor is empty or not.
        if (cursor.moveToFirst()) {
            // Iterate through the cursor
            do {
                // Get the contacts name
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                contactList.add(name);
            } while (cursor.moveToNext());
        }
        // Close the curosor
        cursor.close();

        return contactList;
    }

    public void getContacts() {


        contactList = new ArrayList<String>();

        String phoneNumber = null;
        String email = null;

        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

        Uri EmailCONTENT_URI =  ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
        String DATA = ContactsContract.CommonDataKinds.Email.DATA;

        StringBuffer output;

        ContentResolver contentResolver = getContentResolver();

        cursor = contentResolver.query(CONTENT_URI, null,null, null, null);

        // Iterate every contact in the phone
        if (cursor.getCount() > 0) {

            counter = 0;
            while (cursor.moveToNext()) {
                output = new StringBuffer();

                // Update the progress message
                updateBarHandler.post(new Runnable() {
                    public void run() {
                        pDialog.setMessage("Reading contacts : "+ counter++ +"/"+cursor.getCount());
                    }
                });

                String contact_id = cursor.getString(cursor.getColumnIndex( _ID ));
                String name = cursor.getString(cursor.getColumnIndex( DISPLAY_NAME )).toUpperCase();

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex( HAS_PHONE_NUMBER )));

                if (hasPhoneNumber > 0) {

                //output.append("\n First Name:" + name);
                    output.append(name);

                //This is to read multiple phone numbers associated with the same contact
                Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[] { contact_id }, null);

                while (phoneCursor.moveToNext()) {
                    phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                    //output.append("\n" + phoneNumber);

                }

                phoneCursor.close();

                // Read every email id associated with the contact
//                Cursor emailCursor = contentResolver.query(EmailCONTENT_URI,	null, EmailCONTACT_ID+ " = ?", new String[] { contact_id }, null);
//
//                while (emailCursor.moveToNext()) {
//
//                    email = emailCursor.getString(emailCursor.getColumnIndex(DATA));
//
//                    output.append("\n Email:" + email);
//
//                }
//
//                emailCursor.close();
                  }

                // Add the contact to the ArrayList
                contactList.add(output.toString());
            }

            // ListView has to be updated using a ui thread
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.contact_list, R.id.tvcontact, contactList);
                    mListView.setAdapter(adapter);
                }
            });

            // Dismiss the progressbar after 500 millisecondds
            updateBarHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    pDialog.cancel();
                }
            }, 500);
        }

    }
}
