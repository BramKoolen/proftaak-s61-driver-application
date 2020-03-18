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
    val costs: String,
    @ColumnInfo(name = "average")
    val average: String,
    @ColumnInfo(name = "drivenKilometers")
    val drivenKilometers: String,
    @ColumnInfo(name = "drivenRides")
    val drivenRides: String,
    @ColumnInfo(name = "route")
    val route: String,
    @ColumnInfo(name = "isAllDataFinal")
    val isAllDataFinal: Boolean
)
