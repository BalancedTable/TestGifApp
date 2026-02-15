package com.example.testtask.ui.themeAndScreens.Screens

import com.example.testtask.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.testtask.network.GIF

@Composable
fun FirstScreen(
    gifUIState: GifUiState,
    retryAction:() -> Unit,
    onGifClick: (GIF) -> Unit,
    modifier: Modifier = Modifier
) {
    when(gifUIState){
        is GifUiState.Success ->
            GifGridScreen(gifUIState.gifs,modifier = modifier, onGifClick = onGifClick)
        is GifUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is GifUiState.Error -> ErrorScreen(retryAction, modifier.fillMaxSize())
    }
}
    @Composable
    fun LoadingScreen(modifier: Modifier = Modifier){
        Image(
            modifier = modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )
    }

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun GifCard(
    gif: GIF,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = {onClick(gif.gifSrc)}
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(gif.gifSrc)
                .crossfade(true)
                .allowHardware(false)
                .build(),
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.gif),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun GifGridScreen(
    gif: List<GIF>,
    onGifClick: (GIF) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.padding(horizontal = 4.dp),
        contentPadding = contentPadding
    ) {
        items(items = gif, key = { gifs -> gifs.id }){
            gif -> GifCard(
                gif,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f),
                onClick = {onGifClick(gif)}
            )
        }
    }
}