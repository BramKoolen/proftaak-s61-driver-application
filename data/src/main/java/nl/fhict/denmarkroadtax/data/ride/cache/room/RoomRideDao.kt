package nl.fhict.denmarkroadtax.data.ride.cache.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface RoomRideDao {

    @Query("SELECT * FROM ride WHERE date = :date")
    fun getRidesFromDay(date: String): List<RideRoom>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRidesFromDay(rideRoom: List<RideRoom>)

    @Query("DELETE FROM ride WHERE date = :date")
    fun deleteAllRidesFromDay(date: String)
}