package com.example.kassem.acc;

import android.database.DataSetObserver;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asd);
         name=(TextView)findViewById(R.id.nameofclient);
         adress=(TextView)findViewById(R.id.adress);
         phone=(TextView)findViewById(R.id.phonenumber);
        villages = new ArrayList<>();
        clients = new ArrayList<>();
        Spinner spinner = (Spinner) findViewById(R.id.myspinner);
         spinner2 = (Spinner) findViewById(R.id.myspinner2);
        villages.add("");
     retrievevillage();

add();

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


    }
    void add(){
        Map<String, Object> transcationn = new HashMap<>();
         transcationn.put("3adad eljarat",3);
        transcationn.put("7a2 eljarat",3000);
        transcationn.put("den",0);
        transcationn.put("jarat mortaja3",2);
        transcationn.put("nameofclient","ali jaber");
        transcationn.put("taskir den",0);
       // transcation.put("time",DocumentTransform.FieldTransform.ServerValue.REQUEST_TIME);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

                db.collection("transaction").document()
                        .set(transcationn).addOnSuccessListener(new OnSuccessListener<Void>() {
                  @Override
                    public void onSuccess(Void aVoid) {

                                   }
                           });


                     }

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
                                String clientttt = document.getData().get("name").toString();
                                clients.add(clientttt);
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
