package com.example.kassem.acc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        String dateee;
        dateee=timeee;
        kesm.setText("اسم الزبون:   "+nameee);
        kaydiye.setText("رقم القيدية:   "+idd);
        kadad.setText("عدد الجرات المباعة:   "+adad);
        kmortaja3.setText("عدد الجرات الفارغة المرتجعة:   "+mortaja333);
        kmadfou3.setText("سعر الجرات المدفوع:   "+ha2);
        kden.setText("الدين المتوجب عليه في هذا التاريخ:   "+dennn);
        ktaskrdin.setText("الدين المدفوع في هذا التاريخ:   "+taskirr);
        kemail.setText("اسم البائع:   "+emaill);
        ktime.setText("تاريخ القيدية:   "+dateee);



    }
}
