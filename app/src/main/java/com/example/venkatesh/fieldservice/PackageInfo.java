package com.example.venkatesh.fieldservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Map;
import java.util.Set;

/**
 * Created by venkatesh on 05-07-2016.
 */
public class PackageInfo extends AppCompatActivity {

    private Button updateButton;
    private EditText status;
    private DatabaseReference root;
    private Firebase mref2;

    private TextView emailTextView;
    private TextView empIdTextView;
    private TextView packageIdTextView;
    private TextView statusTextView;

    private String packageId;
    private String scEmail,scEmpid,scPackageId,scStatus;
    private String temp_key;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list_of_packages2=new ArrayList<>();

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.packages);

        updateButton=(Button)findViewById(R.id.upButton);
        status=(EditText)findViewById(R.id.etStatus);
        emailTextView=(TextView)findViewById(R.id.tvEmail);
        empIdTextView=(TextView)findViewById(R.id.tvempid);
        packageIdTextView=(TextView)findViewById(R.id.tvpackageid);
        statusTextView=(TextView)findViewById(R.id.tvstatus);
        //receives the xtra info from the put extra from intent

        packageId=getIntent().getExtras().get("package").toString();

        //setting the title bar value to the current packageId
        setTitle("Package-Info:"+packageId);


        //to get the reference till the packageid node
        root= FirebaseDatabase.getInstance().getReference().child(packageId);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ustatus=status.getText().toString();
                String macha1=packageId+"executive";
                root.getRef().child(macha1).child("status").setValue(ustatus);
                status.setText("");
                Toast.makeText(getApplicationContext(),"status has been updated",Toast.LENGTH_LONG).show();
                if(ustatus.equals("delivered")) {
                    Intent intent = new Intent(PackageInfo.this, DigitalSign.class);
                    intent.putExtra("abc", packageId);
                    startActivity(intent);
                }

            }
        });


        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                append_package_view(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                append_package_view(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void  append_package_view(DataSnapshot dataSnapshot){
        Iterator i=dataSnapshot.getChildren().iterator();
        while(i.hasNext())
        {
            scEmail=(String)((DataSnapshot) i.next()).getValue();
            scEmpid=(String)((DataSnapshot) i.next()).getValue();
            scPackageId=(String)((DataSnapshot) i.next()).getValue();
            scStatus=(String)((DataSnapshot) i.next()).getValue();

            emailTextView.setText(scEmail);
            empIdTextView.setText(scEmpid);
            packageIdTextView.setText(scPackageId);
            statusTextView.setText(scStatus);
        }
    }
}
