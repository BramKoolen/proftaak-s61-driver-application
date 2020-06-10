package nl.fhict.denmarkroadtax.data.ride.cache.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable

@Dao
interface RoomRideRecapFromDayDao {

    @Query("SELECT * FROM ride_recap_of_day")
    fun getRideRecapFromDays(): List<RideRecapOfDayRoom>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRideRecapFromDay(rideRecapOfDayRoom: RideRecapOfDayRoom)
}