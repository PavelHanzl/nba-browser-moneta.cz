package cz.pavelhanzl.nbabrowser.features.playerdetail.model

import cz.pavelhanzl.nbabrowser.features.teamdetail.model.Team

data class Player(
    val id: Int,
    val first_name: String,
    val last_name: String,
    val position: String,
    val height_feet: Int,
    val height_inche: Int,
    val weight_pounds: Int,
    val team: Team


)