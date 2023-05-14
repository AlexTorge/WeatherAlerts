package com.example.composeweather.alertlist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.composeweather.BuildConfig
import com.example.composeweather.entities.WeatherAlert
import com.example.composeweather.toPrettyFormat
import com.example.composeweather.toPrettyTime

@Composable
fun WeatherAlertsList(weatherAlerts: List<WeatherAlert>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        itemsIndexed(weatherAlerts) { index: Int, alert: WeatherAlert ->
            WeatherAlertItem(
                alert,
                Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun WeatherAlertItem(weatherAlert: WeatherAlert, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(24.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            val context = LocalContext.current

            val loader by remember(weatherAlert) {
                mutableStateOf(ImageLoader.Builder(context).build())
            }

            AsyncImage(
                model = BuildConfig.IMAGE_URL,
                contentDescription = null,
                modifier = Modifier.size(60.dp),
                imageLoader = loader
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = weatherAlert.event)
                Text(text = "Duration = ${weatherAlert.durationInMin.toPrettyTime()}")
                Text(
                    text = "Starts at ${weatherAlert.starts.toPrettyFormat()}"
                )
                Text(text = "Ends at ${weatherAlert.ends.toPrettyFormat()}")

            }

        }
    }

}