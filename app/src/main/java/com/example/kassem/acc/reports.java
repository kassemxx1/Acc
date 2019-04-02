package com.example.kassem.acc;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class reports extends AppCompatActivity {
    EditText datee;
    Button getreport;
    EditText startdate;
    EditText enddate;
    Button reportgeneral;

    private Calendar mcalendar;
    private int day, month, year;
    ArrayList<String> sumofjarat = new ArrayList<>();
    ArrayList<String> mabla8 = new ArrayList<>();
    ArrayList<String> tskirden = new ArrayList<>();
    ArrayList<String> generaljarat = new ArrayList<>();
    ArrayList<String> generalmabla8 = new ArrayList<>();
    ArrayList<String> generalmorataja3 = new ArrayList<>();
    ArrayList<String> generalden = new ArrayList<>();
    ArrayList<String> sellers = new ArrayList<>();
    ArrayAdapter<String> spinnerArrayAdapter;
    TextView ogeneralmabla8;
    TextView ogeneralmorataja3;
    TextView ogeneralden;
    TextView ogeneraljarat;
    int gsumjarat;
    int gsummabla8;
    int gsumden;
    int gsummortaja3;
    int sum = 0;
    int summabla8;
    int sumden;
    TextView showreport;
    TextView showmabla8;
    String sellerr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        getreport = (Button) findViewById(R.id.getdailyrepot);
        showreport = (TextView) findViewById(R.id.textView);
        showmabla8 = (TextView) findViewById(R.id.textView2);
        datee = (EditText) findViewById(R.id.inputdate);
        startdate = (EditText) findViewById(R.id.datestart);
        enddate = (EditText) findViewById(R.id.dateend);
        reportgeneral = (Button) findViewById(R.id.generalreport);
        ogeneralden = (TextView) findViewById(R.id.allden);
        ogeneraljarat = (TextView) findViewById(R.id.alljarat);
        ogeneralmabla8 = (TextView) findViewById(R.id.allammount);
        ogeneralmorataja3 = (TextView) findViewById(R.id.allfare8);
        sellers.add("");
        retriveSellers();
        final Spinner spinerselers = (Spinner) findViewById(R.id.sellerspinner);

        spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sellers);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerselers.setAdapter(spinnerArrayAdapter);
        spinerselers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getSelectedItem().toString()=="all") {
                    reportgeneral.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            gsumden = 0;
                            gsumjarat = 0;
                            gsummabla8 = 0;
                            gsummortaja3 = 0;
                            String star = startdate.getText().toString();
                            String end = enddate.getText().toString();
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            Date datestart = new Date();
                            Date dateends = new Date();
                            try {
                                datestart = formatter.parse(star);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                dateends = formatter.parse(end);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            datestart.setHours(0);
                            datestart.setMinutes(0);
                            datestart.setSeconds(0);
                            dateends.setHours(23);
                            dateends.setMinutes(59);
                            dateends.setSeconds(59);

                            retrivegeneral(datestart, dateends);

                        }
                    });

                                 }
                    else {
                    sellerr = parent.getSelectedItem().toString();
                    Log.d("bbbbbbbbb",""+sellerr);
                    reportgeneral.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            gsumden = 0;
                            gsumjarat = 0;
                            gsummabla8 = 0;
                            gsummortaja3 = 0;
                            String star = startdate.getText().toString();
                            String end = enddate.getText().toString();
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            Date datestart = new Date();
                            Date dateends = new Date();
                            try {
                                datestart = formatter.parse(star);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                dateends = formatter.parse(end);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            datestart.setHours(0);
                            datestart.setMinutes(0);
                            datestart.setSeconds(0);
                            dateends.setHours(23);
                            dateends.setMinutes(59);
                            dateends.setSeconds(59);

                           retrivegeneralwithseller(datestart,dateends,sellerr);

                        }
                    });

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


/////////////////////////////////////////////////////////////////
        getreport.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View v) {

                String str = datee.getText().toString();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                sum = 0;
                summabla8 = 0;
                sumden = 0;

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
                retrivesum(date, date2);


                Log.d("datedatedate", "" + tskirden);

                Log.d("asdaasdasd", "" + sumden);


            }
        });
        /////////////////////////////////////////


    }


    void retrivesum(Date date1, Date date2) {
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        final Task<QuerySnapshot> querySnapshotTask = db.collection("transaction").whereGreaterThan("time", date1).whereLessThan("time", date2)
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

                            sumofjaratt();
                            sumofmabla8();
                            sumofden();
                            showreport.setText(" مجموع الجرات المباعة: " + sum);
                            showmabla8.setText("مجموع المبلغ :" + (sumden + summabla8));
                        }
                    }
                });

    }

    Integer sumofjaratt() {

        for (int number = 0; number < sumofjarat.size(); number++) {

            int j = Integer.parseInt(sumofjarat.get(number));

            sum += j;
        }
        return sum;
    }

    Integer sumofmabla8() {

        for (int number = 0; number < mabla8.size(); number++) {

            int j = Integer.parseInt(mabla8.get(number));

            summabla8 += j;
        }
        return summabla8;
    }

    Integer sumofgden() {

        for (int number = 0; number < generalden.size(); number++) {

            int j = Integer.parseInt(generalden.get(number));

            gsumden += j;
        }
        return gsumden;
    }

    Integer sumofgmabla8() {

        for (int number = 0; number < generalmabla8.size(); number++) {

            int j = Integer.parseInt(generalmabla8.get(number));

            gsummabla8 += j;
        }
        return gsummabla8;
    }

    Integer sumofgjarat() {

        for (int number = 0; number < generaljarat.size(); number++) {

            int j = Integer.parseInt(generaljarat.get(number));

            gsumjarat += j;
        }
        return gsumjarat;
    }

    Integer sumofgmortaja3() {

        for (int number = 0; number < generalmorataja3.size(); number++) {

            int j = Integer.parseInt(generalmorataja3.get(number));

            gsummortaja3 += j;
        }
        return gsummortaja3;
    }

    Integer sumofden() {

        for (int number = 0; number < tskirden.size(); number++) {

            int j = Integer.parseInt(tskirden.get(number));

            sumden += j;
        }
        return sumden;
    }


    void retrivegeneral(Date date1, Date date2) {
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        final Task<QuerySnapshot> querySnapshotTask = db.collection("transaction").whereGreaterThan("time", date1).whereLessThan("time", date2)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            generalmabla8.clear();
                            generaljarat.clear();
                            generalden.clear();
                            generalmorataja3.clear();


                            for (DocumentSnapshot document : task.getResult()) {

                                String i = (String) document.getData().get("3adad eljarat");
                                generaljarat.add(i);
                                String j = (String) document.getData().get("7a2 eljarat");
                                generalmabla8.add(j);
                                String t = (String) document.getData().get("taskir den");
                                generalmabla8.add(t);
                                String d = (String) document.getData().get("den");
                                generalden.add(d);
                                String m = (String) document.getData().get("jarat mortaja3");
                                generalmorataja3.add(m);
                            }

                            sumofgden();
                            sumofgjarat();
                            sumofgmabla8();
                            sumofgmortaja3();
                            ogeneralden.setText("مجموع الدين:" + gsumden);
                            ogeneraljarat.setText("مجموع الجرات المباعة:" + gsumjarat);
                            ogeneralmabla8.setText("مجموع المبلغ: " + gsummabla8);
                            ogeneralmorataja3.setText("مجموع الجرات الفارغة:" + gsummortaja3);
                        }
                    }
                });

    }

    void retriveSellers() {
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        final Task<QuerySnapshot> querySnapshotTask = db.collection("seller")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        sellers.clear();
                        sellers.add("all");


                        for (DocumentSnapshot document : task.getResult()) {

                            String seler = (String) document.getData().get("seller");
                            sellers.add(seler);
                        Log.d("aaaaaaaaa",""+sellers);
                        }

                    }
                });

    }

    void retrivegeneralwithseller(final Date date1, final Date date2, final String seller) {
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        final Task<QuerySnapshot> querySnapshotTask = db.collection("transaction").whereGreaterThan("time",date1).whereLessThan("time",date2)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            generalmabla8.clear();
                            generaljarat.clear();
                            generalden.clear();
                            generalmorataja3.clear();


                            for (DocumentSnapshot document : task.getResult()) {
//                                Date time=(Time) document.getData().get("time");
                                String e = (String) document.getData().get("email");
                                    Log.d("aaaaaaa",""+e);
                                    if (e.equals(seller)) {
                                        String i = (String) document.getData().get("3adad eljarat");
                                        generaljarat.add(i);
                                        String j = (String) document.getData().get("7a2 eljarat");
                                        generalmabla8.add(j);
                                        String t = (String) document.getData().get("taskir den");
                                        generalmabla8.add(t);
                                        String g = (String) document.getData().get("den");
                                        generalden.add(g);
                                        String m = (String) document.getData().get("jarat mortaja3");
                                        generalmorataja3.add(m);
                                   }

                            }

                            sumofgden();
                            sumofgjarat();
                            sumofgmabla8();
                            sumofgmortaja3();
                            ogeneralden.setText("مجموع الدين:" + gsumden);
                            ogeneraljarat.setText("مجموع الجرات المباعة:" + gsumjarat);
                            ogeneralmabla8.setText("مجموع المبلغ: " + gsummabla8);
                            ogeneralmorataja3.setText("مجموع الجرات الفارغة:" + gsummortaja3);
                        }
                    }
                });


    }

}


