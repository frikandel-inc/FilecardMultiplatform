package com.filecard.multiplatform

import App
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi


//class test : TagDiscovery.onTagDiscoveryCompletedListener {}

@RequiresApi(Build.VERSION_CODES.KITKAT)
class MainActivity : ComponentActivity()  {
    private var mNfcAdapter: NfcAdapter? = null

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        // onResume() gets called after this to handle the intent
        setIntent(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)

        setContent {
            val tag: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
            var NfcId: Long = 0L
            if (tag != null) {
                NfcId = tag.id.toString().toLong()
            }


            App(NfcId)
//            App()
        }
    }
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

}



//@Preview
//@Composable
//fun AppAndroidPreview() {
//    App()
//}