package com.example.kassem.acc;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.ServerTimestamp;
import com.google.firebase.firestore.model.Document;
import com.google.firestore.v1beta1.DocumentTransform;

import java.sql.Array;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {


    List<String> villages;
    ArrayList<String> clients;
    ArrayAdapter<String> spinnerArrayAdapter;
    ArrayAdapter<String> spinnerArrayAdapter2;
    String village;
    Spinner spinner2;
    TextView name;
    String client;
    String esm;
    TextView adress;
    TextView phone;
   String clienttt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asd);
        final Button sender =(Button)findViewById(R.id.send);
        final EditText inputjara3adad=(EditText)findViewById(R.id.jaratmabi3);
        final EditText inputjaratmortaja3=(EditText)findViewById(R.id.jaratmortaja3);
        final EditText inputmabi3=(EditText)findViewById(R.id.mabi3);
        final EditText inputden=(EditText)findViewById(R.id.dayn);
        final EditText inputtaskirden=(EditText)findViewById(R.id.taskirden);
         name=(TextView)findViewById(R.id.nameofclient);
         adress=(TextView)findViewById(R.id.adress);
         phone=(TextView)findViewById(R.id.phonenumber);
        villages = new ArrayList<>();
        clients = new ArrayList<>();
        Spinner spinner = (Spinner) findViewById(R.id.myspinner);
         spinner2 = (Spinner) findViewById(R.id.myspinner2);
        villages.add("");
     retrievevillage();

//add();

//// Application of the Array to the Spinner
        spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,villages);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerArrayAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 village=parent.getSelectedItem().toString();
                    clients.clear();
                    clients.add("");
                    retrieveclient(village);
                    spinerclient();


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        sender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> transcationn = new HashMap<>();
                transcationn.put("3adad eljarat", Integer.parseInt(inputjara3adad.getText().toString()));
                transcationn.put("7a2 eljarat", Integer.parseInt(inputmabi3.getText().toString()));
                transcationn.put("den", Integer.parseInt(inputden.getText().toString()));
                transcationn.put("jarat mortaja3", Integer.parseInt(inputjaratmortaja3.getText().toString()));
                transcationn.put("nameofclient",client);
                transcationn.put("taskir den", Integer.parseInt(inputtaskirden.getText().toString()));
                transcationn.put("time",Timestamp.now());
                final FirebaseFirestore db = FirebaseFirestore.getInstance();

                db.collection("transaction").document()
                        .set(transcationn).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        inputjara3adad.setText("");
                        inputmabi3.setText("");
                        inputden.setText("");
                        inputjaratmortaja3.setText("");
                        inputtaskirden.setText("");
                        name.setText("");
                        phone.setText("");
                        adress.setText("");
                    }
                });
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id =item.getItemId();
        if(id==R.id.takarir){
            Intent aboutintet=new Intent(MainActivity.this,reports.class);
            startActivity(aboutintet);
        }

        return super.onOptionsItemSelected(item);
    }
//    void add(){
//        Map<String, Object> transcationn = new HashMap<>();
//         transcationn.put("3adad eljarat",1);
//        transcationn.put("7a2 eljarat",2000);
//        transcationn.put("den",0);
//        transcationn.put("jarat mortaja3",1);
//        transcationn.put("nameofclient","kassem abboud");
//        transcationn.put("taskir den",0);
//
//        transcationn.put("time",Timestamp.now());
//        final FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//                db.collection("transaction").document()
//                        .set(transcationn).addOnSuccessListener(new OnSuccessListener<Void>() {
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
    void retrieveclient(String vl){
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final Task<QuerySnapshot> querySnapshotTask = db.collection("nameofclient").whereEqualTo("balda",vl)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){

                            for (DocumentSnapshot document :task.getResult()) {
                                clienttt = document.getData().get("name").toString();
                                clients.add(clienttt);
                            }


                        }
                    }
                });
    }
void spinerclient(){
    spinnerArrayAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,clients);
    spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner2.setAdapter(spinnerArrayAdapter2);
    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            client = parent.getSelectedItem().toString();
            retriveinformation(village,client);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });
}
void retriveinformation(String albalda, String elname){
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    final Task<QuerySnapshot> querySnapshotTask = db.collection("nameofclient").whereEqualTo("balda",albalda).whereEqualTo("name",elname)
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){

                        for (DocumentSnapshot document :task.getResult()) {
                          esm = document.getData().get("name").toString();
                            name.setText(esm);
                            String phonenumber=document.getData().get("phonenumber").toString();
                            phone.setText(phonenumber);
                            String adresss=document.getData().get("location").toString();
                            adress.setText(adresss);

                        }


                    }
                }
            });

}


}
