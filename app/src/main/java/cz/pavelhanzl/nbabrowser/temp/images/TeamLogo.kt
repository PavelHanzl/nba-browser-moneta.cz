package cz.pavelhanzl.nbabrowser.temp.images

/**
 * Enum representing the URLs of NBA team logos.
 *
 * Each constant in this enum corresponds to a specific NBA team and contains the URL of its logo.
 * This enum is used for displaying team logos in various parts of the application and is used as a temporary solution for team logos in the app..
 */
enum class TeamLogo(val url: String) {
    // Each team defined with their logo URL...
    TEAM_1("https://content.sportslogos.net/logos/6/222/full/cleveland_cavaliers_logo_primary_20187997.png"),
    TEAM_2("https://content.sportslogos.net/logos/6/221/full/chicago_bulls_logo_primary_19672598.png"),
    TEAM_3("https://content.sportslogos.net/logos/6/213/full/boston_celtics_logo_primary_19977628.png"),
    TEAM_4("https://content.sportslogos.net/logos/6/235/full/3152_golden_state_warriors-primary-2020.png"),
    TEAM_5("https://content.sportslogos.net/logos/6/223/full/detroit_pistons_logo_primary_20185710.png"),

    /**
     * A placeholder for cases where the team logo is not found.
     */
    TEAM_NOT_FOUND("https://urlthatdoesntexist.czczcz/player.jpg")
}