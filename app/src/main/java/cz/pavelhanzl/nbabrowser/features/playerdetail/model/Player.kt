package cz.pavelhanzl.nbabrowser.features.playerdetail.model

import cz.pavelhanzl.nbabrowser.features.teamdetail.model.Team

/**
 * Data class representing a player in the NBA.
 *
 * This class holds information about an NBA player, including personal details and draft information.
 *
 * @property id The unique identifier of the player.
 * @property first_name The first name of the player.
 * @property last_name The last name of the player.
 * @property position The playing position of the player.
 * @property height The height of the player.
 * @property weight The weight of the player.
 * @property jersey_number The jersey number of the player.
 * @property college The college that the player attended.
 * @property country The country of origin of the player.
 * @property draft_year The year the player was drafted.
 * @property draft_round The round in which the player was drafted.
 * @property draft_number The overall number at which the player was drafted.
 * @property team The [Team] object representing the team the player is currently on.
 */
data class Player(
    val id: Int,
    val first_name: String,
    val last_name: String,
    val position: String,
    val height: String,
    val weight: String,
    val jersey_number: String,
    val college: String,
    val country: String,
    val draft_year: Int,
    val draft_round: Int,
    val draft_number: Int,
    val team: Team
)