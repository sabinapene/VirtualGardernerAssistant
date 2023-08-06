package com.github.sabinapene;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Spinner;
        import android.widget.Switch;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import com.github.sabinapene.Models.Settings;
        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

public class ActivitySettings extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    //firebase authentification
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase db;
    SharedPreferences sharedPreferencesMetric;
    SharedPreferences sharedPreferencesNotificationsPeriod;

    String notPeriod;
    String metric;

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

                    sharedPreferencesMetric = getSharedPreferences("preferences", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferencesMetric.edit();
                    editor.putString("Metric", "true");
                    editor.apply();
                    metric = sharedPreferencesMetric.getString("Metric", "");

                    addSettingsFirebase();
                }
            }
        });

        swtImperialUnits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swtImperialUnits.isChecked()){
                    swtMetricUnits.setChecked(false);

                    sharedPreferencesMetric = getSharedPreferences("preferences", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferencesMetric.edit();
                    editor.putString("Metric", "false");
                    editor.apply();
                    metric = sharedPreferencesMetric.getString("Metric", "");

                    addSettingsFirebase();
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

        sharedPreferencesNotificationsPeriod = getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesNotificationsPeriod.edit();
        editor.putString("Notifications_Period", text);
        editor.apply();

        notPeriod = sharedPreferencesNotificationsPeriod.getString("Notifications_Period", "");

        addSettingsFirebase();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    private void addSettingsFirebase() {

        //initialising firebase authentification
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();


        //set up info to add to firebase
        Settings settings = new Settings( metric, notPeriod );


        //add to firebase
        DatabaseReference reference = db.getReference("Settings");
        reference.push().setValue(settings)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //game added successfully
                        Toast.makeText(getApplicationContext(), "settings save in db", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //game added failure
                        Toast.makeText(getApplicationContext(), "DB Error", Toast.LENGTH_SHORT).show();

                    }
                });

    }
}

