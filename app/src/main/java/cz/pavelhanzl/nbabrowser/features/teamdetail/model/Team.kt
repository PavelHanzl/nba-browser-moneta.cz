package cz.pavelhanzl.nbabrowser.features.teamdetail.model

data class Team (
    val id: Int,
    val conference: String,
    val division: String,
    val city: String,
    val name: String,
    val full_name: String,
    val abbreviation: String
)