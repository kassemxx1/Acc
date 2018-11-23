package com.example.kassem.acc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Deletee extends AppCompatActivity {
    ArrayList<CustomClass> news = new ArrayList<>();
    Date today,tomorow;
    String email="zaher";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletee);

        final ListView list = findViewById(R.id.listview);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        today = Calendar.getInstance().getTime();
        today.setHours(0);
        today.setMinutes(0);
        today.setSeconds(0);
        tomorow = Calendar.getInstance().getTime();
        tomorow.setHours(23);
        tomorow.setMinutes(59);
        tomorow.setSeconds(59);


        final Task<QuerySnapshot> querySnapshotTask = db.collection("transaction").whereGreaterThan("time",today).whereLessThan("time",tomorow)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            news.clear();
                            for (DocumentSnapshot document : task.getResult()) {

                                String e = (String) document.getData().get("email");
                                if (e.equals(email)) {

                                    String nameeeee = document.getData().get("nameofclient").toString();

                                    String adaddddd = document.getData().get("3adad eljarat").toString();

                                    Date timeeeee = (Date) document.getData().get("time");

                                    String id = document.getId();


                                    news.add(0, new CustomClass(nameeeee, adaddddd, timeeeee, id));
                                }





                                baseadapter adapter = new baseadapter();
                                list.setAdapter(adapter);
                                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                                        Bundle mybundle = new Bundle();
                                        mybundle.putString("id", news.get(position).id);
                                        Log.d("asaasas",news.get(position).id);
                                        Intent myintet=new Intent(Deletee.this,pop.class);
                                        myintet.putExtras(mybundle);
                                        startActivity(myintet);



                                    }
                                });
                            }
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
            nameeee.setText(news.get(position).namee);
            jaratttt.setText(news.get(position).jarat3adad);
            String tttt=news.get(position).datee.toString();
             dateeee.setText(tttt);
            return view;

        }
    }
}
