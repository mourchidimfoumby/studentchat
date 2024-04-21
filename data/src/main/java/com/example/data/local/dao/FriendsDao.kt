package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.data.TABLE_USER
import com.example.data.TABLE_USER_FRIENDS
import com.example.data.local.entity.FriendsEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface FriendsDao {
    @Upsert
    fun insertFriends(friendsEntity: FriendsEntity)
    @Query("SELECT * FROM $TABLE_USER_FRIENDS")
    fun getAllFriends(): Flow<FriendsEntity>
    @Query("SELECT * FROM $TABLE_USER_FRIENDS WHERE uid = :uid")
    fun getFriends(uid: String): FriendsEntity?
    @Delete
    fun deleteFriends(friendsEntity: FriendsEntity)
}