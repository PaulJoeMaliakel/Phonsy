package com.example.vaishnavj.phonesy;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public TextView t1, t2;
    public EditText ed1,ed2,ed3,ed4,ed5;
    Button b1,b2;
    public String Phone1 ;


    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t2=(TextView)findViewById(R.id.y);
        //b1=(Button) findViewById(R.id.button1);
        b2=(Button) findViewById(R.id.button2);
        //final ContactInfoDB cid=new ContactInfoDB(this);
        //final SQLiteDatabase db=cid.getWritableDatabase();


        ed1=(EditText) findViewById(R.id.editText);
        ed2=(EditText) findViewById(R.id.editText2);
        ed3=(EditText) findViewById(R.id.editText3);
        ed4=(EditText) findViewById(R.id.editText4);
        ed5=(EditText) findViewById(R.id.editText5);

        sharedpreferences = this.getSharedPreferences( "Phone",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        String phone  = sharedpreferences.getString("phoneKey1",null);
       // Toast.makeText(getApplicationContext(),phone, Toast.LENGTH_SHORT).show();
        String num[] = new String[5];
        num[0]=" ";
        num[1]=" ";
        num[2]=" ";
        num[3]=" ";
        num[4]=" ";
        num[0] = sharedpreferences.getString("phoneKey1",null);
        num[1] = sharedpreferences.getString("phoneKey2",null);
        num[2] = sharedpreferences.getString("phoneKey3",null);
        num[3] = sharedpreferences.getString("phoneKey4",null);
        num[4] = sharedpreferences.getString("phoneKey5",null);
      /* Cursor curser= db.rawQuery("select * from Database ",null);
       int i=curser.getCount();
        String num[] = new String[5];
          if(i!=0) {*/
         // num=cid.getData(db);
          ed1.setText(num[0]);
          ed2.setText(num[1]);
          ed3.setText(num[2]);
          ed4.setText(num[3]);
          ed5.setText(num[4]);
          /*((Number) this.getApplication()).setnum1(num[0]);
          ((Number) this.getApplication()).setnum2(num[1]);
          ((Number) this.getApplication()).setnum3(num[2]);
          ((Number) this.getApplication()).setnum4(num[3]);
          ((Number) this.getApplication()).setnum5(num[4]);*/
       // if(sharedpreferences.getString("State",null).equals("on")) {
           // Intent intent = new Intent(this, SensorDetect.class);

            //intent.putExtra("nos",num[0]);
            //startService(intent);
       // }
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                editor.putString("phoneKey1", ed1.getText().toString());
                editor.putString("phoneKey2", ed2.getText().toString());
                editor.putString("phoneKey3", ed3.getText().toString());
                editor.putString("phoneKey4", ed4.getText().toString());
                editor.putString("phoneKey5", ed5.getText().toString());
                editor.commit();
               /* Cursor curser= db.rawQuery("select * from Database ",null);
                int i=curser.getCount();
               // Log.i("","nvjfbvj");
               // Toast.makeText(getApplicationContext(), Integer.toString(i), Toast.LENGTH_SHORT).show();
                if(i==0){
                cid.insertContact(1,ed1.getText().toString());
                cid.insertContact(2,ed2.getText().toString());
                cid.insertContact(3,ed3.getText().toString());
                cid.insertContact(4,ed4.getText().toString());
                cid.insertContact(5,ed5.getText().toString());
                }
                else {
                    cid.update(db,1, ed1.getText().toString());
                    cid.update(db,2, ed2.getText().toString());
                    cid.update(db,3, ed3.getText().toString());
                    cid.update(db,4, ed4.getText().toString());
                    cid.update(db,5, ed5.getText().toString());
                }
                */
                Toast.makeText(getApplicationContext(),"saved", Toast.LENGTH_SHORT).show();
                //cid.insertContact(2,ed2.getText().toString());
               // cid.insertContact(3,ed3.getText().toString());
            }
        });
    }
}
