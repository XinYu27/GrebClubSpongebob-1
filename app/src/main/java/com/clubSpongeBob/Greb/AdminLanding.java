package com.clubSpongeBob.Greb;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.clubSpongeBob.Greb.databinding.ActivityAdminLandingBinding;

import java.util.ArrayList;

public class AdminLanding extends AppCompatActivity{

    private ActivityAdminLandingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new CustomersFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.driver:
                    replaceFragment(new DriversFragment());
                    break;
                case R.id.customer:
                    replaceFragment(new CustomersFragment());
                    break;
            }
            return true;
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragment == new CustomersFragment()){
            fragmentTransaction.replace(R.id.customerFragmentContainer,fragment);
        }
        else {
            fragmentTransaction.replace(R.id.driverFragmentContainer,fragment);
        }
        fragmentTransaction.commit();

    }

}