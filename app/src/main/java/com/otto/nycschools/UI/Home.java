package com.otto.nycschools.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.otto.nycschools.DataSource.SchoolViewModel;
import com.otto.nycschools.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_host);

        SchoolViewModel schoolViewModel = ViewModelProviders.
                of(this)
                .get(SchoolViewModel.class);
    }
}
