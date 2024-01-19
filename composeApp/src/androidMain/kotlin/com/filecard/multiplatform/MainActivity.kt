package com.filecard.multiplatform

import App
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.romellfudi.fudinfc.util.sync.NfcReadUtilityImpl


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        for (message in NfcReadUtilityImpl().readFromTagWithMap(intent)!!.values!!) {
            // message


        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}