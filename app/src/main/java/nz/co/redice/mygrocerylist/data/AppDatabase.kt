package nz.co.redice.mygrocerylist.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ItemDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {


    abstract fun itemListDao(): ItemListDAO

    companion object {
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "item_db"



        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }



}