package com.example.kassem.acc;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewClients extends AppCompatActivity {
    ArrayList<String> villages=new ArrayList<>();
    Spinner spinernew;
    ArrayAdapter<String> spinnerArrayAdapter;
    String village;
    Button sendnew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_clients);
        spinernew=(Spinner)findViewById(R.id.newspinner);
        sendnew=(Button)findViewById(R.id.newsend);
        final EditText newzbonn=(EditText)findViewById(R.id.newname);
        final EditText newadresss=(EditText)findViewById(R.id.newadress);
        final EditText newnumber=(EditText)findViewById(R.id.newphone);
        villages.add("");
        retrievevillage();



        spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, villages);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinernew.setAdapter(spinnerArrayAdapter);
        spinernew.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                village = parent.getSelectedItem().toString();
                sendnew.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> nameofclient = new HashMap<>();
                        nameofclient.put("name",newzbonn.getText().toString());
                        nameofclient.put("location",newadresss.getText().toString());
                        nameofclient.put("phonenumber",newnumber.getText().toString());
                        nameofclient.put("balda",village);
                        final FirebaseFirestore db = FirebaseFirestore.getInstance();
                        db.collection("nameofclient").document()
                                .set(nameofclient).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                newzbonn.setText("");
                                newadresss.setText("");
                                newnumber.setText("");
                                spinernew.setSelection(0);
                            }
                        });


                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    void retrievevillage() {
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final Task<QuerySnapshot> querySnapshotTask = db.collection("nameofvillage")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            villages.clear();
                            for (DocumentSnapshot document : task.getResult()) {
                                String villagee = document.getData().get("village").toString();
                                villages.add(villagee);
                            }


                        }
                    }
                });
    }
}
