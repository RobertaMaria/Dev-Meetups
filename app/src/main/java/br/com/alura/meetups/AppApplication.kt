package br.com.alura.meetups

import android.app.Application
//import br.com.alura.meetups.di.appModules
import br.com.alura.meetups.di.repositoryModule
import br.com.alura.meetups.di.retrofitModule
import br.com.alura.meetups.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@AppApplication)
            modules(retrofitModule)
            modules(viewModelModule)
            modules(repositoryModule)
        }
    }

}