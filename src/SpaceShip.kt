// “Nave espacial do jogador: o seu estado, o movimento e o disparo”

import pt.isel.canvas.*

data class SpaceShip(val x: Int, SHIP_BASE_Y, val shot: List<Shot>)

fun SpaceShip() {
    val ship_base = Canvas.drawRect(x: Int, SHIP_BASE_Y,SHIP_WIDTH, SHIP_HEIGHT, GREEN)
    val ship_cannon = Canvas.drawRect(x: Int, SHIP_CANNON_Y, CANNON_WIDTH, CANNON_HEIGHT, YELLOW)
}