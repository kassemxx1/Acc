package com.example.kassem.acc;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.kaopiz.kprogresshud.KProgressHUD;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class ClientReports extends AppCompatActivity {
    String clienttt;
    ArrayList<String> clients=new ArrayList<>();
    ArrayAdapter<String> spinnerArrayAdapter;
    ArrayList<customclient> news = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_reports);
        Collections.sort(clients, String.CASE_INSENSITIVE_ORDER);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final AutoCompleteTextView textauto=(AutoCompleteTextView)findViewById(R.id.autotext);
        final ListView list = findViewById(R.id.clientList);
        clients.add("");
        retrieveclient();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line, clients);
        textauto.setAdapter(adapter);

        textauto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                final String client = parent.getAdapter().getItem(position).toString();
                                                final KProgressHUD hud = KProgressHUD.create(ClientReports.this)
                                                        .setStyle(KProgressHUD.Style.ANNULAR_DETERMINATE)
                                                        .setLabel("ZAWATER")
                                                        .setMaxProgress(100)
                                                        .show();
                                                final Task<QuerySnapshot> querySnapshotTask = db.collection("transaction").orderBy("time")
                                                        .get()
                                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                if (task.isSuccessful()) {
                                                                    news.clear();
                                                                    for (DocumentSnapshot document : task.getResult()) {
                                                                        String nameeeee = document.getData().get("nameofclient").toString();
                                                                        if (nameeeee != null & nameeeee.equals(client)) {
                                                                            String e = (String) document.getData().get("email");


                                                                            String adaddddd = document.getData().get("3adad eljarat").toString();

                                                                            Date timeeeee = (Date) document.getData().get("time");
                                                                            String ha2 = document.getData().get("7a2 eljarat").toString();
                                                                            String denn = document.getData().get("den").toString();
                                                                            String morttaja3 = document.getData().get("jarat mortaja3").toString();
                                                                            String taskir = document.getData().get("taskir den").toString();

                                                                            String idd = document.getId();


                                                                            news.add(0, new customclient(adaddddd, ha2, denn, e, morttaja3, nameeeee, taskir, timeeeee, idd));

                                                                        } else if (nameeeee == null) {
                                                                            news.add(0, new customclient("", "", "", "", "", "", "", Calendar.getInstance().getTime(), ""));
                                                                        }


                                                                        baseadapter adapter = new baseadapter();
                                                                        list.setAdapter(adapter);
                                                                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                                            @Override
                                                                            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                                                                                Bundle mybundle = new Bundle();
                                                                                mybundle.putString("adad eljarat", news.get(position).adad_eljarat);
                                                                                mybundle.putString("ha2 eljarat", news.get(position).ha2_eljarat);
                                                                                mybundle.putString("den", news.get(position).den);
                                                                                mybundle.putString("email", news.get(position).email);
                                                                                mybundle.putString("jarat mortaja3", news.get(position).jarat_mortaja3);
                                                                                mybundle.putString("nameofclient", news.get(position).nameofclientt);
                                                                                mybundle.putString("taskir den", news.get(position).taskir_den);
                                                                                mybundle.putString("time", news.get(position).timee.toString());
                                                                                mybundle.putString("id", news.get(position).id);


                                                                                Intent myintet = new Intent(ClientReports.this, transaction.class);
                                                                                myintet.putExtras(mybundle);
                                                                                startActivity(myintet);


                                                                            }
                                                                        });
                                                                    }
                                                                }
                                                                hud.dismiss();
                                                            }
                                                        });
                                            }
                                        });


/////////////////////////////////////////////////////////////////////////

//                spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, clients);
//                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                spinnerclient.setAdapter(spinnerArrayAdapter);
//                spinnerclient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view, int position, final long id) {
//                        final String client = parent.getSelectedItem().toString();
//
//                        final Task<QuerySnapshot> querySnapshotTask = db.collection("transaction").orderBy("time")
//                                .get()
//                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                        if (task.isSuccessful()) {
//                                            news.clear();
//                                            for (DocumentSnapshot document : task.getResult()) {
//                                                String nameeeee = document.getData().get("nameofclient").toString();
//                                                if (nameeeee != null & nameeeee.equals(client)) {
//                                                    String e = (String) document.getData().get("email");
//
//
//                                                    String adaddddd = document.getData().get("3adad eljarat").toString();
//
//                                                    Date timeeeee = (Date) document.getData().get("time");
//                                                    String ha2 = document.getData().get("7a2 eljarat").toString();
//                                                    String denn = document.getData().get("den").toString();
//                                                    String morttaja3 = document.getData().get("jarat mortaja3").toString();
//                                                    String taskir = document.getData().get("taskir den").toString();
//
//                                                    String idd = document.getId();
//
//
//                                                    news.add(0, new customclient(adaddddd, ha2, denn, e, morttaja3, nameeeee, taskir, timeeeee, idd));
//
//                                                } else if (nameeeee == null) {
//                                                    news.add(0, new customclient("", "", "", "", "", "", "", Calendar.getInstance().getTime(), ""));
//                                                }
//
//
//                                                baseadapter adapter = new baseadapter();
//                                                list.setAdapter(adapter);
//                                                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                                    @Override
//                                                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//                                                        Bundle mybundle = new Bundle();
//                                                        mybundle.putString("adad eljarat", news.get(position).adad_eljarat);
//                                                        mybundle.putString("ha2 eljarat", news.get(position).ha2_eljarat);
//                                                        mybundle.putString("den", news.get(position).den);
//                                                        mybundle.putString("email", news.get(position).email);
//                                                        mybundle.putString("jarat mortaja3", news.get(position).jarat_mortaja3);
//                                                        mybundle.putString("nameofclient", news.get(position).nameofclientt);
//                                                        mybundle.putString("taskir den", news.get(position).taskir_den);
//                                                        mybundle.putString("time", news.get(position).timee.toString());
//                                                        mybundle.putString("id", news.get(position).id);
//
//
//                                                        Intent myintet = new Intent(ClientReports.this, transaction.class);
//                                                        myintet.putExtras(mybundle);
//                                                        startActivity(myintet);
//
//
//                                                    }
//                                                });
//                                            }
//                                        }
//
//                                    }
//                                });
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//
//                    }
//                });






    }





                //////////////////////////////////////////////////////////////////

    void retrieveclient() {
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final KProgressHUD hud = KProgressHUD.create(ClientReports.this)
                .setStyle(KProgressHUD.Style.ANNULAR_DETERMINATE)
                .setLabel("ZAWATER")
                .setMaxProgress(100)
                .show();
        final Task<QuerySnapshot> querySnapshotTask = db.collection("nameofclient")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            clients.clear();

                            for (DocumentSnapshot document : task.getResult()) {
                                clienttt = document.getData().get("name").toString();
                                clients.add(clienttt);
                            }

                            Collections.sort(clients, String.CASE_INSENSITIVE_ORDER);
                            hud.dismiss();
                        }
                    }
                });
    }
    class baseadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return news.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            View view = getLayoutInflater().inflate(R.layout.custom_cell, null);//3arafna l custom cell
            TextView nameeee = view.findViewById(R.id.nameee);
            TextView jaratttt = view.findViewById(R.id.adaddd);
            TextView dateeee = view.findViewById(R.id.dateee);
            nameeee.setText(news.get(position).email);
            jaratttt.setText(news.get(position).nameofclientt);
            String tttt=news.get(position).timee.toString();
            dateeee.setText(tttt);
            return view;

        }
    }
}
