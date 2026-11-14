/** Tiro (da nave ou dos aliens): posição, tamanho e velocidade vertical. */

import pt.isel.canvas.*

/** Canto superior esquerdo (x, y); speed > 0 desce, speed < 0 sobe. */
data class Shot(
    val x: Int,
    val y: Int,
    val width: Int = SHOT_WIDTH,
    val height: Int = SHOT_HEIGHT,
    val speed: Int
)

/** Desenha um tiro como um retângulo com a cor dada. */
fun drawShot(canvas: Canvas, shot: Shot, color: Int) {
    canvas.drawRect(shot.x, shot.y, shot.width, shot.height, color)
}

/** Cria um tiro de alien numa posição X aleatória, no topo da área. */
fun createAlienShot(areaWidth: Int): Shot {
    val randomX = (0 until areaWidth).random()
    val randomSpeed = ALIEN_SHOT_SPEED.random()
    return Shot(x = randomX, y = 0, speed = randomSpeed)
}

/** Move o tiro na vertical de acordo com a sua velocidade. */
fun Shot.move(): Shot = copy(y = y + speed)

/** Verifica se o tiro saiu da área de jogo. */
fun Shot.isOutOfBounds(areaHeight: Int): Boolean =
    y + height < 0 || y > areaHeight

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
    shots.map { shot -> shot.move() }.filter { shot -> !shot.isOutOfBounds(areaHeight) }

/** Remove os tiros de 'shots' que acertam em algum tiro de 'others'. */
fun removeHit(shots: List<Shot>, others: List<Shot>): List<Shot> =
    shots.filter { shot -> !others.any { other -> shot.hits(other) } }