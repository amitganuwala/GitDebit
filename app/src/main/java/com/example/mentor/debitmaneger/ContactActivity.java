package com.example.mentor.debitmaneger;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity implements Filterable {

//    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 150;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private ListView mListView;
    private ProgressDialog pDialog;
    private Handler updateBarHandler;

    private List<String> contactList;
    private List<String> origPlanetList;
    private PlanetFilter filter;
    Cursor cursor;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        mListView = (ListView) findViewById(R.id.lvcontact_list);
        showContacts();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //TODO Do whatever you want with the list data
                //Toast.makeText(getApplicationContext(), "item clicked : \n"+contactList.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ContactActivity.this,TakeMoney.class);
                intent.putExtra("a1",contactList.get(position));
                startActivity(intent);
                finish();
            }
        });


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

    private void showContacts() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            // Android version is lesser than 6.0 or the permission is already granted.
            List<String> contacts = getContactNames();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contacts);
            mListView.setAdapter(adapter);
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


    @Override
    public Filter getFilter() {
        if (filter == null)
            filter = new PlanetFilter();

        return filter;
    }

    private class PlanetFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                // No filter implemented we return all the list
                results.values = origPlanetList;
                results.count = origPlanetList.size();
            } else {
                String prefixString = constraint.toString().toLowerCase();
                List<String> nPlanetList = new ArrayList<String>();
//                List<String> nPlanetListlocal = new ArrayList<String>();
//                nPlanetListlocal.addAll(origPlanetList);
                final int count = nPlanetList.size();
                for (int i = 0; i < count; i++) {
                    final String item = nPlanetList.get(i);
//                    final String itemName = item.getName()
//                            .toLowerCase();
                    if (item.contains(prefixString)) {
                        nPlanetList.add(item);
                    } else {
                    }
//                    final String itemMobile = item.getMobile()
//                            .toLowerCase();
//                    if (itemMobile.contains(prefixString)) {
//                        nPlanetList.add(item);
//                    } else {
//                    }
//                    final String itemArea = item.getArea()
//                            .toLowerCase();
//                    if (itemArea.contains(prefixString)) {
//                        nPlanetList.add(item);
//                    } else {
//                    }


//                String constraintString = constraint.toString().toLowerCase();
//                // We perform filtering operation
//                List<Product> nPlanetList = new ArrayList<Product>();
//
//                for (Product p : mProductList) {
//                    if (p.getName().toUpperCase().startsWith(constraint.toString().toUpperCase()))
//                        nPlanetList.add(p);
//                    if (p.getMobile().toUpperCase().startsWith(constraint.toString().toUpperCase()))
//                        nPlanetList.add(p);
//                    if (p.getSetop().toUpperCase().startsWith(constraint.toString().toUpperCase()))
//                        nPlanetList.add(p);
//                    if (p.getArea().toUpperCase().startsWith(constraint.toString().toUpperCase()))
//                        nPlanetList.add(p);
//                    if (p.getName().contains(constraintString)) {
//                        nPlanetList.add(p);
//                    }
                }

                results.values = nPlanetList;
                results.count = nPlanetList.size();

            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

        }
    }
}
