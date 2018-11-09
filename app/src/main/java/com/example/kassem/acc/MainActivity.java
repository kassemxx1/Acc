package com.example.kassem.acc;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.Document;

import java.sql.Array;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {
    Map<String, Object> nameofvilaage = new HashMap<>();

          EditText clientt;
          Button uploadd;
          ArrayList<String> villages;
          ArrayList<String> clients;

    Spinner spinner ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
                 super.onCreate(savedInstanceState);
                 setContentView(R.layout.activity_main);
                 clientt=(EditText)findViewById(R.id.client);
                 uploadd=(Button)findViewById(R.id.upload);
                 villages=new  ArrayList<>();
                 clients=new ArrayList<>();
                 spinner =  (Spinner) findViewById(R.id.myspinner);
                 retrievevillage();
                 retrieveclient();
            uploadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("kkkkkkkkkkkk",""+villages);
                    Log.d("cccccccc",""+clients);

                }
            });





//
//// Application of the Array to the Spinner
      ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, villages);
       spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);

//        String sss=spinner.getSelectedItem().toString();
    }


    //
//    void add(){
//
//        nameofvilaage.put("numberofjara",7);
//        nameofvilaage.put("mabi3",10000);
//        nameofvilaage.put("den",7000);
//        nameofvilaage.put("numberofmortaja3",5);
//
//        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy hh:mm");
//        String format = s.format(new Date());
//
//        String Clienttt=clientt.getText().toString();
//        nameofvilaage.put("time",format);
//        final FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//                db.collection("zawater").document("zawtar").collection("client").document(Clienttt).collection("transaction").document("ffffff").set(nameofvilaage).addOnSuccessListener(new OnSuccessListener<Void>() {
//                  @Override
//                    public void onSuccess(Void aVoid) {
//
//                                   }
//                           });
//
//
//                     }









void retrievevillage(){
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    final Task<QuerySnapshot> querySnapshotTask = db.collection("nameofvillage")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()){

                for (DocumentSnapshot document :task.getResult()) {
                    String villagee = document.getData().get("village").toString();
                    villages.add(villagee);
                }


            }
        }
    });
}
    void retrieveclient(){
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final Task<QuerySnapshot> querySnapshotTask = db.collection("nameofclient").whereEqualTo("balda","mayfadoon")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){

                            for (DocumentSnapshot document :task.getResult()) {
                                String clientttt = document.getData().get("name").toString();
                                clients.add(clientttt);
                            }


                        }
                    }
                });
    }



}
