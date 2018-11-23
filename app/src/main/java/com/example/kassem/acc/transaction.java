package com.example.kassem.acc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class transaction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        Bundle b = getIntent().getExtras();
        final TextView kesm=(TextView)findViewById(R.id.esm);
        final TextView kaydiye=(TextView)findViewById(R.id.aydiya);
        final TextView kadad=(TextView)findViewById(R.id.adad);
        final TextView kmortaja3=(TextView)findViewById(R.id.mortaj3333);
        final TextView kmadfou3=(TextView)findViewById(R.id.madfou3);
        final TextView kden=(TextView)findViewById(R.id.dennn);
        final TextView ktaskrdin=(TextView)findViewById(R.id.tskrdenn);
        final TextView kemail=(TextView)findViewById(R.id.emailll);
        final TextView ktime=(TextView)findViewById(R.id.timeeeeee);





        final String  adad=b.getString("adad eljarat");
        final String  ha2=b.getString("ha2 eljarat");
        final String  emaill=b.getString("email");
        final String  mortaja333=b.getString("jarat mortaja3");
        final String  nameee=b.getString("nameofclient");
        final String  taskirr=b.getString("taskir den");
        final String  timeee=b.getString("time");
        final String  idd=b.getString("id");
        final String  dennn=b.getString("den");
        kesm.setText(nameee);
        kaydiye.setText(idd);
        kadad.setText(adad);
        kmortaja3.setText(mortaja333);
        kmadfou3.setText(ha2);
        kden.setText(dennn);
        ktaskrdin.setText(taskirr);
        kemail.setText(emaill);
        ktime.setText(timeee);



    }
}
