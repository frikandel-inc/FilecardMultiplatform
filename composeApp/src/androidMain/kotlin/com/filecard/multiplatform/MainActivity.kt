package com.filecard.multiplatform

import App
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.st.st25sdk.NFCTag
import com.st.st25sdk.STException
import com.st.st25sdk.TagHelper


class MainActivity : ComponentActivity() , TagDiscovery.onTagDiscoveryCompletedListener  {
    private var mNfcAdapter: NfcAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
        setContent {
            App()
        }

    }

    override fun onPause() {
        super.onPause()
        mNfcAdapter?.disableForegroundDispatch(this)
    }

    override fun onResume() {
        super.onResume()
        // Check if if this phone has NFC hardware
        //Toast.makeText(this, "We are ready to play with NFC!", Toast.LENGTH_SHORT).show();
// Give priority to the current activity when receiving NFC events (over other actvities)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, Intent(
                this,
                javaClass
            ).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_IMMUTABLE
        )
        val nfcFilters: Array<IntentFilter>? = null
        val nfcTechLists: Array<Array<String>>? = null
        mNfcAdapter?.enableForegroundDispatch(this, pendingIntent, nfcFilters, nfcTechLists)
        // The current activity can be resumed for several reasons (NFC tag tapped is one of them).
// Check what was the reason which triggered the resume of current application
        val intent = intent
        val action = intent.action
        if (action == NfcAdapter.ACTION_NDEF_DISCOVERED || action == NfcAdapter.ACTION_TECH_DISCOVERED || action == NfcAdapter.ACTION_TAG_DISCOVERED) {
// If the resume was triggered by an NFC event, it will contain an EXTRA_TAG provinding
// the handle of the NFC Tag
            val androidTag: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
            val nfcTag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
            if (nfcTag != null) {
                Toast.makeText(this, "Starting Tag discovery", Toast.LENGTH_SHORT).show();
// This action will be done in an Asynchronous task.
// onTagDiscoveryCompleted() of current activity will be called when the discovery is completed.

                TagDiscovery(this).execute(androidTag)
            }
        }
    }

    override fun onTagDiscoveryCompleted(
        nfcTag: NFCTag?,
        productId: TagHelper.ProductID?,
        e: STException?
    ) {
        if (nfcTag != null) {
            var tagName:String = nfcTag.getName();
            Toast.makeText(this, "Tag discovery done. Found tag: " + tagName, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Tag discovery failed!", Toast.LENGTH_LONG).show();
        }
    }
}

    @Preview
@Composable
fun AppAndroidPreview() {
    App()
}