package com.github.sabinapene;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import com.github.sabinapene.ActivityProfile;
import com.github.sabinapene.R;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)   {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)   {

        /*switch (item.getItemId())
        {
            case R.id.itemProfile:
                Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ActivityProfile.class));
                break;
        }*/

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


}