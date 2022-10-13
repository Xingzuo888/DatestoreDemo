package com.example.datestoredemo

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

val Context.dataStore:DataStore<Preferences> by  preferencesDataStore(name="pre")

object DataStoreU {

    fun getDs(context: Context,key: String,default:Any):Flow<Any>{
        return context.dataStore.data.map {
            it[getType(key,default)]?:default
        }
    }

    fun getSyncDs(context: Context,key: String,default:Any):String{
        var s = ""
        runBlocking {
            context.dataStore.data.first {
                s= (it[getType(key,default)]?:default) as String
                true
            }
        }

        return s
    }

    private fun getType(key:String,any: Any):Preferences.Key<*>{
        return when (any) {
            is String -> stringPreferencesKey(key)
            is Int -> intPreferencesKey(key)
            is Boolean -> booleanPreferencesKey(key)
            is Float -> floatPreferencesKey(key)
            is Long -> longPreferencesKey(key)
            is Set<*> -> stringSetPreferencesKey(key)
            else-> stringPreferencesKey(key)
        }
    }

    suspend fun setDs(context: Context,key: String,value:Any){
        context.dataStore.edit {
            sett(it,key,value)
        }
    }

    private fun sett(setting: MutablePreferences,key: String,value: Any){
        when (value) {
            is String -> setting[stringPreferencesKey(key)] = value
            is Int -> setting[intPreferencesKey(key)] = value
            is Boolean -> setting[booleanPreferencesKey(key)] = value
            is Float -> setting[floatPreferencesKey(key)] = value
            is Long -> setting[longPreferencesKey(key)] = value
            is Set<*> -> setting[stringSetPreferencesKey(key)] = (value as Set<String>)
            else-> setting[stringPreferencesKey(key)] = value.toString()
        }
    }

}