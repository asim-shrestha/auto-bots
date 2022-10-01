fun main(args: Array<String>) {
    println("Hello World!")
}

class GamePlayer {
    companion object {
        fun playGame(teamOne: PlayerTeam, teamTwo: PlayerTeam): GameState {
            println(teamOne)
            println(teamTwo)
            println()
            if(isSomeoneDead(teamOne, teamTwo)) {
                return getGameResult(teamOne, teamTwo)
            }

            val (nextTeamOne, nextTeamTwo) = playTurnAndGetTeams(teamOne, teamTwo)
            return playGame(nextTeamOne, nextTeamTwo)
        }

        private fun playTurnAndGetTeams(teamOne: PlayerTeam, teamTwo: PlayerTeam): Pair<PlayerTeam, PlayerTeam> {
            require(!isSomeoneDead(teamOne, teamTwo))
            val newTeamOne = teamOne.takeFrontDamage(teamTwo.getFrontDamage())
            val newTeamTwo = teamTwo.takeFrontDamage(teamOne.getFrontDamage())
            return newTeamOne to newTeamTwo
        }

        private fun isSomeoneDead(teamOne: PlayerTeam, teamTwo: PlayerTeam) = teamOne.team.isEmpty() || teamTwo.team.isEmpty()

        private fun getGameResult(teamOne: PlayerTeam, teamTwo: PlayerTeam): GameState {
            if(teamOne.team.isEmpty() && teamTwo.team.isEmpty()) { return GameState.DRAW }
            if(teamOne.team.isEmpty()) { return GameState.PLAYER_TWO }
            return GameState.PLAYER_ONE
        }
    }
}

@JvmInline
value class PlayerTeam(val team: List<Bot>) {
    fun takeFrontDamage(damage: Int): PlayerTeam {
        val newFrontBot = team[0].copy(health = team[0].health - damage)
        return if (newFrontBot.health <= 0) {
            PlayerTeam(team.drop(1))
        } else {
            PlayerTeam(listOf(newFrontBot) + team.drop(1))
        }
    }

    fun getFrontDamage() = team[0].attack
}

data class Bot(val name: String, val health: Int, val attack: Int)

enum class TurnResult {

}

enum class GameState {
    PLAYING,
    DRAW,
    PLAYER_ONE,
    PLAYER_TWO,
}