package com.example.moneymanagement.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "tb_expense")
public class Expense {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exp_id")
    private int expId;
    @ColumnInfo(name = "exp_note")
    private String expNote;
    @ColumnInfo(name = "exp_money")
    private String expMoney;
    @ColumnInfo(name = "exp_date")
    private Date expDate;
    @ColumnInfo(name = "exp_type")
    private int expType;
    @ColumnInfo(name = "exp_category")
    private int expCategory;
    @ColumnInfo(name = "exp_wallet")
    private int expWallet;

    public Expense() {

    }

    public Expense(String expNote, String expMoney, Date expDate, int expType, int expCategory, int expWallet) {
        this.expNote = expNote;
        this.expMoney = expMoney;
        this.expDate = expDate;
        this.expType = expType;
        this.expCategory = expCategory;
        this.expWallet = expWallet;
    }

    public Expense(int expId, String expNote, String expMoney, Date expDate, int expType, int expCategory, int expWallet) {
        this.expId = expId;
        this.expNote = expNote;
        this.expMoney = expMoney;
        this.expDate = expDate;
        this.expType = expType;
        this.expCategory = expCategory;
        this.expWallet = expWallet;
    }

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public String getExpNote() {
        return expNote;
    }

    public void setExpNote(String expNote) {
        this.expNote = expNote;
    }

    public String getExpMoney() {
        return expMoney;
    }

    public void setExpMoney(String expMoney) {
        this.expMoney = expMoney;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public int getExpType() {
        return expType;
    }

    public void setExpType(int expType) {
        this.expType = expType;
    }

    public int getExpCategory() {
        return expCategory;
    }

    public void setExpCategory(int expCategory) {
        this.expCategory = expCategory;
    }

    public int getExpWallet() {
        return expWallet;
    }

    public void setExpWallet(int expWallet) {
        this.expWallet = expWallet;
    }
}
