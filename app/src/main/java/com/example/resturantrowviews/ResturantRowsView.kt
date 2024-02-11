package com.example.resturantrowviews

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.resturantrowviews.datalevel.restItem
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ResturantRowsView(
    navController: NavController,
    resturantList: StateFlow<List<restItem>>
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFF5E667),
                        Color(0xFFED8888)
                    ),
                    start = Offset.Zero,
                    end = Offset.Infinite,
                    tileMode = TileMode.Decal
                )
            )
    ){
        Column {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(50.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ){
                Image(painter = rememberAsyncImagePainter(model = "https://firebasestorage.googleapis.com/v0/b/eateryappself.appspot.com/o/resturant.png?alt=media&token=b74ee5ec-3520-4675-8fd8-fd2dc703133a"),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(40.dp)
                )
                Text(
                    text = "Restaurants",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = rememberAsyncImagePainter(model = "https://firebasestorage.googleapis.com/v0/b/eateryappself.appspot.com/o/qr.png?alt=media&token=0f22b49a-4a87-4a45-a571-7718671c8367"),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                )
            }
            Spacer(modifier = Modifier.height(35.dp))

            var address by remember { mutableStateOf("") }

            Row (
                modifier = Modifier.background(Color.Transparent)
            ){
                Spacer(modifier = Modifier.weight(1f))
                OutlinedTextField(
                    value = address, onValueChange = { newText -> address = newText },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .width(350.dp)
                        .height(70.dp),
                    singleLine = true,
                    label = {
                        Row {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "")
                            Spacer(modifier = Modifier.width(25.dp))
                            Text(
                                "Search Restaurant...",
                                color = Color.Gray,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(35.dp))

            val resturats by resturantList.collectAsState()
            LazyColumn{
                items(resturats.size){
                    index -> 
                    Text(text = resturats[index].name)
                }
            }
        }
    }
}