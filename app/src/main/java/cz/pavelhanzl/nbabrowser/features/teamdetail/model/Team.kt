package cz.pavelhanzl.nbabrowser.features.teamdetail.model

/**
 * Data class representing a team in the NBA.
 *
 * This class holds information about an NBA team, including its identity and organizational details.
 *
 * @property id The unique identifier of the team.
 * @property conference The conference in which the team competes (e.g., Eastern, Western).
 * @property division The division of the conference the team is in.
 * @property city The city where the team is based.
 * @property name The short name of the team (e.g., Lakers, Celtics).
 * @property full_name The full name of the team (e.g., Los Angeles Lakers).
 * @property abbreviation The official abbreviation of the team's name (e.g., LAL for Los Angeles Lakers).
 */
data class Team(
    val id: Int,
    val conference: String,
    val division: String,
    val city: String,
    val name: String,
    val full_name: String,
    val abbreviation: String
)