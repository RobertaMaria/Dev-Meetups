package br.com.alura.meetups.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.toBitmap
import br.com.alura.meetups.R
import br.com.alura.meetups.model.Dispositivo
import br.com.alura.meetups.notifications.IDENTIFICADOR_DO_CANAL
import br.com.alura.meetups.preferences.FirebaseTokenPreferences
import br.com.alura.meetups.repository.DispositivoRepotisitory
import coil.imageLoader
import coil.request.ImageRequest
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject



class MeetupsFirebaseMessagingService : FirebaseMessagingService() {

    private val dispositivoRepotisitory: DispositivoRepotisitory by inject()
    private val preferences : FirebaseTokenPreferences by inject()

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i("TAG", "onNewToken: $token ")

        preferences.tokenNovo()
        dispositivoRepotisitory.salva(Dispositivo(token = token))
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.i("TAG", "onMessageReceived: Notificacao recebida: ${remoteMessage.notification?.title}")
        Log.i("TAG", "onMessageReceived: Dado recebida ${remoteMessage.data}")
        val data = remoteMessage.data

        val gerenciadorDeNotificacao = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        criaCanal(gerenciadorDeNotificacao)

        mostraNotificacao(data, gerenciadorDeNotificacao)


    }

    private fun mostraNotificacao(
        data: Map<String, String>,
        gerenciadorDeNotificacao: NotificationManager,
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val request = ImageRequest.Builder(this@MeetupsFirebaseMessagingService)
                .data(data["imagem"])
                .build()

            val imagem = imageLoader.execute(request).drawable?.toBitmap()

            val notificacao = NotificationCompat.Builder(this@MeetupsFirebaseMessagingService,
                IDENTIFICADOR_DO_CANAL)
                .setContentTitle(data["titulo"])
                .setContentText(data["descricao"])
                .setSmallIcon(R.drawable.ic_acao_novo_evento)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                //.setStyle(NotificationCompat.BigTextStyle()//para texto expansivos, grandes
                //.bigText(data["descricao"]))
                .setStyle(NotificationCompat.BigPictureStyle()//imagem grande
                    .bigPicture(imagem)
                    .bigLargeIcon(null))
                .setLargeIcon(imagem)
                .build()

            gerenciadorDeNotificacao.notify(1, notificacao)
        }
    }

    private fun criaCanal(gerenciadorDeNotificacao: NotificationManager) {

    }
}