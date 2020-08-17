package cl.desafiolatam.desafiounobase

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceController {

    companion object{
        private val sPrefName:String = "cl.desafiolatam.desafiounobase"

        fun getShared(context:Context):SharedPreferences{
            return context.getSharedPreferences(sPrefName, Context.MODE_PRIVATE)
        }

        fun
    }
}