package com.example.githubapp.DB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface UserDao{
    @Query("SELECT * FROM dbitem")
    fun gelAll():Flowable<List<DBItem>>

    @Query("SELECT * FROM dbitem WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray):Flowable<List<DBItem>>

//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(vararg DBItems: DBItem)

    @Delete
    fun delete(DBItem: DBItem)

    @Query("SELECT * FROM dbitem WHERE repository_name IN (:search)")
    fun searchRepos(search:String):Flowable<List<DBItem>>
}
