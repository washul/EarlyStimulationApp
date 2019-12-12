package wsl.com.earlystimulationapp.Data.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import wsl.com.earlystimulationapp.Data.Room.Dao.DaoActivity
import wsl.com.earlystimulationapp.Data.Room.DataBase.Companion.DATABASE_VERSION
import wsl.com.earlystimulationapp.Data.Room.Entity.EActivity

@Database(
    entities = [EActivity::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class DataBase: RoomDatabase() {

    abstract fun activityDao(): DaoActivity

    companion object {

        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "EarlyEstimulationDB"

        private var INSTANCE: DataBase? = null
        fun getInstance( context: Context): DataBase? {

            if ( INSTANCE == null ){

                synchronized(DataBase::class){

                    INSTANCE = Room.databaseBuilder( context.applicationContext, DataBase::class.java, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build()

                }

            }

            return INSTANCE

        }

    }
}