package com.cookandroid.everytime.core.designsystem.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme
import com.cookandroid.everytime.core.util.noRippleClickable

data class LectureCardUiModel(
    val courseName: String,
    val professor: String,
    val courseTimeAndRoom: String,
    val categoryName: String,
    val creditLabel: String,
    val courseCode: String,
    val savedCountLabel: String,
    val capacityLabel: String,
    val recommendedGradeLabel: String,
    val target: String,
    val score: Float,
    val dotState: EverytimeDotState
)

@Composable
fun EverytimeLectureCard(
    lecture: LectureCardUiModel,
    onReviewClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    LectureCardContent(
        lecture = lecture,
        expanded = expanded,
        onCardClick = { expanded = !expanded },
        onReviewClick = onReviewClick,
        modifier = modifier,
    )
}

@Composable
private fun LectureCardContent(
    lecture: LectureCardUiModel,
    expanded: Boolean,
    onCardClick: () -> Unit,
    onReviewClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val backgroundColor =
        if (expanded) EverytimeTheme.colors.primary100 else EverytimeTheme.colors.white
    val detailColor =
        if (expanded) EverytimeTheme.colors.neutral900 else EverytimeTheme.colors.neutral700
    val scoreCountPadding = 8.dp

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = backgroundColor)
            .noRippleClickable(onClick = onCardClick)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = lecture.courseName,
                color = EverytimeTheme.colors.neutral900,
                style = EverytimeTheme.typography.body2,
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = lecture.professor,
                color = EverytimeTheme.colors.neutral900,
                style = EverytimeTheme.typography.body4,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = lecture.courseTimeAndRoom,
                color = detailColor,
                style = EverytimeTheme.typography.caption1,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(
                    lecture.recommendedGradeLabel,
                    lecture.categoryName,
                    lecture.creditLabel,
                    lecture.courseCode
                ).forEach { label ->
                    Text(
                        text = label,
                        color = detailColor,
                        style = EverytimeTheme.typography.caption1
                    )
                }
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = lecture.target,
                    color = EverytimeTheme.colors.primary900,
                    style = EverytimeTheme.typography.caption3
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    //SecondaryButton Merge후 반영 예정
                    Box(modifier = Modifier.size(15.dp).background(color = EverytimeTheme.colors.primary900))

                    Box(modifier = Modifier.size(15.dp).background(color = EverytimeTheme.colors.primary900))
                }
            }
        }

        Column(
            modifier = Modifier.padding(top = 4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.End,
        ) {
            EverytimeScore(
                score = lecture.score,
                type = EverytimeScoreType.Small,
                modifier = Modifier.padding(end = scoreCountPadding)
            )

            Column(
                modifier = Modifier.padding(end = scoreCountPadding),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                listOf(
                    "담은" to lecture.savedCountLabel,
                    "정원" to lecture.capacityLabel,
                ).forEach { (label, value) ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = label,
                            color = EverytimeTheme.colors.neutral400,
                            style = EverytimeTheme.typography.caption5,
                        )
                        Text(
                            text = value,
                            color = EverytimeTheme.colors.neutral400,
                            style = EverytimeTheme.typography.caption5,
                        )
                    }
                }
            }

            EverytimeDot(
                state = lecture.dotState,
                modifier = Modifier.padding(all = 10.dp)
            )
        }
    }
}

@Preview(name = "강의 카드 · 기본", showBackground = true, widthDp = 343)
@Composable
private fun LectureCardCollapsedPreview() {
    LectureCardPreview(expanded = false, dotState = EverytimeDotState.GREEN)
}

@Preview(name = "강의 카드 · 펼침", showBackground = true, widthDp = 343)
@Composable
private fun LectureCardExpandedPreview() {
    LectureCardPreview(expanded = true, dotState = EverytimeDotState.ORANGE)
}

@Composable
private fun LectureCardPreview(expanded: Boolean, dotState: EverytimeDotState) {
    EverytimeTheme {
        LectureCardContent(
            lecture = LectureCardUiModel(
                courseName = "강의명",
                professor = "교수님 성함",
                courseTimeAndRoom = "요일분반 / 강의실",
                recommendedGradeLabel = "N학년",
                categoryName = "강의분류",
                creditLabel = "N학점",
                courseCode = "학수번호",
                score = 5f,
                savedCountLabel = "N",
                capacityLabel = "N",
                target = "수강대상 : [대상] 국어국문학과 전공 1학년",
                dotState = dotState
            ),
            expanded = expanded,
            onCardClick = {},
            onReviewClick = {},
        )
    }
}
