package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*
import util.ftp.ftpFun
import util.nfc.getNfcId

@Composable
fun HomeScreen(){
    Column (modifier = Modifier.padding(16.dp).fillMaxWidth()){
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "\nWelkom bij de filecard app",
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Wilt u scannen druk op Scan aan de onderkant van uw scherm",
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Wilt u uw downloads zien druk dan op Downloads aan de onderkant van uw scherm",
            style = MaterialTheme.typography.titleMedium,
        )
    }

}

