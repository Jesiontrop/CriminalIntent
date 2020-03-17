package com.regens.wesali.criminalintent.Activity;

import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.regens.wesali.criminalintent.Data.Crime;
import com.regens.wesali.criminalintent.Data.CrimeLab;
import com.regens.wesali.criminalintent.Fragment.CrimeFragment;
import com.regens.wesali.criminalintent.Fragment.CrimeListFragment;
import com.regens.wesali.criminalintent.R;

import static com.regens.wesali.criminalintent.R.id.fragment_container;

public class CrimeListActivity extends SingleFragmentActivity
    implements CrimeListFragment.Callbacks, CrimeFragment.Callbacks {

    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onCrimeSelected(Crime crime) {
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = CrimePagerActivity.newIntent(this, crime.getId());
            startActivityForResult(intent, CrimeListFragment.REQUEST_CRIME);
        } else {
            Fragment newDetail = CrimeFragment.newInstance(crime.getId());

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }
    }

    @Override
    public void onCrimeUpdated(Crime crime) {
        if (findViewById(R.id.detail_fragment_container) != null) {
            CrimeListFragment listFragment = (CrimeListFragment)
                    getSupportFragmentManager()
                            .findFragmentById(fragment_container);
            listFragment.updateUI();
        }
    }

    @Override
    public void onCrimeDelete(Crime crime, Fragment fragment) {
        CrimeLab.get(this)
                .deleteCrime(crime);
        getSupportFragmentManager()
                .beginTransaction()
                .remove(fragment)
                .commit();
    }
}
