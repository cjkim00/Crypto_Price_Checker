package com.example.cryptopricechecker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cryptopricechecker.ui.theme.CryptoPriceCheckerTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoPriceCheckerTheme {
                val cryptoCoins = mutableListOf<CryptoData>()
                for(i in 1..10) {
                    val testName = "Crypto " + i
                    val testPrice = i * 10000
                    val testImage = painterResource(R.drawable.bitcoin)
                    val data = CryptoData(testName, testPrice, testImage)

                    cryptoCoins.add(data)
                }

                for(data in cryptoCoins) {
                    Log.d("TEST DATA", "TEST: " + data.name + " " + data.price)
                }

                CryptoPricePager()
            }
        }
    }
}

@Composable
fun CryptoPricePager() {
    val pagerState = rememberPagerState(initialPage = 0) {
        3
    }
    var selectedTab by remember {
        mutableIntStateOf(pagerState.currentPage)
    }

    Column {
        TabRow(selectedTabIndex = selectedTab) {

        }
    }

    HorizontalPager(state = pagerState) { currentPage ->
        ScreenOne(
            pageNumber = currentPage.toString()
        )
    }
}

@Composable
fun CryptoPage(cryptoName: String, cryptoPrice: Int, cryptoImage: Painter, cryptoData: CryptoData) {
    Text(text = cryptoName)
}

@Composable
fun ScreenOne(pageNumber: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = pageNumber)
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {


}

class CryptoData constructor(cryptoName: String, cryptoPrice: Int, cryptoIcon: Painter) {
    val name = cryptoName
    val price = cryptoPrice
    val image = cryptoIcon
}