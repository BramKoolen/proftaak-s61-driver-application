package nl.fhict.denmarkroadtax.data.generic.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import nl.fhict.denmarkroadtax.data.ride.cache.room.RideRecapOfDayRoom
import nl.fhict.denmarkroadtax.data.ride.cache.room.RideRoom
import nl.fhict.denmarkroadtax.data.ride.cache.room.RoomRideDao
import nl.fhict.denmarkroadtax.data.ride.cache.room.RoomRideRecapFromDayDao

@Database(
    entities = [
        RideRoom::class,
        RideRecapOfDayRoom::class],
    version = 1
)
abstract class RoomDRTDatabase : RoomDatabase() {

    abstract fun roomRideDao(): RoomRideDao

    abstract fun roomRideRecapFromDayDao(): RoomRideRecapFromDayDao

    companion object {

        private const val DATABASE_NAME = "room_drt_database"

        fun getInstance(context: Context): RoomDRTDatabase {
            return Room.databaseBuilder(context, RoomDRTDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
    }
}