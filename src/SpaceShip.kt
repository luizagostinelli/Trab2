/** Nave do jogador: estado, movimento, disparo e desenho. */

import pt.isel.canvas.*

//tamanho do canhão e local
private const val CANNON_WIDTH = 4
private const val CANNON_HEIGHT = 5
private const val SHIP_CANNON_Y = SHIP_BASE_Y - CANNON_HEIGHT

//velocidade do tiro da nave
const val SHIP_SHOT_SPEED = 4

// Movimentação da nave
private const val MID_SHIP = SHIP_WIDTH / 2 // 25
private const val MIN_SHIP_X = SHIP_WIDTH / 2
private const val MAX_SHIP_X = AREA_WIDTH - SHIP_WIDTH / 2  // 675

/** Nave com a posição X do canto esquerdo e a lista de projéteis ativos. */
data class SpaceShip(val x: Int, val shots: List<Shot>)

/** Cria a nave centrada na área, sem projéteis. */
fun newShip(area: Area): SpaceShip {
    val x = (area.width - SHIP_WIDTH) / 2
    return SpaceShip(x, emptyList())
}

/** Coordenada X do canhão, centrado sobre o retângulo verde da nave. */
private fun cannonX(ship: SpaceShip): Int {
    return ship.x + (SHIP_WIDTH - CANNON_WIDTH) / 2
}
/** Limita o X do centro da nave para ela não sair da área. */
private fun limitX(x: Int): Int {
    if (x < MIN_SHIP_X) return MIN_SHIP_X
    if (x > MAX_SHIP_X) return MAX_SHIP_X
    return x
}

/** Move a nave para ficar centrada no X do rato, com os parâmetros da fun limitX. */
fun moveShip(ship: SpaceShip, mouseX: Int): SpaceShip {
    val centerX = limitX(mouseX)
    return ship.copy(x = centerX - MID_SHIP)
}

/** Dispara a partir do canhão, mas só se não existir já um projétil da nave. */
fun fire(ship: SpaceShip): SpaceShip {
    if (ship.shots.isNotEmpty()) return ship
    val shot = Shot(x = cannonX(ship), y = SHIP_CANNON_Y - SHOT_HEIGHT, speed = -SHIP_SHOT_SPEED)
    return ship.copy(shots = listOf(shot))
}

/** Desenha a nave e o seu projétil. */
fun drawShip(canvas: Canvas, ship: SpaceShip) {
    canvas.drawRect(ship.x, SHIP_BASE_Y, SHIP_WIDTH, SHIP_HEIGHT, GREEN)
    canvas.drawRect(cannonX(ship), SHIP_CANNON_Y, CANNON_WIDTH, CANNON_HEIGHT, YELLOW)
    for (shot in ship.shots) {
        drawShot(canvas, shot, WHITE)
    }
}