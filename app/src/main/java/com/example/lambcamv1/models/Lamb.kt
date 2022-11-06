package com.example.lambcamv1.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize // To pass custom Lamb data structure between components within app
@Entity(tableName = "lamb_table")
data class Lamb (
    // Set id ad database primary key
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    // Variables to be recorded in Lamb database
    val eweTagNo: String,
    val tupTagNo: String,
    val marking: String,
    val sex: String,
    val DOB: String,
    val siblings: String,
    val comments: String
): Parcelable

