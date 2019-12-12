package wsl.com.earlystimulationapp.Data.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import wsl.com.earlystimulationapp.Data.Room.Entity.EActivity

@Dao
interface DaoActivity {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(customers: List<EActivity> )


    @Update
    fun update( customer: EActivity)

    @Delete
    fun delete( customer: EActivity)



    @Query("SELECT * FROM activity")
    fun getList(): List<EActivity>


}