package dev.msomu.weathercompose.ui.componet

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeAppBar(
    title : String,
    modifier: Modifier = Modifier
) {
    SmallTopAppBar(
        title = {
                Text(text = title, style = MaterialTheme.typography.titleLarge, color = Color.White)
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = modifier,
    )
}

@Preview
@Composable
fun HomeAppBarPreview() {
    HomeAppBar("Chennai")
}