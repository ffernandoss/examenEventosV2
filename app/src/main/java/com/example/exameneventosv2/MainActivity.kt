package com.example.exameneventosv2

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var buttonChangeLanguage: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment())
                .commit()
        }

        buttonChangeLanguage = findViewById(R.id.buttonChangeLanguage)
        buttonChangeLanguage.setOnClickListener {
            val currentLocale = resources.configuration.locale
            if (currentLocale.language == "en") {
                setLocale("es")
                buttonChangeLanguage.text = getString(R.string.ingles)
            } else {
                setLocale("en")
                buttonChangeLanguage.text = getString(R.string.espanol)
            }
        }
    }

    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)

        // Reiniciar la actividad para aplicar los cambios de idioma
        val refresh = Intent(this, MainActivity::class.java)
        startActivity(refresh)
        finish()
    }
}