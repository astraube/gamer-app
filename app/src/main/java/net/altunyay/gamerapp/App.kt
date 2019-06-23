package net.altunyay.gamerapp

import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import net.altunyay.gamerapp.di.appModule
import net.altunyay.gamerapp.di.networkModule
import net.altunyay.gamerapp.common.utils.ClientPreferences
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : MultiDexApplication() {

    private val preferences : ClientPreferences by inject()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOfModules)
        }

        if(preferences.isDarkModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}

val listOfModules = listOf(appModule, networkModule)