// Representa um projétil do jogo e a sua movimentação no ecrã.

import pt.isel.canvas.*

data class ShipShot(val x: Int, val y: Int, val speed: Int)
data class AlienShot(val x: Int, val y: Int, val speed_alien: Int)

// Tiro dos aliens

fun createAlienShot(areaWidth: Int): AlienShot {
    val randomX = (0 until areaWidth).random()
    val randomSpeed = ALIEN_SHOT_SPEED.random() // Sortear o valor explicitamente aqui!
    return AlienShot(x = randomX, y = 0, speed_alien = randomSpeed)
}

// Tiro da nave
/** Move o projétil de acordo com a sua velocidade. */
fun Shot.move(): Shot = copy(pos = Position(pos.x, pos.y - SHIP_SHOT_SPEED))

/** Verifica se o projétil saiu da área de jogo. */
fun Shot.isOutOfBounds(areaHeight: Int): Boolean = pos.y + SHOT_HEIGHT < 0 || pos.y > areaHeight

/** Desenha o projétil com a cor especificada. */
fun Shot.draw(canvas: Canvas, color: Int) {
    canvas.drawRect(pos.x, pos.y, SHOT_WIDTH, SHOT_HEIGHT, RED)
}