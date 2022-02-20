package br.com.alura.meetups.repository

import android.util.Log
import br.com.alura.meetups.model.Dispositivo
import br.com.alura.meetups.preferences.FirebaseTokenPreferences
import br.com.alura.meetups.webclient.DispositivoService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "Meetups"

class DispositivoRepotisitory(private val service: DispositivoService,
                              private val preferences: FirebaseTokenPreferences) {

    fun salva(dispositivo: Dispositivo) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val resposta = service.salva(dispositivo)

                if(resposta.isSuccessful){
                    preferences.tokenEnviado()
                    Log.i(TAG, "salva: token enviado ${dispositivo.token}")
                }else{
                    Log.i(TAG, "salva: token não enviado")
                }
            } catch (e: Exception) {
                Log.e(TAG, "salva: falha ao enviar o token")
            }
        }
    }
}

