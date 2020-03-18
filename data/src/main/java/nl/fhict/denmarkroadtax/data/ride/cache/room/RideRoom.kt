package nl.fhict.denmarkroadtax.data.ride.cache.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ride")
data class RideRoom(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "startTitle")
    val startTitle: String,
    @ColumnInfo(name = "startAddress")
    val startAddress: String,
    @ColumnInfo(name = "startTime")
    val startTime: String,
    @ColumnInfo(name = "endTitle")
    val endTitle: String,
    @ColumnInfo(name = "endAddress")
    val endAddress: String,
    @ColumnInfo(name = "endTime")
    val endTime: String,
    @ColumnInfo(name = "drivenKilometers")
    val drivenKilometers: String,
    @ColumnInfo(name = "drivenTime")
    val drivenTime: String,
    @ColumnInfo(name = "rideAddressType")
    val rideAddressType: String
)