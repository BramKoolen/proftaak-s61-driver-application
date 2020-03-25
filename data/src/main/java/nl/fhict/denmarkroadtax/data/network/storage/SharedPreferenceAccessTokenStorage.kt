package nl.fhict.denmarkroadtax.data.network.storage

import android.content.Context
import android.content.SharedPreferences
import nl.fhict.denmarkroadtax.data.generic.dagger.DataContext
import javax.inject.Inject

class SharedPreferenceAccessTokenStorage @Inject constructor(@DataContext context: Context) : AccessTokenStorage {

    override var accessToken: String?
        get() = sharedPrefs.getString(ACCESS_TOKEN, null).orEmpty()
        set(value) {
            sharedPrefs.edit()
                    .putString(ACCESS_TOKEN, value)
                    .apply()
        }

    private val sharedPrefs: SharedPreferences by lazy {
        context.getSharedPreferences(ACCESS_TOKEN_STORAGE, Context.MODE_PRIVATE)
    }

    private companion object {

        const val ACCESS_TOKEN_STORAGE = "ACCESS_TOKEN_STORAGE"
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
    }
}