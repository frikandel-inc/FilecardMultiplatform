package com.filecard.multiplatform

import App
import android.nfc.NfcAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.st.st25sdk.NFCTag
import com.st.st25sdk.STException
import com.st.st25sdk.TagHelper

//class test : TagDiscovery.onTagDiscoveryCompletedListener {}

class MainActivity : ComponentActivity() , TagDiscovery.onTagDiscoveryCompletedListener {
    private var mNfcAdapter: NfcAdapter? = null

    init {
        Helpe
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
        setContent {
            App()
        }
    }
    override fun onTagDiscoveryCompleted(
        nfcTag: NFCTag?,
        productId: TagHelper.ProductID?,
        e: STException?
    ) {
        if (nfcTag != null) {
            val tagName:String = nfcTag.uidString;
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