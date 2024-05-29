package federico.caffe.progetto_capolavoromf

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DomandeDao {
    @Query("SELECT * FROM Domande")
    fun getAll(): List<Domande>

    @Insert
    suspend fun insertAll(questions: List<Domande>)

    @Query("SELECT * FROM Domande ORDER BY RANDOM() LIMIT 1")
    fun getRandomQuestion(): Domande
}
