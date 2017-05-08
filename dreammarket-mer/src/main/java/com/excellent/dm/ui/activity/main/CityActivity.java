package com.excellent.dm.ui.activity.main;

import android.os.Bundle;

import com.zaaach.citypicker.CityPickerActivity;

public class CityActivity extends CityPickerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getCities();
    }

    private void getCities() {

    }
}
