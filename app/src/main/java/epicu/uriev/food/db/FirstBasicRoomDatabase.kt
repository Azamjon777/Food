package epicu.uriev.food.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import epicu.uriev.food.db.dao.BasicDaoPojo
import epicu.uriev.food.model.BasicItemPojo

@Database(entities = [BasicItemPojo::class], version = 1)
abstract class FirstBasicRoomDatabase : RoomDatabase() {

    abstract fun getBasicDao(): BasicDaoPojo

    companion object {
        private var database: FirstBasicRoomDatabase? = null

        fun getInstance(context: Context): FirstBasicRoomDatabase {
            return if (database == null) {
                database = Room.databaseBuilder(context, FirstBasicRoomDatabase::class.java, "db")
                    .build()
                database as FirstBasicRoomDatabase
            } else {
                database as FirstBasicRoomDatabase
            }
        }
    }
}