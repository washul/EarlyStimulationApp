package wsl.com.earlystimulationapp.Data.Room.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import wsl.com.earlystimulationapp.Utils.TABLE_NAME_ACTIVITY

@Entity(tableName = TABLE_NAME_ACTIVITY)
data class EActivity(

    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String?,
    val age: Int,
    val age_group: String?,
    val activity_type: String?,
    val is_holiday: Boolean?,
    val domain_id: Int,
    val area_id: Int,
    val purpose: String?,
    val thumbnail: String?,
    val active_milestones: Int,
    val completed_milestones: Int,
    val locked: Boolean?,
    val dap_lifes_checked: Boolean

)