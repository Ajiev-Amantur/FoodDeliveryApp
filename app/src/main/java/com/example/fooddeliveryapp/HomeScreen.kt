package com.example.fooddeliveryapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomeScreenUI(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)){
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(R.drawable.menu),
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )
        }
        Text(
            text = buildAnnotatedString {
                append("Hi, Aman\nLet's Make Your ")
                withStyle(style = SpanStyle(Color.Red)) {
                    append("Breakfast \n ")
                }
                append("Amazing")
            },
            fontSize = 30.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )
        var searchText by remember {
            mutableStateOf("")
        }
        var isActive by remember {
            mutableStateOf(false)
        }
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            SearchBar(
                query = searchText,
                onQueryChange = { text ->
                    searchText = text

                },
                placeholder = {
                    Text("Burger Pizza Cake..........", color = Color.Gray)
                },
                onSearch = {

                },
                active = false,
                leadingIcon = {
                    Image(
                        painter = painterResource(R.drawable.search),
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                    )
                },
                onActiveChange = { isActive = it },
                colors = SearchBarDefaults.colors(
                    containerColor = Color.Transparent,
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(20.dp)
                    .height(54.dp)
                    .border(
                        1.dp, Color(0xFFFA00FF),
                        RoundedCornerShape(30.dp)
                    )
            ) {

            }
            Spacer(modifier = Modifier.width(10.dp))


            Box(modifier = Modifier.size(54.dp)
                .background(Color.Black, RoundedCornerShape(24.dp))
                .border(width = 1.dp, color = Color(0xFFFA00FF),
                    RoundedCornerShape(24.dp)),
                contentAlignment = Alignment.Center,){

                Image(painter = painterResource(R.drawable.setting_5),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                    )
            }
        }

        Text("Categories",
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.padding(20.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))

        LazyRow(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
            item {  CategoryItem(null,"All",isSelected = true) }
            item {  CategoryItem(R.drawable.hamburger,"hamburger") }
            item {  CategoryItem(R.drawable.pizza,"pizza") }
            item {  CategoryItem(R.drawable.hamburger,"hamburger") }
            item {  CategoryItem(R.drawable.pizza,"pizza") }

        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)){
            FoodCard("hamburger","8.99",R.drawable.hamburger,"Tasty Hamburger!")
            FoodCard("Veggie Pizza", "11.99", R.drawable.pizza, "Lorem ipsum dolor...")

        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)){
            FoodCard("Veggie Pizza", "11.99", R.drawable.pizza, "Lorem ipsum dolor...")
            FoodCard("hamburger","8.99",R.drawable.hamburger,"Tasty Hamburger!")

        }
    }

    }
}
