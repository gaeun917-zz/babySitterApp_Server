package com.androidnh.nh;

import java.sql.Timestamp;

public class Manbo {

    private Timestamp regDate;
    private int count;

   public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }
}
