package com.example.lambcamv1.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize // To pass custom Ewe data structure between components within app
@Entity(tableName = "ewe_table")
data class Ewe (
    // Set id ad database primary key
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    // Variables to be recorded in Ewe database
    val tagNo: String,
    val breed: String,
    val age: Int
    ): Parcelable