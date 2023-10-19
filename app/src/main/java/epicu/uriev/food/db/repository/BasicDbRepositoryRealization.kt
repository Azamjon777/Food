package epicu.uriev.food.db.repository

import androidx.lifecycle.LiveData
import epicu.uriev.food.db.dao.BasicDaoPojo
import epicu.uriev.food.model.BasicItemPojo

class BasicDbRepositoryRealization(private val basicDaoPojo: BasicDaoPojo) : BasicDbRepository {
    override val allBasicCourses: LiveData<List<BasicItemPojo>>
        get() = basicDaoPojo.getAllBasicItems()

    override suspend fun insertBasicItem(basicItemPojo: BasicItemPojo, onSuccess: () -> Unit) {
        basicDaoPojo.insert(basicItemPojo)
        onSuccess()
    }

    override suspend fun deleteBasicItem(basicItemPojo: BasicItemPojo, onSuccess: () -> Unit) {
        basicDaoPojo.delete(basicItemPojo)
        onSuccess()
    }

    override fun checkItemExists(title: String): LiveData<Boolean> {
        return basicDaoPojo.checkItemExists(title)
    }
}