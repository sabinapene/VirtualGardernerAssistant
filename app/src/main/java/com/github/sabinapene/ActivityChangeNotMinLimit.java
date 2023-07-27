package com.github.sabinapene;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityChangeNotMinLimit extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_min_limit);


        Button buttonSaveNotMinLimit = findViewById(R.id.saveNotMinLimitBtn);

        TextView notMinLimitTextView = findViewById(R.id.textViewNotMinLimit);
        TextView notMinLimitTextView2 = findViewById(R.id.textViewNotMinLimit2);
        TextView notMinLimitTextView3 = findViewById(R.id.textViewNotMinLimit3);


        SeekBar seekBarHumidity = findViewById(R.id.seekBarMinHumidity);
        SeekBar seekBarTemp = findViewById(R.id.seekBarMinTemp);
        SeekBar seekBarCO2 = findViewById(R.id.seekBarMinCO2);

        String textHumidity = "";
        String textTemp = "";
        String textCO2 = "";



        seekBarHumidity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                notMinLimitTextView.setText(String.valueOf(i)+ "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarTemp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                notMinLimitTextView2.setText(String.valueOf(i)+ " C");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarCO2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                notMinLimitTextView3.setText(String.valueOf(i)+ " ppm");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        buttonSaveNotMinLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Humidty "+seekBarHumidity.getProgress()+"%; Temperature "+seekBarTemp.getProgress()+" C; CO2 "+seekBarCO2.getProgress()+" ppm", Toast.LENGTH_LONG).show();
                finish();

            }

        });

        findViewById(R.id.cancelNotMinLimitBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }

        });

    }


}
