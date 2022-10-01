import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.*
import io.kotest.property.checkAll

class GamePlayerTest: DescribeSpec ({
    describe("playGame") {
        context("when both players have no bots") {
            it("results in a draw") {
                checkAll(teamOfSizeGen(0), teamOfSizeGen(0)) { teamOne, teamTwo ->
                    GamePlayer.playGame(teamOne, teamTwo) shouldEndIn GameState.DRAW
                }
            }
        }

        context("when both players have the same basic team") {
            it("results in a draw") {
                checkAll(teamOfSizeGen(1..10)) { team ->
                    GamePlayer.playGame(team, team) shouldEndIn GameState.DRAW
                }
            }
        }

        context("when only one player has bots") {
            it("results in the player with bots winning") {
                checkAll(teamOfSizeGen(1..10), teamOfSizeGen(0)) { nonEmptyTeam, emptyTeam ->
                    GamePlayer.playGame(nonEmptyTeam, emptyTeam) shouldEndIn GameState.PLAYER_ONE
                    GamePlayer.playGame(emptyTeam, nonEmptyTeam) shouldEndIn GameState.PLAYER_TWO
                }
            }
        }

        context("when both players have bots") {
            context("with single bot teams") {
                it("results in the stronger bot team winning") {
                    val strongerTeam = PlayerTeam(listOf(Bot("Strong_Bot", 50, 5)))
                    val weakerTeam = PlayerTeam(listOf(Bot("Weak_Bot", 10, 1)))

                    GamePlayer.playGame(strongerTeam, weakerTeam) shouldEndIn GameState.PLAYER_ONE
                    GamePlayer.playGame(weakerTeam, strongerTeam) shouldEndIn GameState.PLAYER_TWO
                }
            }

            context("with multiple bot teams") {
                it("results in the stronger team winning") {
                    val strongerTeam = PlayerTeam(listOf(Bot("Strong_Bot", 50, 5)))
                    val weakerTeam = PlayerTeam(listOf(Bot("Weak_Bot", 10, 1)))

                    GamePlayer.playGame(strongerTeam, weakerTeam) shouldEndIn GameState.PLAYER_ONE
                    GamePlayer.playGame(weakerTeam, strongerTeam) shouldEndIn GameState.PLAYER_TWO
                }
            }
        }
    }
})

/**
 *
 */
infix fun GameState.shouldEndIn(other: GameState) = this.shouldBe(other)

fun teamOfSizeGen(size: Int) = teamOfSizeGen(size..size)

fun teamOfSizeGen(sizeRange: IntRange) = arbitrary {
    PlayerTeam(
        Arb.list(botGen, sizeRange).bind()
    )
}

val botGen = arbitrary {
    Bot(
        name = Arb.string().bind(),
        health = Arb.int(1..50).bind(),
        attack = Arb.int(1..50).bind(),
    )
}