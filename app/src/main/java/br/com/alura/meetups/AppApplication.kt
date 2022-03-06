package br.com.alura.meetups

import android.app.Application
import br.com.alura.meetups.di.*
import br.com.alura.meetups.notifications.CanalPrincipal
import org.koin.android.ext.android.inject
//import br.com.alura.meetups.di.appModules
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
            modules(preferencesModule)
            modules(notificacaoModule)
        }

        val canalPrincipal : CanalPrincipal by inject()
        canalPrincipal.criaCanal()
    }


}