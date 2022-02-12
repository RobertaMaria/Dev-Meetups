package br.com.alura.meetups.webclient

import br.com.alura.meetups.model.Dispositivo
import br.com.alura.meetups.model.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface DispositivoService {

    @POST("devices")
    suspend fun salva(@Body dispositivo: Dispositivo): Response<Unit>
}