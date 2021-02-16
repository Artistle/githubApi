package com.example.githubapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface ItemDAO{
    @Query("SELECT * FROM dbitem")
    fun gelAll():Flowable<List<DBItem>>

    @Query("SELECT * FROM dbitem WHERE repository_name LIKE :reposName")
    fun loadAllByRepoName(reposName: String):Flowable<List<DBItem>>

    @Insert
    fun insertAll(vararg DBItems: DBItem)

    @Delete
    fun delete(DBItem: DBItem)

    @Query("DELETE FROM dbitem")
    fun deleteAll()

    @Query("SELECT * FROM dbitem WHERE repository_name IN (:search)")
    fun searchRepos(search:String):Flowable<List<DBItem>>
}
