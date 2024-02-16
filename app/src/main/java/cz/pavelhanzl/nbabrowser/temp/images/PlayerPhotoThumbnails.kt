package cz.pavelhanzl.nbabrowser.temp.images

/**
 * Enum representing the URLs of player photo thumbnails.
 *
 * Each constant in this enum corresponds to a specific player and contains the URL of their thumbnail photo.
 * This enum is used for displaying player thumbnails in various parts of the application and is used as a temporary solution for player images in the app.
 */
enum class PlayerPhotoThumbnails(val url: String) {
    // Each player defined with their thumbnail photo URL...
    PLAYER_1("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/203506.png"),
    PLAYER_2("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1629630.png"),
    PLAYER_3("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/203500.png"),
    PLAYER_4("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1629620.png"),
    PLAYER_5("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1629660.png"),
    PLAYER_6("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1629622.png"),
    PLAYER_7("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1629623.png"),
    PLAYER_8("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1629624.png"),
    PLAYER_9("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1629625.png"),
    PLAYER_10("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1629604.png"),
    PLAYER_11("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1629611.png"),
    PLAYER_12("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1629628.png"),
    PLAYER_13("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1629629.png"),

    /**
     * A placeholder for players whose thumbnail photo is not found.
     */
    PLAYER_NOT_FOUND("https://urlthatdoesntexist.czczcz/player.jpg")
}