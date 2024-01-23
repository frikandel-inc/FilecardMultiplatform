package com.filecard.multiplatform

import ftptest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import util.ftp.FtpClientAndroid

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
        val ftpclient = FtpClientAndroid()
        ftptest(ftpclient)
    }
}