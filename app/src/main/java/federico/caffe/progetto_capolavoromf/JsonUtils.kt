package federico.caffe.progetto_capolavoromf

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

fun CaricaDBdaJson(context: Context): List<Domande> {
    val inputStream = context.assets.open("Domande.json")
    val reader = InputStreamReader(inputStream)
    val type = object : TypeToken<List<Domande>>() {}.type
    Log.d("jSonUtils", "DB CARICATO")
    return Gson().fromJson(reader, type)
}