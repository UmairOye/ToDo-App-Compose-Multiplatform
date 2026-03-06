package org.cmp.project.ui.screens.onBoardings

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch
import org.cmp.project.onboarding.setOnboardingCompleted
import org.cmp.project.ui.screens.home.HomeScreenContent
import org.cmp.project.ui.theme.LocalCustomColors
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

private const val PAGE_COUNT = 3

class OnboardingHostScreenContent : Screen {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val colors = LocalCustomColors.current
        val pagerState = rememberPagerState(pageCount = { PAGE_COUNT })
        val scope = rememberCoroutineScope()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.skyBlueColor.copy(alpha = 0.08f))
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(colors.whiteColor, RoundedCornerShape(24.dp))
                        .padding(vertical = 28.dp, horizontal = 20.dp)
                ) {
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.fillMaxSize(),
                        userScrollEnabled = true
                    ) { page ->
                        when (page) {
                            0 -> OnboardingPage1(colors.skyBlueColor, colors.blackColor)
                            1 -> OnboardingPage2(colors.skyBlueColor, colors.blackColor)
                            2 -> OnboardingPage3(colors.skyBlueColor, colors.blackColor)
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(PAGE_COUNT) { index ->
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .size(8.dp)
                                .background(
                                    color = if (pagerState.currentPage == index) colors.skyBlueColor
                                    else colors.skyBlueColor.copy(alpha = 0.3f),
                                    shape = CircleShape
                                )
                        )
                    }
                }

                Button(
                    onClick = {
                        if (pagerState.currentPage < PAGE_COUNT - 1) {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        } else {
                            setOnboardingCompleted()
                            navigator.replaceAll(HomeScreenContent())
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colors.skyBlueColor),
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    Text(
                        text = if (pagerState.currentPage == PAGE_COUNT - 1) "GET STARTED" else "NEXT",
                        style = MaterialTheme.typography.titleMedium,
                        color = colors.whiteColor
                    )
                }
            }
        }
    }
}








