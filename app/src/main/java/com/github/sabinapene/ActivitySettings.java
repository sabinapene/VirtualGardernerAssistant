package com.github.sabinapene;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Spinner;
        import android.widget.Switch;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;

        import com.google.firebase.auth.FirebaseAuth;

public class ActivitySettings extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    //firebase authentification
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Switch swtMetricUnits = findViewById(R.id.switchMetricUnits);
        Switch swtImperialUnits = findViewById(R.id.switchImperialUnits);

        swtMetricUnits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swtMetricUnits.isChecked()){
                    swtImperialUnits.setChecked(false);
                }
            }
        });

        swtImperialUnits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swtImperialUnits.isChecked()){
                    swtMetricUnits.setChecked(false);
                }
            }
        });

        Spinner spinnerNotPeriod = findViewById(R.id.spinnerNotificationsPeriod);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(ActivitySettings.this, R.array.notifications_period, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNotPeriod.setAdapter(arrayAdapter);
        spinnerNotPeriod.setOnItemSelectedListener(ActivitySettings.this);


        findViewById(R.id.notMinLimitTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( ActivitySettings.this, ActivityChangeNotMinLimit.class));
            }
        });

        findViewById(R.id.notMaxLimitTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( ActivitySettings.this, ActivityChangeNotMaxLimit.class));
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

