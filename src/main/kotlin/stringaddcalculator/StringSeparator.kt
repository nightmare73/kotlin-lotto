package stringaddcalculator

object StringSeparator {
    private const val BASIC_DELIMITER_COMMA = ","
    private const val BASIC_DELIMITER_COLON = ":"
    private val CUSTOM_DELIMITER_REGEX = Regex("//(.)\n(.*)")
    private val BASIC_STRING_REGEX = Regex("\\d(.\\d)*")

    fun separate(inputString: String?): List<String> {
        if (inputString.isNullOrBlank()) return emptyList()
        if (inputString.hasCustomDelimiter()) return separateWithCustomDelimiter(inputString)
        validateInputString(inputString)
        return inputString.split(BASIC_DELIMITER_COMMA, BASIC_DELIMITER_COLON)
    }

    private fun String.hasCustomDelimiter(): Boolean {
        return CUSTOM_DELIMITER_REGEX.matches(this)
    }

    private fun separateWithCustomDelimiter(inputString: String): List<String> {
        val (customDelimiter, stringToSeparate) = findMatchResultsOf(inputString)
        validateInputString(stringToSeparate)
        return stringToSeparate.split(BASIC_DELIMITER_COMMA, BASIC_DELIMITER_COLON, customDelimiter)
    }

    private fun findMatchResultsOf(inputString: String): MatchResult.Destructured {
        return CUSTOM_DELIMITER_REGEX.find(inputString)?.destructured
            ?: throw IllegalArgumentException("$inputString 는 유효하지 않은 문자열입니다.")
    }

    private fun validateInputString(inputString: String) {
        if (!BASIC_STRING_REGEX.matches(inputString)) {
            throw IllegalArgumentException("$inputString 는 유효하지 않은 문자열입니다.")
        }
    }
}