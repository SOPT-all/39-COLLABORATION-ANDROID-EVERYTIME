package com.cookandroid.everytime.presentation.lecturelist.component.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import android.app.AlertDialog as PlatformAlertDialog
import com.cookandroid.everytime.presentation.lecturelist.LectureCategory

@Composable
fun EverytimeSystemCategoryDialog(
    selectedCategory: LectureCategory?,
    onCategorySelected: (LectureCategory?) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val onSelect by rememberUpdatedState(onCategorySelected)
    val onClose by rememberUpdatedState(onDismiss)

    DisposableEffect(context, selectedCategory) {
        val categories = listOf<LectureCategory?>(null) + LectureCategory.entries
        val labels = categories
            .map { it?.label ?: "없음" }
            .toTypedArray()

        val dialog = PlatformAlertDialog.Builder(context)
            .setTitle("전공/영역")
            .setSingleChoiceItems(
                labels,
                categories.indexOf(selectedCategory),
            ) { dialog, index ->
                dialog.dismiss()
                onSelect(categories[index])
            }
            .setNegativeButton("취소") { _, _ ->
                onClose()
            }
            .setOnCancelListener {
                onClose()
            }
            .create()

        dialog.show()

        onDispose {
            dialog.dismiss()
        }
    }
}
