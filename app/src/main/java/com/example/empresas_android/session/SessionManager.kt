package com.example.empresas_android.session

import android.content.Context
import android.content.SharedPreferences

class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences("app_teste", Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_CLIENT = "client"
        const val USER_UID = "uid"
    }

    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.commit()
    }

    fun saveAuthClient(client: String) {
        val editor = prefs.edit()
        editor.putString(USER_CLIENT, client)
        editor.commit()
    }

    fun saveAuthUid(uid: String) {
        val editor = prefs.edit()
        editor.putString(USER_UID, uid)
        editor.commit()
    }


    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    fun fetchClient(): String? {
        return prefs.getString(USER_CLIENT, null)
    }

    fun fetchUid(): String? {
        return prefs.getString(USER_UID, null)
    }
}
