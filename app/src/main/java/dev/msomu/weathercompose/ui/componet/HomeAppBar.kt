package dev.msomu.weathercompose.ui.componet

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.msomu.weathercompose.R

@Composable
fun HomeAppBar(
    modifier: Modifier = Modifier
) {
    SmallTopAppBar(
        title = {
                Text(text = stringResource(R.string.app_name), style = MaterialTheme.typography.titleLarge)
        },
        modifier = modifier,
    )
}

@Preview
@Composable
fun HomeAppBarPreview() {
    HomeAppBar()
}