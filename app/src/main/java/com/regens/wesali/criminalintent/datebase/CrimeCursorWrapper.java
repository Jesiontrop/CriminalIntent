package com.regens.wesali.criminalintent.datebase;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.regens.wesali.criminalintent.Crime;
import com.regens.wesali.criminalintent.datebase.CrimeDbSchema.CrimeTable;

import java.util.Date;
import java.util.UUID;

public class CrimeCursorWrapper extends CursorWrapper {
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        String uuidString = getString(getColumnIndex(CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeTable.Cols.TITLE));
        long date = getLong(getColumnIndex(CrimeTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(CrimeTable.Cols.SOLVED));
        String suspect = getString(getColumnIndex(CrimeTable.Cols.SUSPECT));
        String numberSuspect = getString(getColumnIndex(CrimeTable.Cols.NUMBER_SUSPECT));
        int position = getInt(getColumnIndex(CrimeTable.Cols.POSITION));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved != 0);
        crime.setSuspect(suspect);
        crime.setNumberSuspect(numberSuspect);
        crime.setPosition(position);

        return crime;
    }
}
