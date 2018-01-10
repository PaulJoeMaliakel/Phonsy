package com.example.vaishnavj.phonesy;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by Vaishnav J on 04-03-2017.
 */

public class SensorDetect extends Service implements SensorEventListener {

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;
    public double gforce;
    SharedPreferences sharedpreferences;
    //public String num=null;

    @Override
    public void onDestroy() {
        super.onDestroy();
        senSensorManager.unregisterListener(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
       // SmsManager smsManager = SmsManager.getDefault();
        //smsManager.sendTextMessage("9400977209", null, "accident occuered at ", null, null);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       // senSensorManager.registerListener((SensorEventListener) this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        //String yes=intent.getStringExtra("hello");

        Toast.makeText(getApplicationContext(), "sss", Toast.LENGTH_SHORT).show();
        //Intent s=new Intent(this,SLocation.class);
        //startService(s);
        //num=intent.getStringExtra("nos");
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;
        sharedpreferences = this.getSharedPreferences( "Phone",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        if(sharedpreferences.getString("State",null).equals("off")) {
            stopSelf();
        }
        //Toast.makeText(getApplicationContext(), "service", Toast.LENGTH_SHORT).show();
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = (int)sensorEvent.values[0];
            float y = (int)sensorEvent.values[1];
            float z = (int)sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;
                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ (diffTime*diffTime) * 10000;
                double gforce = Math.abs(x+y+z)/ 9.8;
                Intent s=new Intent(this,SLocation.class);
               // s.putExtra("nos",num);
                //Toast.makeText(getApplicationContext(), "service", Toast.LENGTH_SHORT).show();
                if(gforce>2) {
                    //SmsManager smsManager = SmsManager.getDefault();
                    //smsManager.sendTextMessage("9400977209",null, "accident occuered at", null, null);
                   // Toast.makeText(getApplicationContext(), "service", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(this,((Number) this.getApplication()).getnum1(),Toast.LENGTH_SHORT).show();
                   /* SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("Phone",Context.MODE_PRIVATE);
                    String num = sharedPref.getString("phoneKey1",null);
                    Toast.makeText(getApplicationContext(), num, Toast.LENGTH_SHORT).show();*/
                    startService(s);
                    stopSelf();
                }
                last_x = x;
                last_y = y;
                last_z = z;

            }

        }
    }
}