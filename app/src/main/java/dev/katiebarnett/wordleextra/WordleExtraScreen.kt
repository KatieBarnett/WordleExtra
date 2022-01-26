package dev.katiebarnett.wordleextra

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.ui.graphics.vector.ImageVector

enum class WordleExtraScreen(
    val icon: ImageVector
) {
    StartGame(
        icon = Icons.Filled.PieChart
    ),
    Game(
        icon = Icons.Filled.AttachMoney
    ),
    Share(
        icon = Icons.Filled.MoneyOff
    );

    companion object {
        fun fromRoute(route: String?): WordleExtraScreen =
            when (route?.substringBefore("/")) {
                StartGame.name -> StartGame
                Game.name -> Game
                Share.name -> Share
                null -> StartGame
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}