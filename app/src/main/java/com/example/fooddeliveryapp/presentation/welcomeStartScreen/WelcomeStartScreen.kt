package com.example.fooddeliveryapp.presentation.welcomeStartScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.fooddeliveryapp.R
import kotlinx.coroutines.launch

@Composable
fun WelcomeStartScreen(
    onFinishClick: () -> Unit
){
    val pagerState = rememberPagerState(pageCount = {3})
    val coroutineScope = rememberCoroutineScope()
    val compesition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.food_deliver)
    )
    val compesition2 by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.food2)
    )
    val composition3 by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.food)
    )

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth()
    ) { page ->
        val(anim,title,description) = when(page){
            0 -> Triple(
                compesition,
                "All your favorites",
                "Get all your foods in once place,\n" +
                        "you just place the orer we do the rest"
            )
            1 -> Triple(
                compesition2,
                "Order from chosen chef",
                "Enjoy delicious meals prepared by top professional chefs right to your table."
            )
            else -> Triple(
                composition3,
                "Free delivery offers",
                "Get fast, reliable, and free delivery on your favorite meals every single day."
            )
        }
        WelcomeStartScreenItem(
            anim,
            title = title,
            description = description,
            currentPage = page,
            onNextClick = {
                if (page == 2){
                    onFinishClick()
                }else{
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(page + 1)
                    }
                }
            },
            onSkipClick = {
                onFinishClick()
            },

        )
        }

}