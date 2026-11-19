package com.cookandroid.everytime.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.cookandroid.everytime.core.navigation.LectureList
import com.cookandroid.everytime.core.navigation.Review
import com.cookandroid.everytime.core.navigation.Timetable
import com.cookandroid.everytime.presentation.lecturelist.LecturelistRoute
import com.cookandroid.everytime.presentation.review.ReviewRoute
import com.cookandroid.everytime.presentation.timetable.TimetableRoute
import java.util.Map.entry

@Composable
fun MainAppHost(
    appState: MainAppState,
    modifier: Modifier = Modifier
) {
    var selectedTimeLabel by rememberSaveable { mutableStateOf<String?>(null) }

    NavHost(
        navController = appState.navController,
        startDestination = LectureList,
        modifier = modifier
    ) {
        composable<LectureList> {
            LecturelistRoute(
                onNavigateToReview = { appState.navController.navigate(Review(courseId = it)) },
                onNavigateToTimetable = { appState.navController.navigate(Timetable) },
                timeValue = selectedTimeLabel,
                onTimeClearClick = { selectedTimeLabel = null }
            )
        }

        composable<Timetable> {
            TimetableRoute(
                onNavigateBack = { appState.navController.popBackStack() },
                onApply = { label ->
                    selectedTimeLabel = label
                    appState.navController.popBackStack()
                }
            )
        }

        composable<Review> { entry ->
            ReviewRoute(
                courseId = entry.toRoute<Review>().courseId,
                onNavigateBack = { appState.navController.popBackStack() }
            )
        }
    }
}
