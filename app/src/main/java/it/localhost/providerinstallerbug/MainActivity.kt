package it.localhost.providerinstallerbug

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.security.ProviderInstaller
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(Dispatchers.IO) {
            val r1 = OkHttpClient().newCall(Request.Builder().url("https://www.google.com").build()).execute()
            println(r1.handshake)
            try {
                ProviderInstaller.installIfNeeded(this@MainActivity)
                println("ProviderInstaller DONE")
            } catch (e: Exception) {
                e.printStackTrace()
            }

            val r2 = OkHttpClient().newCall(Request.Builder().url("https://www.google.com").build()).execute()
            println(r2.handshake)
        }

    }
}