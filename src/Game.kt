/** Estado do jogo e funções que juntam a nave, os tiros e o desenho. */

import pt.isel.canvas.*


/** Estado do jogo: área, tiros dos aliens, nave e se acabou. */
data class Game(
    val area: Area,
    val alienShots: List<Shot>,
    val ship: SpaceShip,
    val over: Boolean
)

/** Cria o jogo inicial: nave centrada, sem tiros. */
fun newGame(area: Area): Game =
    Game(area, emptyList(), newShip(area), false)

/** Move a nave para o X do rato. */
fun Game.moveShip(mouseX: Int): Game =
    copy(ship = moveShip(ship, mouseX, area))

/** A nave dispara. */
fun Game.fire(): Game =
    copy(ship = fire(ship))

/** Avança o jogo um passo (mover tiros, colisões...). Por fazer. */
fun Game.next(): Game {
    return this
}

/** Tenta um tiro dos aliens. Por fazer. */
fun Game.tryAlienShot(): Game {
    return this
}

/** Limpa o canvas e desenha a nave e os tiros dos aliens. */
fun Canvas.drawGame(game: Game) {
    erase()
    drawShip(this, game.ship)
    for (shot in game.alienShots) {
        drawShot(this, shot, RED)
    }
}