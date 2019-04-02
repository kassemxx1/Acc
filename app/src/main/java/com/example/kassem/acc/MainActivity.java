package com.example.kassem.acc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.kaopiz.kprogresshud.KProgressHUD;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {


     String email;
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
    ArrayList<String> den = new ArrayList<>();
    ArrayList<String> tskirden = new ArrayList<>();
    ArrayList<String> inin = new ArrayList<>();
    ArrayList<String> outout = new ArrayList<>();
    ArrayList<String> sumofjarat=new ArrayList<>();
    ArrayList<String> mabla8 = new ArrayList<>();
    ArrayList<String> tskirdene = new ArrayList<>();
    ArrayList<String> sellers=new ArrayList<>();
    ArrayList<String> mortaja3=new ArrayList<>();
    int sumden;
    int sumtskirden;
    int sumin;
    int sumout;
    int summortaja3;
    TextView summ;
    TextView outin;
    int sum=0;
    int summabla8,sumoftskir;
    TextView jaratemail,mabla8email,mortaja3email;
    Date today,tomorow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.asd);
            email=getIntent().getExtras().getString("emailll");
            final Button sender = (Button) findViewById(R.id.send);
            final Button newzbon=(Button)findViewById(R.id.newclient);
        final Button delete=(Button)findViewById(R.id.daletebutton);
        final Button ReportsClient=(Button)findViewById(R.id.ClientReport);
        ReportsClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newintent=new Intent(MainActivity.this,ClientReports.class);
                startActivity(newintent);
            }
        });

            today = Calendar.getInstance().getTime();
            today.setHours(0);
            today.setMinutes(0);
            today.setSeconds(0);
            tomorow = Calendar.getInstance().getTime();
            tomorow.setHours(23);
            tomorow.setMinutes(59);
            tomorow.setSeconds(59);
            final EditText inputjara3adad = (EditText) findViewById(R.id.jaratmabi3);
            final EditText inputjaratmortaja3 = (EditText) findViewById(R.id.jaratmortaja3);
            final EditText inputmabi3 = (EditText) findViewById(R.id.mabi3);
            final EditText inputden = (EditText) findViewById(R.id.dayn);
            final EditText inputtaskirden = (EditText) findViewById(R.id.taskirden);
            jaratemail = (TextView) findViewById(R.id.emeiljarat);
            mabla8email = (TextView) findViewById(R.id.emailmabla8);
            mortaja3email=(TextView)findViewById(R.id.emailmortaja3);
            name = (TextView) findViewById(R.id.nameofclient);
            adress = (TextView) findViewById(R.id.adress);
            phone = (TextView) findViewById(R.id.phonenumber);
            summ = (TextView) findViewById(R.id.sum);
            villages = new ArrayList<>();
            clients = new ArrayList<>();
            final Spinner spinner = (Spinner) findViewById(R.id.myspinner);
            spinner2 = (Spinner) findViewById(R.id.myspinner2);
            outin = (TextView) findViewById(R.id.inout);
            villages.add("");
              retriveSellers();

            retrievevillage();
            retrivesumemail(today, tomorow, email);

            newzbon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newclientt = new Intent(MainActivity.this, NewClients.class);
                    startActivity(newclientt);
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newclientt = new Intent(MainActivity.this, Deletee.class);
                    startActivity(newclientt);
                }
            });

            spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, villages);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerArrayAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    village = parent.getSelectedItem().toString();
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

                        Log.d("eeeeeeeee",""+sellers);

                        retrivesumemail(today, tomorow, email);
                        Map<String, Object> transcationn = new HashMap<>();
                        if (inputjara3adad.getText().toString().equals("")) {
                            transcationn.put("3adad eljarat", "0");
                        } else {
                            transcationn.put("3adad eljarat", inputjara3adad.getText().toString());
                        }
                        if (inputmabi3.getText().toString().equals("")) {
                            transcationn.put("7a2 eljarat", "0");
                        } else {
                            transcationn.put("7a2 eljarat", inputmabi3.getText().toString());
                        }
                        if (inputden.getText().toString().equals("")) {
                            transcationn.put("den", "0");
                        } else {
                            transcationn.put("den", inputden.getText().toString());
                        }
                        if (inputjaratmortaja3.getText().toString().equals("")) {
                            transcationn.put("jarat mortaja3", "0");
                        } else {
                            transcationn.put("jarat mortaja3", inputjaratmortaja3.getText().toString());
                        }
                        if (inputtaskirden.getText().toString().equals("")) {
                            transcationn.put("taskir den", "0");
                        } else {
                            transcationn.put("taskir den", inputtaskirden.getText().toString());
                        }

                        transcationn.put("nameofclient", client);
                        transcationn.put("time", Timestamp.now());
                        transcationn.put("email", email);
                        final FirebaseFirestore db = FirebaseFirestore.getInstance();
                            final KProgressHUD hud = KProgressHUD.create(MainActivity.this)
                                    .setStyle(KProgressHUD.Style.ANNULAR_DETERMINATE)
                                    .setLabel("الرجاء الانتظار")
                                    .setMaxProgress(100)
                                    .show();

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
                                spinner.setSelection(0);
                                spinner2.setSelection(0);
                                retrivesumemail(today, tomorow, email);
                            }
                        });
                        hud.dismiss();

                    }

                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.takarir) {
            Intent aboutintet = new Intent(MainActivity.this, reports.class);
            startActivity(aboutintet);
        }

        return super.onOptionsItemSelected(item);
    }


    void retrievevillage() {
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final KProgressHUD hud = KProgressHUD.create(MainActivity.this)
                .setStyle(KProgressHUD.Style.ANNULAR_DETERMINATE)
                .setLabel("ZAWATER")
                .setMaxProgress(100)
                .show();
        final Task<QuerySnapshot> querySnapshotTask = db.collection("nameofvillage")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (DocumentSnapshot document : task.getResult()) {
                                String villagee = document.getData().get("village").toString();
                                String id=document.getId();
                                villages.add(villagee);
                            }


                        }
                    }
                });
        hud.dismiss();
    }

    void retrieveclient(String vl) {
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final KProgressHUD hud = KProgressHUD.create(MainActivity.this)
                .setStyle(KProgressHUD.Style.ANNULAR_DETERMINATE)
                .setLabel("ZAWATER")
                .setMaxProgress(100)
                .show();
        final Task<QuerySnapshot> querySnapshotTask = db.collection("nameofclient").whereEqualTo("balda", vl)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (DocumentSnapshot document : task.getResult()) {
                                clienttt = document.getData().get("name").toString();
                                clients.add(clienttt);
                            }
                            hud.dismiss();

                        }
                    }
                });

    }

    void spinerclient() {
        spinnerArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, clients);
        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(spinnerArrayAdapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                client = parent.getSelectedItem().toString();

                retriveinformation(village, client);
                retrivesum(client);
                retriveSellers();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void retriveinformation(String albalda, final String elname) {
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final Task<QuerySnapshot> querySnapshotTask = db.collection("nameofclient").whereEqualTo("balda", albalda).whereEqualTo("name", elname)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (DocumentSnapshot document : task.getResult()) {
                                esm = document.getData().get("name").toString();
                                name.setText(esm);
                                String phonenumber = document.getData().get("phonenumber").toString();
                                phone.setText(phonenumber);
                                String adresss = document.getData().get("location").toString();
                                adress.setText(adresss);


                            }


                        }
                    }
                });


    }

    void retrivesum(String cliennnt) {
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        final Task<QuerySnapshot> querySnapshotTask = db.collection("transaction").whereEqualTo("nameofclient",cliennnt)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if(den.size()>0){
                                den.clear();
                            }
                            if(tskirden.size()>0){
                                tskirden.clear();
                            }
                            if(inin.size()>0){
                                inin.clear();
                            }
                            if(outout.size()>0){
                                outout.clear();
                            }

                            for (DocumentSnapshot document : task.getResult()) {

                                String i = (String) document.getData().get("den");
                                den.add(i);

                                String t = (String) document.getData().get("taskir den");
                                tskirden.add(t);
                                String in = (String) document.getData().get("3adad eljarat");
                                String out = (String) document.getData().get("jarat mortaja3");
                                inin.add(in);
                                outout.add(out);
                            }


                        }
                        sumofden();
                        sumoftskirden();
                        ininin();
                        outoutout();
                        Log.d("ssss",""+den);


                        summ.setText("الدين المتوجب عليه :"+(sumden-sumtskirden));
                        outin.setText("الجرات الفارغة الموجودة"+(sumin-sumout));

                    }
                });


    }
    Integer sumofden() {
sumden=0;
        for (int number = 0; number < den.size(); number++) {

            int j = Integer.parseInt(den.get(number));

            sumden += j;}
        return sumden;
    }
    Integer sumoftskirden() {
        sumtskirden=0;
        for (int number = 0; number < tskirden.size(); number++) {

            int j = Integer.parseInt(tskirden.get(number));

            sumtskirden += j;}
        return sumtskirden;
    }
    Integer ininin() {
        sumin=0;
        for (int number = 0; number < inin.size(); number++) {

            int j = Integer.parseInt(inin.get(number));

            sumin += j;}
        return sumin;
    }
    Integer outoutout() {
        sumout=0;
        for (int number = 0; number < outout.size(); number++) {

            int j = Integer.parseInt(outout.get(number));

            sumout += j;}
        return sumout;
    }
    Integer sumofjaratt() {
sum=0;
        for (int number = 0; number < sumofjarat.size(); number++) {

            int j = Integer.parseInt(sumofjarat.get(number));

            sum += j;}
        return sum;
    }
    Integer sumofmabla8() {
summabla8=0;
        for (int number = 0; number < mabla8.size(); number++) {

            int j = Integer.parseInt(mabla8.get(number));

            summabla8 += j;}
        return summabla8;
    }
    Integer sumoftskirr() {
        sumoftskir=0;
        for (int number = 0; number < tskirdene.size(); number++) {

            int j = Integer.parseInt(tskirdene.get(number));
            sumoftskir += j;}
        return sumoftskir;
    }
    Integer sumofofmortaja3() {
        summortaja3=0;
        for (int number = 0; number < mortaja3.size(); number++) {

            int j = Integer.parseInt(mortaja3.get(number));
            summortaja3 += j;}
        return summortaja3;
    }

    ////////////////////////////////
    void retrivesumemail(final Date date1, final Date date2, String seller) {
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        final Task<QuerySnapshot> querySnapshotTask = db.collection("transaction").whereEqualTo("email",seller)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                                sumofjarat.clear();
                                mabla8.clear();
                                tskirdene.clear();

                                mortaja3.clear();
                            for (DocumentSnapshot document : task.getResult()) {
                                Date d=(Date) document.getTimestamp("time").toDate();
                                if(d.after(date1))  {
                                String i = (String) document.getData().get("3adad eljarat");
                                sumofjarat.add(i);
                                String j = (String) document.getData().get("7a2 eljarat");
                                mabla8.add(j);
                                String t = (String) document.getData().get("taskir den");
                                tskirdene.add(t);
                                String m = (String) document.getData().get("jarat mortaja3");
                                mortaja3.add(m);}
                            }
                            sumofofmortaja3();
                            sumofjaratt();
                            sumofmabla8();
                            sumofden();
                            sumoftskirr();
                            Log.d("pppppppppppp",""+mortaja3);
                            jaratemail.setText(" مجموع الجرات المباعة: "+sum);
                            mabla8email.setText("مجموع المبلغ :"+(summabla8+sumoftskir));
                            mortaja3email.setText("عدد الجرات الفارغة المرتجعة:"+(summortaja3));

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
                        }

                    }
                });

    }

}
