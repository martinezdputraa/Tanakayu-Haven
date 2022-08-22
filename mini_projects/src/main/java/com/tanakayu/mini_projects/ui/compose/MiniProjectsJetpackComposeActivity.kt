package com.tanakayu.mini_projects.ui.compose

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tanakayu.mini_projects.R
import com.tanakayu.mini_projects.data.MiniProjectsPokemon
import com.tanakayu.mini_projects.data.MiniProjectsPokemonDictionary
import com.tanakayu.mini_projects.resource.FontBaloo
import com.tanakayu.mini_projects.resource.LightGreen1

class MiniProjectsJetpackComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val pokemons = MiniProjectsPokemonDictionary.get()
            Box {
                Image(
                    painter = painterResource(id = R.drawable.pokeball),
                    contentDescription = "background_image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxSize()
                ) {
                    var state by remember {
                        mutableStateOf(ViewModes.LIST)
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                end = 16.dp
                            )
                    ) {
                        ToggleStyleButton(R.drawable.ic_list, isSelected = state == ViewModes.LIST) {
                            state = ViewModes.LIST
                        }
                        Spacer(modifier = Modifier.width(2.dp))
                        ToggleStyleButton(R.drawable.ic_grid_2x2, isSelected = state == ViewModes.GRIDDED_INFO) {
                            state = ViewModes.GRIDDED_INFO
                        }
                        Spacer(modifier = Modifier.width(2.dp))
                        ToggleStyleButton(R.drawable.ic_grid_3x3, isSelected = state == ViewModes.GRIDDED_ICONS) {
                            state = ViewModes.GRIDDED_ICONS
                        }
                    }
                    when (state) {
                        ViewModes.LIST -> ListPokemon(pokemons)
                        ViewModes.GRIDDED_INFO -> GridPokemon(pokemons)
                        ViewModes.GRIDDED_ICONS -> GridIconPokemon(pokemons)
                    }
                }
            }
        }
    }

    @Composable
    fun ToggleStyleButton(
        @DrawableRes resDrawable: Int,
        modifier: Modifier = Modifier,
        isSelected: Boolean = false,
        onClick: () -> Unit
    ) {
        IconButton(
            onClick = onClick,
            modifier = modifier
                .background(
                    color = Color.White,
                    shape = CircleShape
                )
                .border(
                    width = if (isSelected) 3.dp else 1.dp,
                    color = if (isSelected) LightGreen1 else Color.Black,
                    shape = CircleShape
                ),
        ) {
            Icon(
                painter = painterResource(id = resDrawable),
                contentDescription = null,
            )
        }
    }

    @Composable
    fun ListPokemon(pokemons: List<MiniProjectsPokemon>) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.alpha(0.95f)
        ) {
            items(pokemons.size) {
                ListImageCard(
                    painter = painterResource(pokemons[it].drawableRes),
                    title = getString(pokemons[it].title),
                    contentDescription = getString(pokemons[it].description)
                )
            }
        }
    }

    @Composable
    fun GridPokemon(pokemons: List<MiniProjectsPokemon>) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp),
        ) {
            items(pokemons.size) {
                GridImageCard(
                    painter = painterResource(pokemons[it].drawableRes),
                    title = getString(pokemons[it].title),
                    contentDescription = getString(pokemons[it].description),
                )
            }
        }
    }

    @Composable
    fun GridIconPokemon(pokemons: List<MiniProjectsPokemon>) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp),
        ) {
            items(pokemons.size) {
                GridImageIconCard(
                    painter = painterResource(pokemons[it].drawableRes)
                )
            }
        }
    }

    @Composable
    fun ListImageCard(
        painter: Painter,
        contentDescription: String,
        title: String,
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = 6.dp
        ) {
            Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(84.dp)
                        .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
                )

                Column(
                    modifier = Modifier.fillMaxHeight(),
                ) {
                    val fontFamily = FontBaloo.get()
                    Text(
                        title, style = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontFamily
                        )
                    )
                    Text(
                        contentDescription, style = TextStyle(
                            color = Color.Black,
                            fontSize = 12.sp,
                            fontFamily = fontFamily
                        )
                    )
                }
            }
        }
    }

    @Composable
    fun GridImageCard(
        painter: Painter,
        contentDescription: String,
        title: String,
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            elevation = 6.dp
        ) {
            Box(modifier = Modifier.height(200.dp)) {
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
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

    @Composable
    fun GridImageIconCard(painter: Painter) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            elevation = 6.dp
        ) {
            Box(modifier = Modifier.height(200.dp)) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

    companion object {
        fun startThisActivity(activity: Activity) {
            val intent = Intent(activity, MiniProjectsJetpackComposeActivity::class.java)
            activity.startActivity(intent)
        }

        private enum class ViewModes {
            LIST, GRIDDED_INFO, GRIDDED_ICONS
        }
    }
}