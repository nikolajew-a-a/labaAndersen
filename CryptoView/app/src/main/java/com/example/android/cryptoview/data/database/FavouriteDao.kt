package com.example.android.cryptoview.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.cryptoview.data.model.favourite.CryptoObjectFavourite
import io.reactivex.Single

@Dao
interface FavouriteDao {
    @get:Query("SELECT * FROM crypto_objects_favourite_table")
    val ids: Single<List<CryptoObjectFavourite?>?>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cryptoObject: CryptoObjectFavourite?)

    @Query("DELETE FROM crypto_objects_favourite_table WHERE favourite_id = :id")
    fun deleteById(id: Int)
}
