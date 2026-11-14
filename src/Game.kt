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
    copy(ship = moveShip(ship, mouseX))

/** A nave dispara. */
fun Game.fire(): Game =
    copy(ship = fire(ship))


/** Limpa o canvas e desenha a nave e os tiros dos aliens. */
fun Canvas.drawGame(game: Game) {
    erase()
    drawShip(this, game.ship)
    for (shot in game.alienShots) {
        drawShot(this, shot, RED)
    }
}

/** Avança o jogo um passo: move os tiros e remove os que saíram da área. */
/** Avança o jogo um passo: move tiros, trata colisões e deteta o fim do jogo. */
fun Game.next(): Game {
    if (over) return this
    val movedAlienShots = moveShots(alienShots, area.height)
    val movedShipShots = moveShots(ship.shots, area.height)
    val shipHit = movedAlienShots.any { shot -> shot.hitsShip(ship) }
    return copy(
        alienShots = removeHit(movedAlienShots, movedShipShots),
        ship = ship.copy(shots = removeHit(movedShipShots, movedAlienShots)),
        over = shipHit
    )
}

/** Tenta criar um tiro dos aliens, com a probabilidade definida. */
fun Game.tryAlienShot(): Game {
    if (over) return this
    if ((1..100).random() > ALIEN_SHOT_PROBABILITY) return this
    return copy(alienShots = alienShots + createAlienShot(area.width))
}
