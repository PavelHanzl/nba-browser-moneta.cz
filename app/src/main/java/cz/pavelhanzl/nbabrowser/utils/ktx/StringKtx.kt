package cz.pavelhanzl.nbabrowser.utils.ktx

/**
 * This KTX string function converts a string representing a height in feet and inches format (e.g., "6-1") to a formatted string.
 *
 * This extension function takes a nullable String, checks if it is in the expected format (feet-inches),
 * and converts it to a more readable format with feet and inches symbols (e.g., 6' 1"). If the input
 * does not match the expected format, the original string is returned.
 *
 * @return A formatted string representing height in feet and inches, or the original string if the format is incorrect.
 */
fun String?.toFeetAndInches(): String? {
    val regex = Regex("^\\d+-\\d+\$")

    if (this == null) {
        return this
    }

    // Check if the input string matches the expected format
    if (!this.matches(regex)) {
        // The input string is not in the expected data format, return the original string
        return this
    }

    val parts = this.split("-")
    if (parts.size == 2) {
        return "${parts[0]}' ${parts[1]}\""
    }
    return this //should not be reached

}