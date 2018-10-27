package com.waterphobiadr.data.local.database.table;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * A basic class representing an entity that is a row in a one-column database table.
 *
 * @ Entity - You must annotate the class as an entity and supply a table name if not class name.
 * @ PrimaryKey - You must identify the primary key.
 * @ ColumnInfo - You must supply the column name if it is different from the variable name.
 *
 * See the documentation for the full rich set of annotations.
 * https://developer.android.com/topic/libraries/architecture/room.html
 */

@Entity(tableName = "brand_table")
public class Brand {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "brand")
    private String brand;

    public Brand(@NonNull String brand) {
        this.brand = brand;
    }

    @NonNull
    public String getBrand() {
        return this.brand;
    }
}