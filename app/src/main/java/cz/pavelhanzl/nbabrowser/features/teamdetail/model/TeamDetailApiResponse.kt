package cz.pavelhanzl.nbabrowser.features.teamdetail.model

/**
 * Data class representing the response from the API for a team detail request.
 *
 * This class encapsulates the team detail data returned from the API.
 *
 * @property data The [Team] object containing detailed information about a specific team.
 */
data class TeamDetailApiResponse(
    val data: Team
)