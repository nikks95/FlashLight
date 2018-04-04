package com.example.nikhil.flashlight;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView btn_on;

    private boolean flashLightStatus =false;

    //private static final int CAMERA_REQUEST = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_on = (ImageView) findViewById(R.id.on_button);

        final boolean hasCameraFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        btn_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasCameraFlash)
                {
                    onFlash();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Your phone do not have the flashlight",Toast.LENGTH_LONG);
                }
            }
        });

    }

    private void onFlash(){
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, !flashLightStatus);
             flashLightStatus = !flashLightStatus;
             if(flashLightStatus)
             {
                 btn_on.setImageResource(R.drawable.bulb_on);
             }
             else {
                 btn_on.setImageResource(R.drawable.bulb_off);
             }
        } catch (CameraAccessException e) {
        }
    }
    private void offFlash(){

    }
}
