package epicu.uriev.food.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "item_table")
data class BasicItemPojo(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val img: Int,
    val title: String,
) : Serializable
