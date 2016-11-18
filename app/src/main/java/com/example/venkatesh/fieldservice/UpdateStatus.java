package com.example.venkatesh.fieldservice;

import android.app.DownloadManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.client.ValueEventListener;
import com.firebase.client.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import android.util.Log;
import android.content.Intent;

import java.util.HashMap;
import java.util.Map;
import java.lang.*;
import android.text.util.*;
import com.firebase.client.Query;

/**
 * Created by venkatesh on 03-07-2016.
 */
public class UpdateStatus extends AppCompatActivity {
    private String TAG;
    Firebase ref;
    private DatabaseReference mDatabase;


    protected void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_update_status);
        final EditText dbEmpId = (EditText) findViewById(R.id.change_status_text1);
        final EditText dbEmail = (EditText) findViewById(R.id.status_email);
        final EditText dbPackageId = (EditText) findViewById(R.id.status_packageId);
        final EditText dbPackageName = (EditText) findViewById(R.id.status_packageName);
        final EditText dbStatus = (EditText) findViewById(R.id.change_status_text);
        Button changeStatus = (Button) findViewById(R.id.btn_change_status);

        Firebase.setAndroidContext(this);
        ref = new Firebase("https://planar-cell-122112.firebaseio.com/FSMDataBase");
        //mDatabase = FirebaseDatabase.getInstance().getReference().getRoot();

        changeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onPost(dbEmpId.getText().toString(), dbEmail.getText().toString(), dbPackageId.getText().toString(), dbPackageName.getText().toString(), dbStatus.getText().toString());
                //Toast.makeText(getApplicationContext(), "content updated", Toast.LENGTH_SHORT).show();
                dbEmail.setText("");
                dbEmpId.setText("");
            }
        });


    }



    void onPost(String empId, String email, String pckId, String pckName, String sts) {
        ChatMessage chat = new ChatMessage(empId, email, pckId, pckName, sts);
        //ref.child("package1").child("status").setValue("pending");
        //Toast.makeText(getApplicationContext(),"updated",Toast.LENGTH_LONG).show();
    }//end of onPost


}//endof oncreate










