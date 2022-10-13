package com.example.datestoredemo

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

val Context.demoDataStore:DataStore<Demo.Demos> by dataStore(fileName = "demo.pb", serializer = DemosSerializer)

object ProtoDataStoreU {

    fun getDs(context: Context):Flow<*>{
        return context.demoDataStore.data.map {
            it.eee
        }
    }

    fun getSyncDs(context: Context):String{
        var s = ""
        runBlocking {
            context.demoDataStore.data.first {
                s=it.eee
                true
            }
        }

        return s
    }
    suspend fun setDs(context: Context,string: String) {
        context.demoDataStore.updateData {
            it.toBuilder().setEee(string)
                .build()
        }
    }
}