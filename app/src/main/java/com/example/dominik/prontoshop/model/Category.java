package com.example.dominik.prontoshop.model;

import android.database.Cursor;

import com.example.dominik.prontoshop.util.Constants;

public class Category {
    private long id;
    private String categoryName;

    public Category() {
    }

    public Category(long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public static Category fromCursor(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(Constants.COLUMN_ID));
        String name = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_NAME));
        Category category = new Category(id, name);
        return category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
