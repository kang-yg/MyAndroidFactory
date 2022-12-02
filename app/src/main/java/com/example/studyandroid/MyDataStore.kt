package com.example.studyandroid

import android.content.Context
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.studyandroid.view.DataStore.MyPersonProtoSerializer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MyDataStore {
    private val Context.preferenceDataStore by preferencesDataStore(preferenceDataStoreName)
    private val Context.protoDataStore by dataStore(
        fileName = "Persons.pb",
        serializer = MyPersonProtoSerializer
    )

    suspend fun writePreferenceDataStoreMyNumber() {
        context.preferenceDataStore.edit { myPreferenceDataStore ->
            val currentMyNum = myPreferenceDataStore[preferenceDataStoreMyNumberKey] ?: 0
            myPreferenceDataStore[preferenceDataStoreMyNumberKey] = currentMyNum + 1
        }
    }

    fun readPreferenceDataStoreMyNumber(): Flow<Int> {
        return context.preferenceDataStore.data.map { myPreferenceDataStore ->
            myPreferenceDataStore[preferenceDataStoreMyNumberKey] ?: 0
        }
    }

    suspend fun writeProtoDataStore(myPersonName: String, myPersonAge: Int) {
        context.protoDataStore.updateData {
            it.toBuilder().setName(myPersonName).setAge(myPersonAge).build()
        }
    }

    fun readProtoDataStoreMyPerson(): Flow<MyPersonProto> = context.protoDataStore.data

    companion object {
        private const val preferenceDataStoreName = "MyPreferenceDataStore"
        private val preferenceDataStoreMyNumberKey = intPreferencesKey("MyNumber")

        private lateinit var context: Context
        private var instance: MyDataStore? = null
        fun getInstance(_context: Context): MyDataStore {
            return instance ?: synchronized(this) {
                instance ?: MyDataStore().also {
                    context = _context
                    instance = it
                }
            }
        }
    }
}