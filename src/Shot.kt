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
    val randomX = (0 until areaWidth - SHOT_WIDTH).random()
    val randomSpeed = ALIEN_SHOT_SPEED.random()
    return Shot(x = randomX, y = 0, speed = randomSpeed)
}

/** Move o tiro na vertical de acordo com a sua velocidade. */
fun Shot.move(): Shot = copy(y = y + speed)

/** Verifica se o tiro saiu da área de jogo. */
fun Shot.isOutOfBounds(areaHeight: Int): Boolean =
    y + height < 0 || y > areaHeight