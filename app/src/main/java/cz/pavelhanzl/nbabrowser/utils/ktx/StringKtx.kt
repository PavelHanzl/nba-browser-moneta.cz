package cz.pavelhanzl.nbabrowser.utils.ktx


fun String?.toFeetAndInches(): String? {
    val regex = Regex("^\\d+-\\d+\$")

    if (this == null){
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