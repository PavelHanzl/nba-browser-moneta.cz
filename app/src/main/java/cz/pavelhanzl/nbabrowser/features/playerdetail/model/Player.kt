package cz.pavelhanzl.nbabrowser.features.playerdetail.model

import cz.pavelhanzl.nbabrowser.features.teamdetail.model.Team

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