package federico.caffe.progetto_capolavoromf

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface DomandeDao {
    @Query("SELECT * FROM domande")
    fun getAll(): List<Domande>

    @Insert
    fun insertAll(vararg users: Domande)

    @Delete
    fun delete(user: Domande)
}
