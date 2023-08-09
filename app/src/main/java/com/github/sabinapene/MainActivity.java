package com.github.sabinapene;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.github.sabinapene.Models.Garden;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String userID="";
    String metricStr;
    boolean metric = false;
    private FirebaseDatabase db;
    DatabaseReference reference;
    private ArrayList<Garden> gardens = new ArrayList<Garden>();
    private ArrayList<Garden> currentGardens = new ArrayList<Garden>();
    private ArrayList<Garden> orderedGardens = new ArrayList<Garden>();
    private Garden currentGarden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("garden");
        SharedPreferences sharedPreferencesUserID = getSharedPreferences("UserID", MODE_PRIVATE);
        userID = sharedPreferencesUserID.getString("UserID", "");

        SharedPreferences sharedPreferencesMetric = getSharedPreferences("preferences", MODE_PRIVATE);
        metricStr = sharedPreferencesMetric.getString("Metric", "");

        Switch swtWindow = findViewById(R.id.switchWindow);
        TextView tvCO2 = findViewById(R.id.textViewCO22);
        TextView tvHum = findViewById(R.id.textViewHum2);
        TextView tvTemp = findViewById(R.id.textViewTemp2);
        setContentView(R.layout.activity_main);

        addGardensToFirebase();
        retrieveData();

                if(metricStr.equals("true")){
                    metric = true;
                }

                if(metric){
                    tvTemp.setText(currentGarden.getTemp()+" C");
                }else {
                    tvTemp.setText(calculateTempImp(currentGarden.getTemp())+" F");
                }
                tvCO2.setText(currentGarden.getCO2()+" ppm");
                tvHum.setText(currentGarden.getHumidity()+"%");
                swtWindow.setChecked(currentGarden.isWindowOpen());

        findViewById(R.id.buttonBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        findViewById(R.id.buttonNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        swtWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swtWindow.isChecked()){

                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)   {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)   {

        if(item.getItemId()==R.id.itemProfile)
        {
            Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), ActivityProfile.class));
        } else{
            Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), ActivitySettings.class));
        }
        return super.onOptionsItemSelected(item);

    }
    private void addGardensToFirebase() {
        //set up info to add to firebase
        Garden garden1 = new Garden("123", "28/07/2023", "", true, 430, 20, 60);
        Garden garden2 = new Garden("123", "29/07/2023", "", true, 370, 21, 55);
        Garden garden3 = new Garden("123", "30/07/2023", "", false, 530, 23, 63);
        Garden garden4 = new Garden("123", "31/07/2023", "", true, 550, 22, 77);
        Garden garden5 = new Garden("123", "01/08/2023", "", false, 475, 19, 42);
        Garden garden6 = new Garden("123", "02/08/2023", "", true, 433, 20, 74);
        Garden garden7 = new Garden("123", "03/08/2023", "", true, 533, 19, 30);
        Garden garden8 = new Garden("123", "04/08/2023", "", false, 600, 21, 40);
        Garden garden9 = new Garden("123", "05/08/2023", "", true, 369, 19, 65);
        Garden garden10 = new Garden("123", "06/08/2023", "", false, 399, 22, 60);
        Garden garden11 = new Garden("123", "07/08/2023", "", false, 577, 18, 53);
        Garden garden12 = new Garden("123", "08/08/2023", "", true, 450, 20, 47);

        //add to firebase
        DatabaseReference reference = db.getInstance().getReference("garden");
        reference.child("garden1").setValue(garden1);
        reference.child("garden2").setValue(garden2);
        reference.child("garden3").setValue(garden3);
        reference.child("garden4").setValue(garden4);
        reference.child("garden5").setValue(garden5);
        reference.child("garden6").setValue(garden6);
        reference.child("garden7").setValue(garden7);
        reference.child("garden8").setValue(garden8);
        reference.child("garden9").setValue(garden9);
        reference.child("garden10").setValue(garden10);
        reference.child("garden11").setValue(garden11);
        reference.child("garden12").setValue(garden12);

    }

    public int calculateTempImp(int c){
       //C to F
        return 9/5*c+32;
    }

    private void retrieveData(){
        ValueEventListener valueEventListener = new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    gardens.clear();
                    for (DataSnapshot ds: dataSnapshot.getChildren()){

                        Garden garden = ds.getValue(Garden.class);
                        garden.setGardenID(ds.getKey());
                        gardens.add(garden);

                    }
                    searchByUserID();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Connection Error", Toast.LENGTH_SHORT).show();

            }
        };

        reference.addValueEventListener(valueEventListener);


    }

    private void searchByUserID(){

        SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");

        currentGardens.clear();
        if(gardens.size()!=0 && gardens.get(0)!=null) {
            for (int i = 0; i < gardens.size(); i++) {
                Garden garden = gardens.get(i);
                if (garden.getUserID().equals(userID)) {
                    currentGardens.add(garden);
                    if(garden.getDate().equals(sdformat.toString())){
                        currentGarden = garden;
                    }
                }
            }
        }
        else{
            Toast.makeText(MainActivity.this, "No garden entries", Toast.LENGTH_SHORT).show();
        }
    }

    private void orderGardens(){

        SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");

        orderedGardens.clear();
        if(currentGardens.size()!=0 && currentGardens.get(0)!=null) {
            for (int i = 0; i < currentGardens.size(); i++) {
                Garden garden = currentGardens.get(i);
                while(currentGardens.size()!=orderedGardens.size()) {
                    if(garden.getDate().equals(sdformat.toString())) {
                        orderedGardens.add(garden);
                    }
                }
            }
        }
    }

}