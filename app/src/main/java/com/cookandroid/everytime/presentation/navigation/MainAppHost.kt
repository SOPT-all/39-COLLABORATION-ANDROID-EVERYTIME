package com.cookandroid.everytime.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cookandroid.everytime.presentation.lecturelist.LecturelistRoute
import com.cookandroid.everytime.presentation.review.ReviewRoute
import com.cookandroid.everytime.presentation.timetable.TimetableRoute
import kotlinx.serialization.Serializable

@Serializable
private data object TimetableDestination

@Serializable
private data object LectureListDestination

@Serializable
private data object LectureDetailDestination

@Composable
internal fun MainAppHost(
    appState: MainAppState,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = appState.navController,
        startDestination = TimetableDestination,
        modifier = modifier
    ) {
        composable<TimetableDestination> {
            TimetableRoute(
                onNavigateToLectureList = {
                    appState.navController.navigate(LectureListDestination)
                }
            )
        }

        composable<LectureListDestination> {
            LecturelistRoute(
                onLectureClick = {
                    appState.navController.navigate(LectureDetailDestination)
                },
            )
        }

        composable<LectureDetailDestination> {
            ReviewRoute()
        }
    }
}