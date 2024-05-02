package federico.caffe.progetto_capolavoromf

import androidx.room.Database
import androidx.room.RoomDatabase
/*

@Database(
    entities = [Domande::class],
    version = 1
)
abstract class DomandeDatabase: RoomDatabase() {

    abstract val dao: DomandeDao
}*/

@Database(entities = [Domande::class], version = 1)
abstract class DomandeDatabase : RoomDatabase() {
    abstract fun domandeDao(): DomandeDao
}