package cz.pavelhanzl.nbabrowser.features.playersearch.model

import cz.pavelhanzl.nbabrowser.features.playerdetail.model.Player

data class PlayerSearchResponse (
    val data: List<Player>?,
    val meta: Meta
)

data class Meta(
    val next_cursor: Int,
    val per_page: Int
)