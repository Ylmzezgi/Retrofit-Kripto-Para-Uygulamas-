package com.ezgiyilmaz.composedenemeleri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ezgiyilmaz.composedenemeleri.model.CryptoModel
import com.ezgiyilmaz.composedenemeleri.service.CryptoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                MainScreen()
        }

    }
    @Composable
    fun MainScreen(){
        val BASE_URL = "https://raw.githubusercontent.com/"
        var cryptoModels=remember { mutableStateListOf<CryptoModel>() }
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoAPI::class.java)

        val call = retrofit.getData()

        call.enqueue(object : Callback<List<CryptoModel>> {

            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        cryptoModels.addAll(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.localizedMessage
            }
        })

        Scaffold(topBar = {AppBar()}){
            CryptoList(cryptos = cryptoModels)
        }
    }
    @Composable
    fun CryptoList(cryptos:List<CryptoModel>){

        LazyColumn (contentPadding = PaddingValues(5.dp)){
            items(cryptos){crypto->
                CryptoRow(crypto=crypto)
            }
        }
    }

    @Composable
    fun CryptoRow(crypto: CryptoModel){
        Column(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colors.surface)) {
            Text(text = crypto.currency,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(2.dp),
                fontWeight = FontWeight.Bold
                )
            Text(text = crypto.price,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(2.dp),)
        }
    }

    @Composable
    fun AppBar(){
        TopAppBar(contentPadding = PaddingValues(18.dp)){
            Text(text = "RETROFİT COMPOSE", fontSize = 20.sp)
        }
    }
    }