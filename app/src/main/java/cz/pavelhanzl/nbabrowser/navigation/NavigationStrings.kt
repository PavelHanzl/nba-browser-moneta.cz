package cz.pavelhanzl.nbabrowser.navigation

enum class NavigationStrings(val route:String) {
    PLAYERDETAIL("playerdetail"),
    PLAYERSEARCH("playersearch"),
    TEAMDETAIL("teamdetail");

    override fun toString(): String {
        return route
    }

}