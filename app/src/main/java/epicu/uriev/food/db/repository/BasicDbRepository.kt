package epicu.uriev.food.db.repository

import androidx.lifecycle.LiveData
import epicu.uriev.food.model.BasicItemPojo

interface BasicDbRepository {
    val allBasicCourses: LiveData<List<BasicItemPojo>>

    suspend fun insertBasicItem(basicItemPojo: BasicItemPojo, onSuccess: () -> Unit)
    suspend fun deleteBasicItem(basicItemPojo: BasicItemPojo, onSuccess: () -> Unit)
    fun checkItemExists(title: String): LiveData<Boolean>
}
