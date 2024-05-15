package federico.caffe.progetto_capolavoromf

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.RoomDatabase.Callback
import androidx.room.Room
import android.content.Context
import androidx.annotation.NonNull
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import federico.caffe.progetto_capolavoromf.CaricaDBdaJson

@Database(entities = [Domande::class], version = 1)
abstract class DomandeDatabase : RoomDatabase() {
    abstract fun domandeDao(): DomandeDao

    companion object {
        @Volatile
        private var INSTANCE: DomandeDatabase? = null

        fun getDatabase(context: Context): DomandeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DomandeDatabase::class.java,
                    "Domande_Database"
                )
                    .addCallback(DomandeDatabaseCallback(context))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }




    private class DomandeDatabaseCallback(
        private val context: Context
    ) : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(database.domandeDao(), context)
                }
            }
        }
        private suspend fun populateDatabase(dao: DomandeDao, context: Context) {
            val questions = CaricaDBdaJson(context)
            dao.insertAll(questions)
        }
    }



}
