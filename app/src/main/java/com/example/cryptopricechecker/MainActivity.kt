package com.example.cryptopricechecker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import com.example.cryptopricechecker.ui.theme.CryptoPriceCheckerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoPriceCheckerTheme {


                CryptoPricePager()
            }
        }
    }
}

@Composable
fun CryptoPricePager() {

    var cryptoCoins = emptyArray<CryptoData>()
    for(i in 1..10) {
        val testName = "Crypto " + i
        val testPrice = i * 10000
        val testImage = painterResource(R.drawable.bitcoin)
        val data = CryptoData(testName, testPrice, testImage)

        cryptoCoins += data
    }


    val pagerState = rememberPagerState(initialPage = 0) {
        cryptoCoins.size
    }
    var selectedTab by remember {
        mutableIntStateOf(pagerState.currentPage)
    }




    Column {
        TabRow(selectedTabIndex = selectedTab) {

        }
    }

    HorizontalPager(state = pagerState) { currentPage ->
        CryptoPage(cryptoCoins, currentPage)
    }
}

@Composable
fun CryptoPage(cryptoCoins: Array<CryptoData>, pageNumber: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = cryptoCoins[pageNumber].name)
        Text(text = cryptoCoins[pageNumber].price.toString())
        Image(painter = cryptoCoins[pageNumber].image,
              contentDescription = "Crypto Icon")

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