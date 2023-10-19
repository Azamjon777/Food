package epicu.uriev.food.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import epicu.uriev.food.model.BasicItemPojo

@Dao
interface BasicDaoPojo {
    @Query("SELECT EXISTS (SELECT 1 FROM item_table WHERE title = :title)")
    fun checkItemExists(title: String): LiveData<Boolean>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(basicItemPojo: BasicItemPojo)

    @Delete
    suspend fun delete(basicItemPojo: BasicItemPojo)

    @Query("SELECT * FROM item_table")
    fun getAllBasicItems(): LiveData<List<BasicItemPojo>>
}