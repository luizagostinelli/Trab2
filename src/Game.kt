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

/** Verdadeiro se dois retângulos (x, y, largura, altura) se sobrepõem. */
fun collides(x1: Int, y1: Int, w1: Int, h1: Int, x2: Int, y2: Int, w2: Int, h2: Int): Boolean =
    x1 < x2 + w2 && x2 < x1 + w1 && y1 < y2 + h2 && y2 < y1 + h1

/** Verdadeiro se dois tiros se tocam. */
fun Shot.hits(other: Shot): Boolean =
    collides(x, y, width, height, other.x, other.y, other.width, other.height)

/** Verdadeiro se o tiro atinge o retângulo verde da nave. */
fun Shot.hitsShip(ship: SpaceShip): Boolean =
    collides(x, y, width, height, ship.x, SHIP_BASE_Y, SHIP_WIDTH, SHIP_HEIGHT)

/** Move uma lista de tiros e remove os que saíram da área. */
fun moveShots(shots: List<Shot>, areaHeight: Int): List<Shot> =
    shots
        .map { shot -> shot.move() }
        .filter { shot -> !shot.isOutOfBounds(areaHeight) }

/** Remove os tiros de 'shots' que acertam em algum tiro de 'others'. */
fun removeHit(shots: List<Shot>, others: List<Shot>): List<Shot> =
    shots.filter { shot -> !others.any { other -> shot.hits(other) } }