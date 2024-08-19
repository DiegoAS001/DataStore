package edu.ifsp.dmo.datastoreexemplo.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore by  preferencesDataStore(name = "filedatastore")


class UserRepository (context: Context) {

    //Definição de um companion object para representar os campos do aarquivo DataStore
    companion object{
        val ATTR_USERNAME = stringPreferencesKey("username")
    }

    //Atributo de referencia ao DataStore do Context
    private val dataStore = context.dataStore

    //Atributo Flow que posui o uername salvo no DataStore
    val usernameFlow: Flow<String?> = dataStore.data.catch {expection ->
        if (expection is IOException){
            emit(emptyPreferences())
        }else{
            throw  expection
        }
    }
        .map {
            it.get(ATTR_USERNAME)
        }

    suspend fun saveUsername(username: String){
        dataStore.edit { it[ATTR_USERNAME] = username
        }
    }
}