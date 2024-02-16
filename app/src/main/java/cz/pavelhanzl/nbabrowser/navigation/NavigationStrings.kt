package cz.pavelhanzl.nbabrowser.navigation

/**
 * Enum defining the routes for navigation within the application.
 *
 * Each constant in this enum represents a different screen in the app,
 * with its corresponding navigation route string.
 */
enum class NavigationStrings(val route: String) {
    PLAYERDETAIL("playerdetail"),
    PLAYERSEARCH("playersearch"),
    TEAMDETAIL("teamdetail");

    /**
     * Converts the enum constant to its corresponding route string.
     *
     * @return The navigation route string for the enum constant.
     */
    override fun toString(): String {
        return route
    }

}