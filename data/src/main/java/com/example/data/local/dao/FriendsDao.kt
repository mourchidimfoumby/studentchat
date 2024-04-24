package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.TABLE_USER_FRIENDS
import com.example.data.local.entity.FriendsEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface FriendsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFriends(friendsEntity: FriendsEntity)

    @Update
    suspend fun updateFriends(friendsEntity: FriendsEntity)

    @Query("SELECT * FROM $TABLE_USER_FRIENDS")
    fun getAllFriends(): Flow<List<FriendsEntity>>

    @Query("SELECT * FROM $TABLE_USER_FRIENDS WHERE uid = :uid")
    suspend fun getFriends(uid: String): FriendsEntity?

    @Delete
    suspend fun deleteFriends(friendsEntity: FriendsEntity)
}