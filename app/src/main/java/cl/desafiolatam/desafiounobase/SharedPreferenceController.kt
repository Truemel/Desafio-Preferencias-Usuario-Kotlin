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

        fun getNickName(userName: String, context: Context):String{
            return getShared(context).getString("${userName}_nick", "Nickname")!!
        }

        fun getAge(userName: String, context: Context):Int{
            return getShared(context).getInt("${userName}_age", 0)
        }

        fun setNickName(nick:String, userName: String, context: Context){
            getShared(context).edit().putString("${userName}_nick", nick).apply()
        }

        fun setAge(age:Int, userName: String, context: Context){
            getShared(context).edit().putInt("${userName}_age", age).apply()
        }

        fun setLanguage(lang:String, userName: String, context: Context){
            if(lang.contains("Other"))
                getShared(context).edit().putString("${userName}_Other", lang).apply()
            else
                getShared(context).edit().putString("${userName}_$lang", lang).apply()
        }

        fun getLanguage(lang:String, userName: String, context: Context):String{
            return getShared(context).getString("${userName}_$lang", "")!!
        }
    }
}