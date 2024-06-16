package com.sundaegukbap.banchango

enum class RecipeDifficulty(val level: Int, val explain: String) {
    ANYONE(1, "아무나"),
    BEGINNER(2, "초보"),
    INTERMEDIATE(3, "중급"),
    ADVANCED(4, "고급"),
    GODLIKE(5, "신의 경지"),
}
