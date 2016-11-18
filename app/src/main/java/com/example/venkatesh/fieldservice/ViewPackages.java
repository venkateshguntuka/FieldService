package com.example.venkatesh.fieldservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * Created by venkatesh on 04-07-2016.
 */
public class ViewPackages extends AppCompatActivity {


   private ListView listView;
   private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list_of_packages=new ArrayList<>();
    private DatabaseReference root= FirebaseDatabase.getInstance().getReference().getRoot();

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_packages);

        listView=(ListView)findViewById(R.id.listView);


        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_of_packages);
        listView.setAdapter(arrayAdapter);


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();//this can access all the package/id nodes
                while (i.hasNext()) {
                    set.add(((DataSnapshot) i.next()).getKey());//gets all the packagesId nodes
                }
                list_of_packages.clear();
                list_of_packages.addAll(set);
                ;//contains all the package id

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),PackageInfo.class);
                String selectedFromList = (String)(listView.getItemAtPosition(position));
                intent.putExtra("package",selectedFromList);
                startActivity(intent);
            }
        });
    }
}
