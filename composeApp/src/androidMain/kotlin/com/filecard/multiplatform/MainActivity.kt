package com.filecard.multiplatform

import App
import ftptest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import serverutil.FtpClientAndroid
import kotlinx.coroutines.*
import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val ftpclient = FtpClientAndroid()
        super.onCreate(savedInstanceState)
        setContent {
            ftptest(ftpclient)
        }

    }

    @Preview
    @Composable
    fun AppAndroidPreview() {
        App()
    }
}