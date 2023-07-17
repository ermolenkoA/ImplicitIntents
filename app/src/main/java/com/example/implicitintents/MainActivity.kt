package com.example.implicitintents

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ShareCompat
import com.example.implicitintents.Utils.Utils.locQuery
import com.example.implicitintents.Utils.Utils.mimeType
import com.example.implicitintents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val tag = "ImplicitIntents"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun openWebsite(view: View) {
        val webpage = Uri.parse(binding.websiteEdittext.text.toString())
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        tryStartActivity(intent)
    }

    fun openLocation(view: View) {
        val addressUri = Uri.parse(locQuery + binding.locationEdittext.text.toString())
        val intent = Intent(Intent.ACTION_VIEW, addressUri)
        tryStartActivity(intent)
    }

    fun shareText(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
            .setType(mimeType)
            .putExtra(Intent.EXTRA_TEXT, binding.shareEdittext.text.toString())
        startActivity(Intent.createChooser(intent, "Share this text with:"))
    }

    private fun tryStartActivity(intent: Intent) {
        try {
            startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            Log.d(tag, "Can't handle this intent!")
        }
    }
}