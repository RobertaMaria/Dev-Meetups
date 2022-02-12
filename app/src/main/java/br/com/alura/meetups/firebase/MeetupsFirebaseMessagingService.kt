package br.com.alura.meetups.firebase

import android.util.Log
import br.com.alura.meetups.model.Dispositivo
import br.com.alura.meetups.repository.DispositivoRepotisitory
import com.google.firebase.messaging.FirebaseMessagingService
import org.koin.android.ext.android.inject

class MeetupsFirebaseMessagingService : FirebaseMessagingService() {

    private val dispositivoRepotisitory: DispositivoRepotisitory by inject()

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i("TAG", "onNewToken: $token ")
        dispositivoRepotisitory.salva(Dispositivo(token = token))
    }
}