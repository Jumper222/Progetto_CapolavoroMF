package federico.caffe.progetto_capolavoromf

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Domande(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val domanda: String,
    val risposta1: String,
    val risposta2: String,
    val risposta3: String,
    val risposta4: String,
    val trueAns: Int,
)
