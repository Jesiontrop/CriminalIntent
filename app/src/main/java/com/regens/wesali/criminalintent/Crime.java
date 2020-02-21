package com.regens.wesali.criminalintent;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private int mIdPosition;

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public int getIdPosition() {
        return mIdPosition;
    }

    public void setIdPosition(int idPosition) {
        mIdPosition = idPosition;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public UUID getId() {
        return mId;
    }

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }
}
