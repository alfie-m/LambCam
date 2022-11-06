package com.example.lambcamv1.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize // To pass custom Tup data structure between components within app
@Entity(tableName = "tup_table")
data class Tup (
    // Set id ad database primary key
    @PrimaryKey(autoGenerate = true)
    // Variables to be recorded in Tup database
    val id: Int,
    val tagNo: String,
    val breed: String,
    val age: Int
): Parcelable