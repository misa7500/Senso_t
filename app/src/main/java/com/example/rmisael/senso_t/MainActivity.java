package com.example.rmisael.senso_t;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView encabezado;
    RelativeLayout fondo;
    int x,y,z,ran;
    boolean ban = false;
    String lado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        encabezado=(TextView)findViewById(R.id.encabezado);
        encabezado.setTextSize(24);
        fondo=(RelativeLayout)findViewById(R.id.fondo);
        SensorManager miSensor=(SensorManager)getSystemService(SENSOR_SERVICE);
        ran =(int) (Math.random()*3)+1;
        miSensor.registerListener(this,miSensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x = (int) event.values[0];
        y = (int) event.values[1];
        z = (int) event.values[2];

        if (ran >= 1 || ran <= 3) {
            ban = true;
            fondo.setBackgroundColor(Color.WHITE);
        } else
            ran = (int) (Math.random() * 3) + 1;
        //if (x >= 7){
            if (ban) {
                if (ran == 1) {
                    lado = ("Izquierda");
                    if (y <= -7) {
                        fondo.setBackgroundColor(Color.GREEN);
                        encabezado.setText("Correcto");
                        ban = false;
                        ran = (int) (Math.random() * 3) + 1;

                    }

                } else if (ran == 2) {
                    lado = ("Derecha");
                    if (y >= +7) {
                        fondo.setBackgroundColor(Color.GREEN);
                        encabezado.setText("Correcto");
                        ban = false;
                        ran = (int) (Math.random() * 3) + 1;
                    }
                } else if (ran == 3) {
                    lado = ("Atras");
                    if (z >= 7) {
                        fondo.setBackgroundColor(Color.GREEN);
                        encabezado.setText("Correcto");
                        ban = false;
                        ran = (int) (Math.random() * 3) + 1;
                    }
                } else
                    ban = false;

            }
        //}

       /* else if (x <= -7)
            if (ban){
                if (ran == 1)
                {
                    lado = ("Derecha");
                    if (y<=-7){
                        fondo.setBackgroundColor(Color.GREEN);
                        encabezado.setText("Correcto");
                        ban = false;
                        ran =(int) (Math.random()*3)+1;

                    }

                }
                else if (ran == 2)
                {
                    lado = ("Izquierda");
                    if (y>=+7){
                        fondo.setBackgroundColor(Color.GREEN);
                        encabezado.setText("Correcto");
                        ban = false;
                        ran =(int) (Math.random()*3)+1;
                    }
                }
                else if (ran == 3)
                {
                    lado = ("Atras");
                    if (z>=7){
                        fondo.setBackgroundColor(Color.GREEN);
                        encabezado.setText("Correcto");
                        ban = false;
                        ran =(int) (Math.random()*3)+1;
                    }
                }
                else
                    ban = false;

            }
        */

        encabezado.setText("Valor: "+ x + ","+y+","+z+","+lado);

//        fondo.setBackgroundColor(Color.rgb(x*20,y*20,z*20));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
