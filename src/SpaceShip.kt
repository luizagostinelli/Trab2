
data class SpaceShip(val x: Int, val y: Int, val shot: List<Shot>)

fun nave () {
    val ship_base = Canvas.drawRect(x: Int, SHIP_BASE_Y,SHIP_WIDTH, SHIP_HEIGHT, GREEN)
    val ship_cannon = Canvas.drawRect(x: Int, SHIP_BASE_Y,SHIP_WIDTH, SHIP_HEIGHT, YELLOW)
}