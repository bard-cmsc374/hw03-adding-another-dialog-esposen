package com.example.eliasposen.criminalintent;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by eliasposen on 9/19/16.
 */
public class CrimeListActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
