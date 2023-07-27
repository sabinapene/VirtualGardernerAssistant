package com.github.sabinapene;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityChangeNotMaxLimit extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_max_limit);


        Button buttonSaveNotMaxLimit = findViewById(R.id.saveNotMaxLimitBtn);

        TextView notMaxLimitTextView = findViewById(R.id.textViewNotMaxLimit);
        TextView notMaxLimitTextView2 = findViewById(R.id.textViewNotMaxLimit2);
        TextView notMaxLimitTextView3 = findViewById(R.id.textViewNotMaxLimit3);


        SeekBar seekBarHumidity = findViewById(R.id.seekBarMaxHumidity);
        SeekBar seekBarTemp = findViewById(R.id.seekBarMaxTemp);
        SeekBar seekBarCO2 = findViewById(R.id.seekBarMaxCO2);

        String textHumidity = "";
        String textTemp = "";
        String textCO2 = "";



        seekBarHumidity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                notMaxLimitTextView.setText(String.valueOf(i)+ "%");
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
                notMaxLimitTextView2.setText(String.valueOf(i)+ " C");
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
                notMaxLimitTextView3.setText(String.valueOf(i)+ " ppm");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        buttonSaveNotMaxLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Humidty "+seekBarHumidity.getProgress()+"%; Temperature "+seekBarTemp.getProgress()+" C; CO2 "+seekBarCO2.getProgress()+" ppm", Toast.LENGTH_LONG).show();
                finish();

            }

        });

        findViewById(R.id.cancelNotMaxLimitBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }

        });

    }


}
