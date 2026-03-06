package org.cmp.project.ui.screens.gate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.cmp.project.ui.screens.onBoardings.OnboardingHostScreenContent
import org.cmp.project.ui.theme.LocalCustomColors

class GateScreenContent : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val colors = LocalCustomColors.current

        LaunchedEffect(Unit) {
//            if (isOnboardingCompleted()) {
//                navigator.replaceAll(HomeScreenContent())
//            } else {
                navigator.replaceAll(OnboardingHostScreenContent())
//            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.whiteColor),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = colors.skyBlueColor)
        }
    }
}
