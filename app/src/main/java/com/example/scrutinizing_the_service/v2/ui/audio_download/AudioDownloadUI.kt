package com.example.scrutinizing_the_service.v2.ui.audio_download

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.scrutinizing_the_service.R
import com.example.scrutinizing_the_service.theme.ScrutinizingTheServiceTheme
import com.example.scrutinizing_the_service.v2.data.models.remote.last_fm.Rank
import com.example.scrutinizing_the_service.v2.data.models.remote.last_fm.Track
import com.example.scrutinizing_the_service.v2.paging.isAppending
import com.example.scrutinizing_the_service.v2.paging.isEmpty
import com.example.scrutinizing_the_service.v2.paging.isFirstLoad
import com.example.scrutinizing_the_service.v2.ui.search.result.SearchResultState
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun AudioDownloadUI(
    onDownloadItem: (Track) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AudioDownloadViewModel = hiltViewModel()
) {

    val items = viewModel.tracks.collectAsLazyPagingItems()

    val loadTargetState: SearchResultState by remember {
        derivedStateOf {
            return@derivedStateOf when {
                items.isEmpty.not() -> SearchResultState.Success

                items.isFirstLoad -> SearchResultState.Loading

                else -> SearchResultState.Error
            }
        }
    }

    Crossfade(
        targetState = loadTargetState,
        label = "Track Search transition",
        modifier = modifier.fillMaxSize()
    ) {
        when (it) {
            SearchResultState.Error -> {

            }

            SearchResultState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            SearchResultState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        items(
                            count = items.itemCount,
                            key = {
                                val key: (index: Int) -> Any = items.itemKey { item ->
                                    item.mbid + "$it"
                                }
                                key(it)
                            },
                            contentType = items.itemContentType { "tracks" },
                        ) { index ->
                            val item = items[index]
                            val url = when (index % 3) {
                                0 -> "https://onlinetestcase.com/wp-content/uploads/2023/06/1-MB-MP3.mp3"
                                1 -> "https://onlinetestcase.com/wp-content/uploads/2023/06/2-MB-MP3.mp3"
                                2 -> "https://onlinetestcase.com/wp-content/uploads/2023/06/10-MB-MP3.mp3"
                                else -> "https://onlinetestcase.com/wp-content/uploads/2023/06/10-MB-MP3.mp3"
                            }
                            if (item != null) {
                                TrackUI2(item, url, {
                                    onDownloadItem(item)
                                })
                            }
                        }

                        if (items.isAppending) {
                            item {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TrackUI2(
    item: Track,
    url: String,
    onClicked : () -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClicked()
            }
        ,
    ) {

        val (image, detail) = createRefs()

        GlideImage(
            imageModel = {
                if (item.image.isNotEmpty())
                    item.image[1].text
            },
            imageOptions = ImageOptions(
                contentScale = ContentScale.FillBounds
            ),
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .fillMaxWidth(0.3f)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(15))
                .background(Color.Green),
            failure = {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        R.drawable.ic_album_place_holder
                    ),
                    contentDescription = "Album place holder",
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .clip(RoundedCornerShape(15))
                        .background(Color.Gray)
                        .padding(12.dp)
                )
            },
            loading = {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        )
        Column(
            modifier = Modifier
                .padding(start = 12.dp)
                .constrainAs(detail) {
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                }
                .background(Color.Red),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = item.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = item.artist, style = MaterialTheme.typography.titleMedium)
            Text(text = "https://onlinetestcase.com/wp-content/uploads/2023/06/1-MB-MP3.mp3", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview
@Composable
fun TrackUI2Preview() {
    ScrutinizingTheServiceTheme {
        TrackUI2(
            item = Track(
                rank = Rank("1"),
                artist = "Mahmood",
                duration = "03:10",
                image = emptyList(),
                listeners = "3088",
                mbid = "",
                name = "Mahmood",
                streamable = "True",
                url = ""
            ),
            url = "https://onlinetestcase.com/wp-content/uploads/2023/06/1-MB-MP3.mp3" ,
            {}
        )
    }
}