package com.example.kassem.acc;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class reports extends AppCompatActivity {
    EditText datee;
    Button getreport;

    private Calendar mcalendar;
    private int day, month, year;
    ArrayList<String> sumofjarat = new ArrayList<>();
    ArrayList<String> mabla8 = new ArrayList<>();
    ArrayList<String> tskirden = new ArrayList<>();
    int sum=0;
    int summabla8;
    int sumden;
    TextView showreport;
    TextView showmabla8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        getreport = (Button) findViewById(R.id.getdailyrepot);
       showreport = (TextView)findViewById(R.id.textView);
        showmabla8 = (TextView)findViewById(R.id.textView2);
        datee = (EditText) findViewById(R.id.inputdate);


        getreport.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View v) {

                String str = datee.getText().toString();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                sum = 0;
                summabla8=0;
                sumden=0;

                Date date = new Date();
                try {
                    date = formatter.parse(str);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date date2 = new Date();
                try {
                    date2 = formatter.parse(str);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                date.setHours(0);
                date.setMinutes(0);
                date.setSeconds(0);
                date2.setMinutes(0);
                date2.setSeconds(0);
                date2.setHours(24);
//                if (sumofjarat.size()>0){
//                sumofjarat.clear();}
                retrivesum(date,date2);
             sumofjaratt();
            sumofmabla8();
            sumofden();
                Log.d("datedatedate", "" + tskirden);

                Log.d("asdaasdasd", "" + sumden);


                showreport.setText(" مجموع الجرات المباعة: "+sum);
                showmabla8.setText("مجموع المبلغ :"+(sumden+summabla8) );
            }
        });

    }


    void retrivesum(Date date1,Date date2) {
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        final Task<QuerySnapshot> querySnapshotTask = db.collection("transaction").whereGreaterThan("time", date1).whereLessThan("time",date2)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (sumofjarat.size() > 0) {
                                sumofjarat.clear();
                                mabla8.clear();
                                tskirden.clear();
                            }

                            for (DocumentSnapshot document : task.getResult()) {

                                String i = (String) document.getData().get("3adad eljarat");
                                sumofjarat.add(i);
                                String j = (String) document.getData().get("7a2 eljarat");
                                mabla8.add(j);
                                String t = (String) document.getData().get("taskir den");
                                tskirden.add(t);
                            }


                        }
                    }
                });

    }

    Integer sumofjaratt() {

        for (int number = 0; number < sumofjarat.size(); number++) {

            int j = Integer.parseInt(sumofjarat.get(number));

            sum += j;}
            return sum;
        }
    Integer sumofmabla8() {

        for (int number = 0; number < mabla8.size(); number++) {

            int j = Integer.parseInt(mabla8.get(number));

            summabla8 += j;}
        return summabla8;
    }
    Integer sumofden() {

        for (int number = 0; number < tskirden.size(); number++) {

            int j = Integer.parseInt(tskirden.get(number));

            sumden += j;}
        return sumden;
    }



}


