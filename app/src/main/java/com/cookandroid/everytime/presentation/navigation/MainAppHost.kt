package com.cookandroid.everytime.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cookandroid.everytime.core.navigation.LectureList
import com.cookandroid.everytime.core.navigation.Review
import com.cookandroid.everytime.core.navigation.Timetable
import com.cookandroid.everytime.presentation.lecturelist.LecturelistRoute
import com.cookandroid.everytime.presentation.review.ReviewRoute
import com.cookandroid.everytime.presentation.timetable.TimetableRoute

@Composable
fun MainAppHost(
    appState: MainAppState,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = appState.navController,
        startDestination = Timetable,
        modifier = modifier
    ) {
        composable<Timetable> {
            TimetableRoute(
                onNavigateToLectureList = {
                    appState.navController.navigate(LectureList)
                }
            )
        }

        composable<LectureList> {
            LecturelistRoute(
                onLectureClick = {
                    appState.navController.navigate(Review)
                },
            )
        }

        composable<Review> {
            ReviewRoute()
        }
    }
}