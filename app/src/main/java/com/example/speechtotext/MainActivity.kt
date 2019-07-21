package com.example.speechtotext

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import android.content.ActivityNotFoundException
import android.speech.RecognizerIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val REQ_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupPermissions()

        imv_iran.setOnClickListener{
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            //For some county you can use only the below lines
            //Some countries not define in Locale
            //You can use this page for finding your language
            //https://cloud.google.com/speech-to-text/docs/languages
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "fa-IR")//Persian (Iran)
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Need to speak")

            try {
                startActivityForResult(intent, REQ_CODE)
            } catch (a: ActivityNotFoundException) {
                Toast.makeText(
                    applicationContext,
                    "Sorry! Your device not supported",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        imv_england.setOnClickListener{
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            //For some county you can use one of the below lines
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH)
            //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-GB")//English (United Kingdom)
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Need to speak")

            try {
                startActivityForResult(intent, REQ_CODE)
            } catch (a: ActivityNotFoundException) {
                Toast.makeText(
                    applicationContext,
                    "Sorry! Your device not supported",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        imv_france.setOnClickListener{
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.FRANCE)
            //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "fr-FR")//French (France)
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Need to speak")

            try {
                startActivityForResult(intent, REQ_CODE)
            } catch (a: ActivityNotFoundException) {
                Toast.makeText(
                    applicationContext,
                    "Sorry! Your device not supported",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        imv_south_korea.setOnClickListener{
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.KOREAN)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR")//Korean (South Korea)
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Need to speak")

            try {
                startActivityForResult(intent, REQ_CODE)
            } catch (a: ActivityNotFoundException) {
                Toast.makeText(
                    applicationContext,
                    "Sorry! Your device not supported",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==REQ_CODE){
            if (resultCode == RESULT_OK && data!=null) {
                val result = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                tv_result?.text=result[0].toString()
            }
        }
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.RECORD_AUDIO)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            //Log.i(Tag, "Permission to record denied")
        }
    }
}
