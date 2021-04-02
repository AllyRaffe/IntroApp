package com.example.introapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class InformationPageActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSave;
    Spinner spnSeason;
    SeekBar skbTemp;
    Switch swchAllergy;
    TextView lblSeekValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_page);
        spnSeason = (Spinner) findViewById(R.id.spnSeason);
        skbTemp = (SeekBar) findViewById(R.id.skbTemp);
        swchAllergy = (Switch) findViewById(R.id.swchAllergy);
        lblSeekValue = (TextView) findViewById(R.id.lblSeekValue);
        btnSave = (Button) findViewById(R.id.btnSave);

        btnSave.setOnClickListener(this);

        skbTemp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String display = String.valueOf(progress);
                lblSeekValue.setText(display);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.mnuMain:
                startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
                return true;
            case R.id.mnuExit:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(View v) {
        String season, allergies;
        int temperature;
        season = spnSeason.getSelectedItem().toString();
        temperature = skbTemp.getProgress();
        allergies = (String) (swchAllergy.isChecked() ? swchAllergy.getTextOn() : swchAllergy.getTextOff());
        Intent intent = new Intent(InformationPageActivity.this, InformationResultsActivity.class);
        intent.putExtra("season", season);
        intent.putExtra("temperature", temperature);
        intent.putExtra("allergies", allergies);
        this.startActivity(intent);
    }
}
