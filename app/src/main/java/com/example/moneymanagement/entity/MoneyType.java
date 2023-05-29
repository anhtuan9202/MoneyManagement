package com.example.moneymanagement.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_moneyType")
public class MoneyType {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "moneyType_id")
    private int moneyTypeId;
    @ColumnInfo(name = "moneyType_name")
    private String moneyTypeName;

    public MoneyType() {
    }

    public MoneyType(int moneyTypeId, String moneyTypeName) {
        this.moneyTypeId = moneyTypeId;
        this.moneyTypeName = moneyTypeName;
    }

    public int getMoneyTypeId() {
        return moneyTypeId;
    }

    public void setMoneyTypeId(int moneyTypeId) {
        this.moneyTypeId = moneyTypeId;
    }

    public String getMoneyTypeName() {
        return moneyTypeName;
    }

    public void setMoneyTypeName(String moneyTypeName) {
        this.moneyTypeName = moneyTypeName;
    }
}
