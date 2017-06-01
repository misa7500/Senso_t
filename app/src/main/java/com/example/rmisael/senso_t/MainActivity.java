package com.example.rmisael.senso_t;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Environment;
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
    MediaPlayer sonido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        encabezado=(TextView)findViewById(R.id.encabezado);
        encabezado.setTextSize(100);
        fondo=(RelativeLayout)findViewById(R.id.fondo);
        SensorManager miSensor=(SensorManager)getSystemService(SENSOR_SERVICE);
        ran =(int) (Math.random()*3)+1;
        miSensor.registerListener(this,miSensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
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
                    // sonido = MediaPlayer.create(getBaseContext(),R.raw.izquierda);

                    if (y <= -7) {

                        encabezado.setText("Correcto");
                        fondo.setBackgroundColor(Color.GREEN);
                        new CountDownTimer(1500, 1000) {
                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {
                                ban = false;
                                ran = (int) (Math.random() * 3) + 1;
                            }
                        }.start();
                    }
                    else if (y >= +7 || z >= 7)
                    {
                        fondo.setBackgroundColor(Color.RED);
                    }

                } else if (ran == 2) {
                    lado = ("Derecha");
                    //sonido = MediaPlayer.create(getBaseContext(),R.raw.derecha);

                    if (y >= +7) {
                        encabezado.setText("Correcto");
                        fondo.setBackgroundColor(Color.GREEN);
                        new CountDownTimer(1500, 1000) {
                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {
                                ban = false;
                                ran = (int) (Math.random() * 3) + 1;
                            }
                        }.start();
                    }
                    else if ( y <= -7 || z >= 7)
                    {
                        fondo.setBackgroundColor(Color.RED);
                    }
                } else if (ran == 3) {
                    lado = ("Atras");
                    //sonido = MediaPlayer.create(getBaseContext(),R.raw.atras);

                    if (z >= 7) {

                        encabezado.setText("Correcto");
                        fondo.setBackgroundColor(Color.GREEN);
                        new CountDownTimer(1500, 1000) {
                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {
                                ban = false;
                                ran = (int) (Math.random() * 3) + 1;
                            }
                        }.start();
                    }
                    else if (y >= +7 ||y <= -7 )
                    {
                        fondo.setBackgroundColor(Color.RED);
                    }
                } else
                    ban = false;

            }


        encabezado.setText(lado);

//        fondo.setBackgroundColor(Color.rgb(x*20,y*20,z*20));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
