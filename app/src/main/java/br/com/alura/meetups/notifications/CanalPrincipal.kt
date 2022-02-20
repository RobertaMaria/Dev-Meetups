package br.com.alura.meetups.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import br.com.alura.meetups.R

const val IDENTIFICADOR_DO_CANAL = "principal"

class CanalPrincipal(private val context: Context, private val manager: NotificationManager) {

    fun criaCanal(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val nome = context.getString(R.string.channel_name)
            val descricao = context.getString(R.string.channel_description)
            val importancia = NotificationManager.IMPORTANCE_DEFAULT
            val canal = NotificationChannel(IDENTIFICADOR_DO_CANAL, nome, importancia)
            canal.description = descricao
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            manager.createNotificationChannel(canal)
        }
    }
}