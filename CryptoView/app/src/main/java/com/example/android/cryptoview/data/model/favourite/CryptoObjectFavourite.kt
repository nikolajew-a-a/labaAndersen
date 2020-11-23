package com.example.android.cryptoview.data.model.favourite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "crypto_objects_favourite_table")
class CryptoObjectFavourite (
        @PrimaryKey @ColumnInfo(name = "favourite_id") val favouriteId: Int?
)