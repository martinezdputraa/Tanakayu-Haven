package com.tanakayu.mini_projects.ui.compose

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tanakayu.mini_projects.data.MiniProjectsPokemonDictionary
import com.tanakayu.mini_projects.resource.FontBaloo

class MiniProjectsJetpackComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val pokemons = MiniProjectsPokemonDictionary.get()

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(pokemons.size) {
                    ImageCard(
                        painter = painterResource(pokemons[it].drawableRes),
                        title = getString(pokemons[it].title),
                        contentDescription = getString(pokemons[it].description),
                    )
                }
            }
        }
    }

    @Composable
    fun ImageCard(
        painter: Painter,
        contentDescription: String,
        title: String,
        modifier: Modifier = Modifier
    ) {
        Card(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            elevation = 6.dp
        ) {
            Box(modifier = Modifier.height(200.dp)) {
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = modifier.fillMaxSize()
                )
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                ),
                                startY = 300F
                            )
                        )
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Column {
                        val fontFamily = FontBaloo.get()
                        Text(
                            title, style = TextStyle(
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = fontFamily
                            )
                        )
                        Text(
                            contentDescription, style = TextStyle(
                                color = Color.White,
                                fontSize = 10.sp,
                                fontFamily = fontFamily
                            )
                        )
                    }
                }
            }
        }
    }

    companion object {
        fun startThisActivity(activity: Activity) {
            val intent = Intent(activity, MiniProjectsJetpackComposeActivity::class.java)
            activity.startActivity(intent)
        }
    }
}