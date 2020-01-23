package com.example.mySpinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner mCountriesSpinner;
    private Spinner mCitiesSpinner;
    private Spinner mHouseNumberSpinner;
    private Button mShowAddressBtn;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mCountriesSpinner = findViewById(R.id.countriesSpinner);
        mCitiesSpinner = findViewById(R.id.citiesSpinner);
        mHouseNumberSpinner = findViewById(R.id.houseNumberSpinner);
        mShowAddressBtn = findViewById(R.id.showAddressBtn);

        initSpinnerCountries();
        initHouseNumbersSpinner();

        mShowAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast();
            }
        });
    }

    private void initHouseNumbersSpinner() {
        Integer[] houseNumbers = new Integer[50];

        for (int i = 0; i < houseNumbers.length; i++) {
            houseNumbers[i] = i + 1;
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, houseNumbers);

        mHouseNumberSpinner.setAdapter(adapter);
    }

    private void initSpinnerCountries() {
        ArrayAdapter<CharSequence> adapterCountries = ArrayAdapter.createFromResource(this,
                R.array.countries, android.R.layout.simple_spinner_item);
        adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCountriesSpinner.setAdapter(adapterCountries);

        mCountriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] countries = getResources().getStringArray(R.array.countries);
                initSpinnerCities(countries[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initSpinnerCities(String country) {
        ArrayAdapter<CharSequence> adapter = null;

        switch (country) {
            case "Россия":
                adapter = ArrayAdapter.createFromResource(this, R.array.r_cities,
                        android.R.layout.simple_spinner_item);
                break;
            case "Украина":
                adapter = ArrayAdapter.createFromResource(this, R.array.u_cities,
                        android.R.layout.simple_spinner_item);
                break;
            case "Белоруссия":
                adapter = ArrayAdapter.createFromResource(this, R.array.b_cities,
                        android.R.layout.simple_spinner_item);
                break;
        }

        if (adapter != null) {
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mCitiesSpinner.setAdapter(adapter);
        }
    }

    public void showToast() {
        Toast.makeText(MainActivity.this
                , mCountriesSpinner.getSelectedItem().toString()
                        + " "
                        + mCitiesSpinner.getSelectedItem().toString()
                        + " "
                        + mHouseNumberSpinner.getSelectedItem().toString()
                , Toast.LENGTH_LONG)
                .show();
    }
}
