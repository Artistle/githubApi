package com.example.githubapp.DB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class DBItem(
        @PrimaryKey(autoGenerate = true) var uid:Int = 0,
        @ColumnInfo(name="repository_name") val reposName:String,
        @ColumnInfo(name="author") val fullname:String?,
        @ColumnInfo(name="description") val desc:String,
        @ColumnInfo(name="fork") val fork:Int,
        @ColumnInfo(name="stars") val stars:Int
)
