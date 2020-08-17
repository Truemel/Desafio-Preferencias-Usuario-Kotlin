package cl.desafiolatam.desafiounobase

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceController {

    companion object{
        private val sPrefName:String = "cl.desafiolatam.desafiounobase"

        private fun getShared(context:Context):SharedPreferences{
            return context.getSharedPreferences(sPrefName, Context.MODE_PRIVATE)
        }

        fun setSeenWelcome(seen:Boolean, userName:String, context: Context){
            getShared(context).edit().putBoolean("${userName}_welcome", seen).apply()
        }

        fun getSeenWelcome(userName: String, context: Context):Boolean{
            return getShared(context).getBoolean("${userName}_welcome", false)
        }


    }
}