package com.filecard.multiplatform

import App
import android.content.Intent
import android.os.Bundle
import android.util.SparseArray
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.romellfudi.fudinfc.util.sync.NfcReadUtilityImpl
import nfc_id

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val items: SparseArray<String?>? = NfcReadUtilityImpl().readFromTagWithSparseArray(intent)
        if (items != null) {
            for (i in 0 until items.size()) {
                items.valueAt(i)
                nfc_id()
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}