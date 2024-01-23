package com.filecard.multiplatform

import App
import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.defaultComponentContext
import nav.DefaultRootComponent


var status by mutableStateOf(0L)
fun readstatus(): Long {
    return status
}
class MainActivity : ComponentActivity() {
    private lateinit var pendingIntent: PendingIntent


    private fun intentFlags() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }

    @OptIn(ExperimentalDecomposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root =
            DefaultRootComponent(
                componentContext = defaultComponentContext(),
            )
        pendingIntent =
            PendingIntent.getActivity(
                this, 0,
                Intent(this, javaClass).apply {
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                }, intentFlags()
            )

        setContent {
            App(root)
        }
    }

    override fun onResume() {
        super.onResume()
        if (NfcAdapter.getDefaultAdapter(this@MainActivity) != null) {
            NfcAdapter.getDefaultAdapter(this@MainActivity).enableForegroundDispatch(this@MainActivity, pendingIntent, null, null)
            Toast.makeText(this, "NFC supported", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "NFC not supported", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPause() {
        if (NfcAdapter.getDefaultAdapter(this@MainActivity) != null) {
            NfcAdapter.getDefaultAdapter(this@MainActivity).enableForegroundDispatch(this@MainActivity, pendingIntent, null, null)
            Toast.makeText(this, "NFC supported", Toast.LENGTH_SHORT).show()
            if (NfcAdapter.getDefaultAdapter(this@MainActivity).isEnabled) {
                Toast.makeText(this, "NFC enabled", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "NFC not enabled", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "NFC not supported", Toast.LENGTH_SHORT).show()
        }
        super.onPause()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        readTag(intent)
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun readTag(intent: Intent) {
        val tag: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
        if (tag != null) {
            status = tag.id.toHexString().hexToLong()
        } else {
            status = 0L
        }
    }
}

//@Preview
//@Composable
//fun AppAndroidPreview() {
//    App(root)
//}
