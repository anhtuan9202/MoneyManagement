package com.example.moneymanagement.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_wallet")
public class Wallet {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "wal_id")
    private int walId;
    @ColumnInfo(name = "wal_image")
    private String walImage;
    @ColumnInfo(name = "wal_name")
    private String walName;
    @ColumnInfo(name = "wal_money")
    private String walMoney;

    public Wallet() {
    }

    public Wallet(int walId, String walImage, String walName, String walMoney) {
        this.walId = walId;
        this.walImage = walImage;
        this.walName = walName;
        this.walMoney = walMoney;
    }

    public int getWalId() {
        return walId;
    }

    public void setWalId(int walId) {
        this.walId = walId;
    }

    public String getWalImage() {
        return walImage;
    }

    public void setWalImage(String walImage) {
        this.walImage = walImage;
    }

    public String getWalName() {
        return walName;
    }

    public void setWalName(String walName) {
        this.walName = walName;
    }

    public String getWalMoney() {
        return walMoney;
    }

    public void setWalMoney(String walMoney) {
        this.walMoney = walMoney;
    }
}
