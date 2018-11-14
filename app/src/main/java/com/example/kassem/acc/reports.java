package com.example.kassem.acc;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class reports extends AppCompatActivity {
EditText datee;
Button getreport;
TextView showreport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        getreport=(Button)findViewById(R.id.getdailyrepot);
        showreport=(TextView)findViewById(R.id.sumofjarat);
        datee=(EditText)findViewById(R.id.inputdate);
        getreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = datee.getText().toString();
                DateFormat formatter = new SimpleDateFormat("dd:mm:yyyy");
                try {
                    Date date = formatter.parse(str);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        });

    }

    void retriveinformation(String time){
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        final Task<QuerySnapshot> querySnapshotTask = db.collection("transaction").where("time",'==',time)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){

                            for (DocumentSnapshot document :task.getResult()) {


                            }


                        }
                    }
                });

    }
}
