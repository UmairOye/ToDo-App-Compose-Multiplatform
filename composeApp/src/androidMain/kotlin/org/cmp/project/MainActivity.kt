package org.cmp.project

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        AndroidContext.init(this@MainActivity)


        setContent {
            val view = LocalView.current
            if (!view.isInEditMode) {
                SideEffect {
                    val window = (view.context as Activity).window
                    WindowCompat.setDecorFitsSystemWindows(window, false)
                    val controller = WindowCompat.getInsetsController(window, view)

                    controller.hide(WindowInsetsCompat.Type.navigationBars())
                    controller.systemBarsBehavior =
                        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                    controller.isAppearanceLightStatusBars = true

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        window.isNavigationBarContrastEnforced = false
                    }

                    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                        WindowCompat.setDecorFitsSystemWindows(window, false)

                        val controller = WindowInsetsControllerCompat(window, window.decorView)
                        controller.hide(WindowInsetsCompat.Type.navigationBars())
                        controller.show(WindowInsetsCompat.Type.statusBars())
                        controller.systemBarsBehavior =
                            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                    }

                }

                App()
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}