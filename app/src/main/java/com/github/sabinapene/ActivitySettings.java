package com.github.sabinapene;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Spinner;
        import android.widget.Switch;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import com.github.sabinapene.Models.Settings;
        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

        import java.util.HashMap;

public class ActivitySettings extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    //firebase authentification
    private FirebaseDatabase db;
    SharedPreferences sharedPreferencesMetric;
    SharedPreferences sharedPreferencesNotificationsPeriod;

    String notPeriod ="";
    int spinnerIndexCurrentDisplay =0;
    String metric="true";
    TextView notPerTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Switch swtMetricUnits = findViewById(R.id.switchMetricUnits);
        Switch swtImperialUnits = findViewById(R.id.switchImperialUnits);
        notPerTextView = (TextView)findViewById(R.id.textView2);
        sharedPreferencesMetric = getSharedPreferences("preferences", MODE_PRIVATE);
        sharedPreferencesNotificationsPeriod = getSharedPreferences("preferences", MODE_PRIVATE);
        notPeriod = sharedPreferencesNotificationsPeriod.getString("Notifications_Period", "");
        notPerTextView.setText("Notifications Period: "+notPeriod);
        metric = sharedPreferencesNotificationsPeriod.getString("Metric", "");
        if(metric.equals("true")){
            swtMetricUnits.setChecked(true);
            swtImperialUnits.setChecked(false);
        }
        else{
            swtMetricUnits.setChecked(false);
            swtImperialUnits.setChecked(true);
        }


        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sharedPreferencesMetric = getSharedPreferences("preferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferencesMetric.edit();
                if(metric.equals("true")){
                    editor.putString("Metric", "true");
                }
                else{
                    editor.putString("Metric", "false");
                }
                editor.apply();


                sharedPreferencesNotificationsPeriod = getSharedPreferences("preferences", MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sharedPreferencesNotificationsPeriod.edit();
                editor2.putString("Notifications_Period", notPeriod);
                editor2.apply();

                addSettingsFirebase();
                Toast.makeText(getApplicationContext(), notPeriod+" "+metric, Toast.LENGTH_SHORT).show();


                startActivity(new Intent( getApplicationContext(), ActivitySettings.class));
            }
        });

        findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( getApplicationContext(), ActivitySettings.class));
            }
        });

        swtMetricUnits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swtMetricUnits.isChecked()){
                    swtImperialUnits.setChecked(false);
                    findViewById(R.id.saveButton).setVisibility(View.VISIBLE);
                    findViewById(R.id.cancelButton).setVisibility(View.VISIBLE);

                    metric = "true";
                }
            }
        });

        swtImperialUnits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swtImperialUnits.isChecked()){
                    swtMetricUnits.setChecked(false);
                    findViewById(R.id.saveButton).setVisibility(View.VISIBLE);
                    findViewById(R.id.cancelButton).setVisibility(View.VISIBLE);

                    metric = "false";
                }
            }
        });



        Spinner spinnerNotPeriod = findViewById(R.id.spinnerNotificationsPeriod);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(ActivitySettings.this, R.array.notifications_period, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HashMap<Integer, String> spinnerMap = new HashMap<Integer, String>();
        spinnerMap.put(0, "Select...");
        spinnerMap.put(1, "30 minutes");
        spinnerMap.put(2, "1 hour");spinnerMap.put(0, "30 minutes");
        spinnerMap.put(3, "2 hours");spinnerMap.put(0, "30 minutes");
        spinnerMap.put(4, "12 hours");spinnerMap.put(0, "30 minutes");
        spinnerMap.put(5, "Daily");spinnerMap.put(0, "30 minutes");

        if(notPeriod.equals("")){
            for (int j=1;j<5;j++){
                if (spinnerMap.get(j).equals(notPeriod)){
                    spinnerIndexCurrentDisplay = j;
                }
            }
        }
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

        if(adapterView.getItemAtPosition(i).toString().equals("Select...") ){
            //Toast.makeText(getApplicationContext(), "Not a viable option", Toast.LENGTH_SHORT).show();
            }
        else{
            notPeriod = adapterView.getItemAtPosition(i).toString();

            findViewById(R.id.saveButton).setVisibility(View.VISIBLE);
            findViewById(R.id.cancelButton).setVisibility(View.VISIBLE);
        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    /*spinnerNotPeriod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterVieww, View selectedItemView, int i, long id) {
            notPeriod = adapterVieww.getItemAtPosition(i).toString();

            findViewById(R.id.saveButton).setVisibility(View.VISIBLE);
            findViewById(R.id.cancelButton).setVisibility(View.VISIBLE);            }

        @Override
        public void onNothingSelected(AdapterView<?> adapterVieww) {

        }

    });*/

    private void addSettingsFirebase() {

        //initialising firebase authentification
        db = FirebaseDatabase.getInstance();


        //set up info to add to firebase
        Settings settings = new Settings( metric, notPeriod );


        //add to firebase
       DatabaseReference reference = db.getInstance().getReference("settings");
         reference.child(ActivitySignIn.getCurrentUserEmailID()).setValue(settings)
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

