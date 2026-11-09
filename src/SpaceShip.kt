// “Nave espacial do jogador: o seu estado, o movimento e o disparo”

import pt.isel.canvas.*

data class SpaceShip(val x: Int, val shots: List<Shot> = emptyList())

/** Desenha a nave e o seu projétil. */
fun Canvas.drawShip(ship: SpaceShip) {
    drawRect(ship.x, SHIP_BASE_Y, SHIP_WIDTH, SHIP_HEIGHT, GREEN)
    drawRect(ship.cannonX(), SHIP_CANNON_Y, CANNON_WIDTH, CANNON_HEIGHT, YELLOW)
    ship.shots.forEach { drawShot(it, WHITE) }
}

/** Cria a nave centrada na área, sem projéteis. */
fun newShip(area: Area) = SpaceShip((area.width - SHIP_WIDTH) / 2)

