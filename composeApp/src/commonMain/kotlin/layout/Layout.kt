package layout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.FlowPreview


@Composable
fun test() {
    Text("test")
}

@Composable
@FlowPreview
fun landing_page() {
    Column {
        Row(modifier = Modifier.weight(0.1F).background(Color.Green).fillMaxWidth(1F)) {
            Text(text="shit", Modifier)
        }
        Row(modifier = Modifier.weight(0.9F).background(Color.Red).fillMaxWidth(1F)) {
            Text(text="shit2", Modifier)

        }
    }
}

