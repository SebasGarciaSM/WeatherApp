package com.example.weatherapp.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.R
import com.example.weatherapp.domain.models.DomainState
import com.example.weatherapp.domain.models.WeatherDetailsModel
import com.example.weatherapp.domain.models.WindDetailsModel
import com.example.weatherapp.ui.UIWeatherUtils.appendDegreeFormat
import com.example.weatherapp.ui.UIWeatherUtils.appendMetersPerSecondFormat
import com.example.weatherapp.ui.UIWeatherUtils.appendPercentageFormat
import com.example.weatherapp.ui.core.DetailText
import com.example.weatherapp.ui.core.ErrorView
import com.example.weatherapp.ui.core.LoadingView
import com.example.weatherapp.ui.theme.WeatherTheme
import com.example.weatherapp.ui.viewmodels.CityViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CityLookupScreen(modifier: Modifier = Modifier, viewModel: CityViewModel = hiltViewModel()) {

    val city by remember { viewModel.city }
    val weatherState by remember { viewModel.cityState }

    CityLookupScreenContent(
        modifier = modifier,
        city = city,
        onCityWriting = { viewModel.search(it) },
        weatherState = weatherState,
        onSearch = { viewModel.getCity() },
        onCleanQuery = { viewModel.onCleanQuery() }
    )
}

@Composable
private fun CityLookupScreenContent(
    modifier: Modifier = Modifier,
    city: String,
    onCityWriting: (String) -> Unit,
    weatherState: DomainState<WeatherDetailsModel>,
    onSearch: () -> Unit,
    onCleanQuery: () -> Unit,
) {
    Column(modifier = modifier) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = city,
            onValueChange = onCityWriting,
            leadingIcon = {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = stringResource(R.string.search_icon),
                )
            },
            trailingIcon = {
                IconButton(onClick = onCleanQuery) {
                    Icon(
                        Icons.Filled.Clear,
                        contentDescription = stringResource(R.string.clear_icon),
                    )
                }
            },
            keyboardActions = KeyboardActions(onSearch = { onSearch.invoke() }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            singleLine = true,
        )
        //REVIEW DATA STATE
        when (weatherState) {
            DomainState.Loading -> LoadingView(modifier = Modifier.fillMaxSize())
            is DomainState.Error -> ErrorView(
                modifier = Modifier.fillMaxSize(),
                message = weatherState.message
            )
            is DomainState.Success -> SuccessView(
                modifier = Modifier.fillMaxSize(),
                weatherState.data
            )
        }
    }
}

@Composable
private fun SuccessView(modifier: Modifier = Modifier, data: WeatherDetailsModel) {
    Column(modifier = modifier.padding(horizontal = 20.dp)) {
        ElevatedCard(modifier = Modifier.padding(vertical = 10.dp)) {
            Column(modifier = Modifier.padding(vertical = 10.dp)) {
                Text(data.name, modifier = Modifier.align(Alignment.CenterHorizontally))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    GlideImage(
                        modifier = Modifier.size(100.dp),
                        imageModel = { data.icon },
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center,
                        )
                    )
                    Text(
                        data.temperatureInCelsius.appendDegreeFormat(),
                        fontSize = 60.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        HorizontalDivider(Modifier, 1.dp, DividerDefaults.color)
        DetailText(
            title = stringResource(R.string.feelsLike),
            content = data.feelsLikeInCelsius.appendDegreeFormat()
        )
        DetailText(
            title = stringResource(R.string.maxTemp),
            content = data.maxTempInCelsius.appendDegreeFormat()
        )
        DetailText(
            title = stringResource(R.string.minTemp),
            content = data.minTempInCelsius.appendDegreeFormat()
        )
        DetailText(
            title = stringResource(R.string.pressure),
            content = data.pressure
        )
        DetailText(
            title = stringResource(R.string.humidity),
            content = data.humidity.appendPercentageFormat()
        )
        DetailText(
            title = stringResource(R.string.sealevel),
            content = data.seaLevel
        )
        HorizontalDivider(Modifier, 1.dp, DividerDefaults.color)
        ElevatedCard(modifier = Modifier.padding(vertical = 10.dp)) {
            Column(modifier = Modifier.padding(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = R.drawable.ic_wind),
                        contentDescription = stringResource(R.string.wind_icon),
                    )
                    Text(stringResource(R.string.wind), modifier = Modifier.padding(start = 10.dp))
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    WindDetailColumn(
                        title = stringResource(R.string.speed),
                        content = data.wind.speed.appendMetersPerSecondFormat()
                    )
                    WindDetailColumn(
                        title = stringResource(R.string.gust),
                        content = data.wind.gust.appendMetersPerSecondFormat()
                    )
                    WindDetailColumn(
                        title = stringResource(R.string.degrees),
                        content = data.wind.degrees.appendDegreeFormat()
                    )
                }
            }
        }
    }
}

@Composable
private fun WindDetailColumn(title: String, content: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(title)
        Text(content, fontWeight = FontWeight.Bold)
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)
@Composable
private fun CityLookupScreenPreview() {
    WeatherTheme {
        CityLookupScreenContent(
            modifier = Modifier.fillMaxSize(),
            city = "Atlanta",
            onCityWriting = {},
            weatherState = DomainState.Success(
                WeatherDetailsModel(
                    name = "Atlanta",
                    temperatureInCelsius = 50,
                    feelsLikeInCelsius = 80,
                    maxTempInCelsius = 50,
                    minTempInCelsius = 50,
                    pressure = "50",
                    humidity = "50",
                    seaLevel = "50",
                    wind = WindDetailsModel(
                        speed = 50.0,
                        gust = 50.0,
                        degrees = 50,
                    ),
                    icon = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.flaticon.com%2Ffree-icon%2Fexample_5650380&psig=AOvVaw1ihAnFILIAGQY0s8lFq9Dn&ust=1751484730924000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCIjvw5uznI4DFQAAAAAdAAAAABAE",
                )
            ),
            onSearch = {},
            onCleanQuery = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoadingViewPreview(modifier: Modifier = Modifier) {
    LoadingView(modifier = modifier.fillMaxSize())
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ErrorViewPreview(modifier: Modifier = Modifier) {
    ErrorView(modifier = modifier.fillMaxSize(), message = "Error")
}