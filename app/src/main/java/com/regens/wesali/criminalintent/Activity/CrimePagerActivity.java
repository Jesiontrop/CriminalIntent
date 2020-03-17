package com.regens.wesali.criminalintent.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.regens.wesali.criminalintent.Data.Crime;
import com.regens.wesali.criminalintent.Fragment.CrimeFragment;
import com.regens.wesali.criminalintent.Data.CrimeLab;
import com.regens.wesali.criminalintent.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class CrimePagerActivity extends AppCompatActivity
        implements CrimeFragment.Callbacks{
    private static final String EXTRA_CRIME_ID =
            "com.regens.wesali.criminalintent.crime_id";
    public static final String EXTRA_ARRAY_RESULT =
            "ArrayResult";

    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CRIME_ID);

        mViewPager =(ViewPager) findViewById(R.id.activity_crime_pager_view_pager);

        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager,
                BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
        Intent intent = new Intent();
        ArrayList<Integer> arrayListResult = CrimeFragment.getArrayResult();
        intent.putIntegerArrayListExtra("ArrayResult", arrayListResult);
        setResult(Activity.RESULT_OK, intent);
    }

    @Override
    public void onCrimeUpdated(Crime crime) {
    }

    @Override
    public void onCrimeDelete(Crime crime, Fragment fragment) {
        Intent intent = new Intent();
        ArrayList<Integer> arrayListResult = CrimeFragment.getArrayResult();
        intent.putIntegerArrayListExtra("ArrayResult", arrayListResult);
        setResult(Activity.RESULT_OK, intent);
        CrimeLab.get(this)
                .deleteCrime(crime);
        finish();
    }
}
