package com.regens.wesali.criminalintent.Data;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private int mPosition;

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private String mSuspect;
    private int mNumberSuspect;

    public int getNumberSuspect() {
        return mNumberSuspect;
    }

    public void setNumberSuspect(int numberSuspect) {
        mNumberSuspect = numberSuspect;
    }

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int idPosition) {
        mPosition = idPosition;
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

    public String getPhotoFilename() {
        return  "IMG_" + getId().toString() + ".jpg";
    }

    public UUID getId() {
        return mId;
    }

    public Crime() {
        this(UUID.randomUUID());
    }

    public Crime(UUID id) {
        mId = id;
        mDate = new Date();
    }
}
