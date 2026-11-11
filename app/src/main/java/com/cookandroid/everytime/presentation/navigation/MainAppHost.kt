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
        startDestination = LectureList,
        modifier = modifier
    ) {
        composable<LectureList> {
            LecturelistRoute(
                onLectureClick = {
                    appState.navController.navigate(Review)
                },
                onNavigateToTimetable = {
                    appState.navController.navigate(Timetable)
                }
            )
        }

        composable<Timetable> {
            TimetableRoute(
                onBackClick = {
                    appState.navController.popBackStack()
                }
            )
        }

        composable<Review> {
            ReviewRoute(
                onBackClick = {
                    appState.navController.popBackStack()
                }
            )
        }
    }
}