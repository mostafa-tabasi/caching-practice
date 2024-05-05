package com.cachingpractice.android.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cachingpractice.android.domain.Beer
import com.cachingpractice.android.ui.theme.CachingPracticeTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeerItem(beer: Beer) {
    Card(
        modifier = Modifier.padding(8.dp),
        onClick = {}) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp),
        ) {
            AsyncImage(
                beer.imageUrl,
                contentDescription = beer.name,
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = beer.name,
                    style = LocalTextStyle.current.copy(
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                    modifier = Modifier.padding(vertical = 4.dp),
                )
                Text(
                    text = beer.tagline,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 2.dp),
                )
                Text(
                    text = beer.description,
                    style = LocalTextStyle.current.copy(
                        fontSize = 13.sp,
                    ),
                    modifier = Modifier.padding(vertical = 2.dp),
                )
                Text(
                    text = "First brewed in ${beer.firstBrewed}",
                    style = LocalTextStyle.current.copy(
                        fontSize = 11.sp,
                    ),
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp),
                )
            }
        }
    }
}

@Preview
@Composable
private fun BeerItemPreview() {
    CachingPracticeTheme {
        BeerItem(
            Beer(
                id = 1,
                name = "Beer",
                tagline = "One of the coolest beers",
                firstBrewed = "10/1991",
                description = "This is the description for the beer. This is the description for the beer.",
                imageUrl = null,
            ),
        )
    }
}