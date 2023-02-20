package com.example.mfsensor


import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;

    Sensor s;
    TextView textView;
    TextView textViewAxis;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.info_MF);
        textViewAxis=findViewById(R.id.info_Axis);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        s= sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if(s !=null){
            Toast.makeText(this, "Sensor is found..", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Sensor is not found..", Toast.LENGTH_SHORT).show();
        }




    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event!!.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
            val x = abs(event.values[0])
            val y = abs(event.values[1])
            val z = abs(event.values[2])
            infoMF.text = "X : $x\nY : $y\nZ : $z"
            if (x > 1000 || y > 1000 || z > 1000) {
                binding.view.setBackgroundColor(resources.getColor(R.color.purple_700))
            }
            else if (x > 100 || y > 100 || z > 100) {
                binding.view.setBackgroundColor(resources.getColor(R.color.teal_700))
            }else{
                binding.view.setBackgroundColor(resources.getColor(R.color.black))
            }


            if (x > y && x > z) {
                infoAxis.text = "X"
            } else if (y > x && y > z) {
                infoAxis.text = "Y"
            } else {
                infoAxis.text = "Z"
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}