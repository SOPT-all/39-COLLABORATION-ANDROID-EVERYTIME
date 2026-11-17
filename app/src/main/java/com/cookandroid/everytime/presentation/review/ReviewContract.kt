package com.cookandroid.everytime.presentation.review

import com.cookandroid.everytime.presentation.review.component.model.ReviewItemUiModel
import com.cookandroid.everytime.presentation.review.component.model.ReviewKeywordUiModel

data class ReviewUiState(
    val lectureName: String = "AI와창의적문제해결 강의실",
    val averageScore: Float = 3.67f,
    val selectedKeyword: String? = null,
    val reviews: List<ReviewItemUiModel> = listOf(
        ReviewItemUiModel(
            id = 1L,
            score = 5,
            semesterLabel = "26년 2학기 수강자",
            content = "교수님께서 좋으셔서 듣기 좋았어요!\n" +
                "물론 풀강을.. 하시지만 안하실때도 있기도 하고 너무 친절하게 알려주셔서 만족했습니다!",
        ),
        ReviewItemUiModel(
            id = 2L,
            score = 5,
            semesterLabel = "26년 2학기 수강자",
            content = "매주 과제있긴한데 거의 수업시간에 다하는거고 부담 없어요 뼈문과인데 재미있게 들었습니다",
        ),
        ReviewItemUiModel(
            id = 3L,
            score = 3,
            semesterLabel = "26년 2학기 수강자",
            content = "매주 과제 있기는 한데 수업시간에 실습했던거로 나오는 경우도 있어서 엄청 귀찮지는 않음 " +
                "풀강하셔서 빡세기는 함 ⋯ 그리고 출석 직접 호명하십니당",
        ),
        ReviewItemUiModel(
            id = 4L,
            score = 3,
            semesterLabel = "26년 2학기 수강자",
            content = "매주 과제 있지만 할만한 수준이라 어렵진 않아요 그리고 과제 제출 기간도 넉넉한 편이에요\n" +
                "하지만 풀강 때리셔서 정말 힘들어요 ⋯\n" +
                "피피티에 있는 거의 모든 내용을 그냥 읽으시는 수준이라 정말 힘들어요",
        ),
        ReviewItemUiModel(
            id = 5L,
            score = 4,
            semesterLabel = "26년 2학기 수강자",
            content = "매주 있는 과제가 조금 귀찮지만, 교수님도 좋으시고 ai가 코딩해줘서 편했어요",
        ),
    ),
) {
    val keywords: List<ReviewKeywordUiModel>
        get() = listOf("교수님", "과제", "시험", "팀플").map { keyword ->
            ReviewKeywordUiModel(
                keyword = keyword,
                count = reviews.count { keyword in it.content },
            )
        }

    val visibleReviews: List<ReviewItemUiModel>
        get() = selectedKeyword?.let { keyword ->
            reviews.filter { keyword in it.content }
        } ?: reviews
}
