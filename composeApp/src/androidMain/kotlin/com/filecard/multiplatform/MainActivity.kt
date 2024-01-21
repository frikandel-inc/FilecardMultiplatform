package com.filecard.multiplatform

import App
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.nfc.tech.NdefFormatable
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


//class test : TagDiscovery.onTagDiscoveryCompletedListener {}

//@RequiresApi(Build.VERSION_CODES.KITKAT)
//class MainActivity : ComponentActivity()  {
//    private var mNfcAdapter: NfcAdapter? = null
//
//    override fun onNewIntent(intent: Intent?) {
//        super.onNewIntent(intent)
//        // onResume() gets called after this to handle the intent
//        setIntent(intent)
//    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
//
//        setContent {
//            val tag = mutableStateOf<Tag?>( intent.getParcelableExtra(NfcAdapter.EXTRA_TAG))
//            var NfcId: Long = 0L
//            if (tag != null) {
//                NfcId = tag.id.toString().toLong()
//            }
//
//
//            App({ NfcId })
////            App()
//        }
//    }
//    override fun onTagDiscovered(tag: Tag?) {
//        if (tag != null) {
//            Toast.makeText(this, "Tag discovery done. Found tag: " + tag.toString(), Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(this, "Tag discovery failed!", Toast.LENGTH_LONG).show();
//        }
//    }

//    override fun onPause() {
//        super.onPause()
//        if (mNfcAdapter != null) {
//            mNfcAdapter!!.disableForegroundDispatch(this)
//        }
//    }
//
//    override fun onResume() {
//        super.onResume()
//        // Check if if this phone has NFC hardware
//        if (mNfcAdapter == null) {
//            val alertDialogBuilder = AlertDialog.Builder(this)
//            // set title
//            alertDialogBuilder.setTitle("Warning!")
//            // set dialog message
//            alertDialogBuilder
//                .setMessage("This phone doesn't have NFC hardware!")
//                .setMessage("This phone doesn't have NFC hardware!")
//                .setCancelable(true)
//                .setPositiveButton("Close") { dialog, id ->
//                    dialog.cancel()
//
//                }
//            // create alert dialog
//            val alertDialog = alertDialogBuilder.create()
//            // show it
//            alertDialog.show()
//        }
//        else {
////Toast.makeText(this, "We are ready to play with NFC!", Toast.LENGTH_SHORT).show();
//// Give priority to the current activity when receiving NFC events (over other actvities)
//            val pendingIntent = PendingIntent.getActivity(
//                this, 0, Intent(
//                    this,
//                    javaClass
//                ).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_IMMUTABLE
//            )
//            val nfcFilters: Array<IntentFilter>? = null
//            val nfcTechLists: Array<Array<String>>? = null
//            mNfcAdapter!!.enableForegroundDispatch(this, pendingIntent, nfcFilters, nfcTechLists)
//        }
//        // The current activity can be resumed for several reasons (NFC tag tapped is one of them).
//// Check what was the reason which triggered the resume of current application
//        val intent = intent
//        val action = intent.action
//        if (action == NfcAdapter.ACTION_NDEF_DISCOVERED || action == NfcAdapter.ACTION_TECH_DISCOVERED || action == NfcAdapter.ACTION_TAG_DISCOVERED) {
//// If the resume was triggered by an NFC event, it will contain an EXTRA_TAG providing
//// the handle of the NFC Tag
//            val nfcTag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
//            if (nfcTag != null) {
//                Toast.makeText(this, "NFC Tag detected!", Toast.LENGTH_LONG).show()
//            }
//        }
//    }
//     fun onTagDiscoveryCompleted(
//        nfcTag: NFCTag?,
//        productId: TagHelper.ProductID?,
//        e: STException?
//    ) {
//        if (nfcTag != null) {
//            val tagName:String = nfcTag.uidString;
//            Toast.makeText(this, "Tag discovery done. Found tag: " + tagName, Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(this, "Tag discovery failed!", Toast.LENGTH_LONG).show();
//        }
//    }

//}


//@Preview
//@Composable
//fun AppAndroidPreview() {
//    App({ 0L })
//}

class MainActivity : ComponentActivity() {
    private lateinit var pendingIntent: PendingIntent

    private val readFilters = arrayOf(
        IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED).apply {
            addDataScheme("http")
            addDataAuthority("javadude.com", null)
        },
        IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED, "text/plain")
    )

    private val writeFilters = emptyArray<IntentFilter>()

    private val writeTechList =
        arrayOf(arrayOf(Ndef::class.java.name), arrayOf(NdefFormatable::class.java.name))

    private var messageToWrite: NdefMessage? = null

    private var status by mutableStateOf("")
    private var input by mutableStateOf("")

    private fun intentFlags() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pendingIntent =
            PendingIntent.getActivity(
                this, 0,
                Intent(this, javaClass).apply {
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                }, intentFlags()
            )

        setContent {
            val statusLong = try {
                if (status.isNotEmpty()) {
                    status.toLong()
                } else {
                    // Handle the case when 'status' is empty
                    // In this example, using 0L as a default value
                    0L
                }
            } catch (e: NumberFormatException) {
                // Handle the case when 'status' cannot be converted to Long
                // In this example, using 0L as a default value
                0L
            }
            App( statusLong )

            // haal niet weg anders werkt het niet!!
            if (status.isNotEmpty()) {
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (NfcAdapter.getDefaultAdapter(this@MainActivity) != null) {

            NfcAdapter.getDefaultAdapter(this@MainActivity).enableForegroundDispatch(this@MainActivity, pendingIntent, readFilters, null)
        }
    }

    override fun onPause() {
        if (NfcAdapter.getDefaultAdapter(this@MainActivity) != null) {

            NfcAdapter.getDefaultAdapter(this@MainActivity).enableForegroundDispatch(this@MainActivity, pendingIntent, readFilters, null)
        }
        super.onPause()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        readTag(intent)
    }

    private fun readTag(intent: Intent) {
        status = ""
        val tag: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
        if (tag != null) {
            status = tag.id.toString()
        } else {
            status = "No tag found"
        }
    }
}


