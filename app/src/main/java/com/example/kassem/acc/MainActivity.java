package com.example.kassem.acc;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

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
    TextView ddd;
ArrayList<String> aaa=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    ddd=(TextView)findViewById(R.id.asd);
//        String colors[] = {"Red","Blue","White","Yellow","Black", "Green","Purple","Orange","Grey"};

//// Selection of the spinner
//        Spinner spinner = (Spinner) findViewById(R.id.myspinner);
//
//// Application of the Array to the Spinner
//        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, colors);
//        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
//        spinner.setAdapter(spinnerArrayAdapter);
                 add();
                 retirirve();

        print();
    }
    void add(){

        nameofvilaage.put("numberofjara",7);
        nameofvilaage.put("mabi3",10000);
        nameofvilaage.put("den",7000);
        nameofvilaage.put("numberofmortaja3",5);

        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        String format = s.format(new Date());


        nameofvilaage.put("time",format);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("zawater").document("zawtar").collection("client").document("mariam").collection("transaction").document("ffffff").set(nameofvilaage).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });


    }
    void retirirve(){



        final FirebaseFirestore db = FirebaseFirestore.getInstance();



        final Task<QuerySnapshot> querySnapshotTask = db.collection("zawater").document("zawtar").collection("client").document("mariam").collection("transaction")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {


                                String title = document.getData().get("den").toString();
                                aaa.add(title);


                            }
                        }

                    }

                });

    }
void print(){
    int sum = 0;
    for (int i=0;i<aaa.size();i++){

        int result = Integer.parseInt(aaa.get(i));

        sum=sum+result;
    }
    String numberAsString = Integer.toString(sum);
      ddd.setText(numberAsString);
}
}
