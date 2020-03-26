package nl.fhict.denmarkroadtax.data.ride.cache.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ride_recap_of_day")
data class RideRecapOfDayRoom(
    @PrimaryKey
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "costs")
    val costs: Double,
    @ColumnInfo(name = "average")
    val average: Double,
    @ColumnInfo(name = "drivenMeters")
    val drivenMeters: Int,
    @ColumnInfo(name = "drivenRides")
    val drivenRides: Int,
    @ColumnInfo(name = "isAllDataFinal")
    val isAllDataFinal: Boolean
)
