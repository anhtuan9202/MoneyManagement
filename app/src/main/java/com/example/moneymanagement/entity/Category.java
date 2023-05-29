package com.example.moneymanagement.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_category")
public class Category {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cate_id")
    private int cateId;
    @ColumnInfo(name = "cate_name")
    private String cateName;
    @ColumnInfo(name = "cate_image")
    private String cateImage;
    @ColumnInfo(name = "cate_type")
    private int cateType;

    public Category() {
    }

    public Category(int cateId, String cateName, String cateImage, int cateType) {
        this.cateId = cateId;
        this.cateName = cateName;
        this.cateImage = cateImage;
        this.cateType = cateType;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCateImage() {
        return cateImage;
    }

    public void setCateImage(String cateImage) {
        this.cateImage = cateImage;
    }

    public int getCateType() {
        return cateType;
    }

    public void setCateType(int cateType) {
        this.cateType = cateType;
    }
}
