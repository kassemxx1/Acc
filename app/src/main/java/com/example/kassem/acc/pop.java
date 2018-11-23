package com.example.kassem.acc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class pop extends Activity {


    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindows);
        final Button ta2kid=(Button)findViewById(R.id.buttonok);
        final Button cancel=(Button)findViewById(R.id.buttoncancel);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        Bundle b = getIntent().getExtras();
     final String  idd=b.getString("id");
        ta2kid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.collection("transaction").document(idd)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                               finish();
                                Intent backintet=new Intent(pop.this,Deletee.class);
                                startActivity(backintet);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("321321", "Error deleting document", e);
                            }
                        });

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        DisplayMetrics dm =new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.widthPixels;
        getWindow().setLayout(width, (int) (height*.3));
    }
}
