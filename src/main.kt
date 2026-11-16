





//Ponto de entrada do jogo, que liga o canvas, os eventos e os temporizadores ao estado do jogo.

import pt.isel.canvas.*

private const val AREA_HEIGHT = 500

private const val FIRE_KEY = ' '

// Temporização
const val MS_PER_SEC = 1000
const val FRAMES_PER_SEC = 70
const val FRAME_PERIOD = MS_PER_SEC / FRAMES_PER_SEC   // ~14 ms
const val ALIEN_SHOT_PERIOD = 250

// Representa as dimensões da área jogável do jogo.
data class Area(val width: Int = AREA_WIDTH, val height: Int = AREA_HEIGHT)

fun main() {
    onStart {
        val arena = Canvas(AREA_WIDTH, AREA_HEIGHT, BLACK)
        var game = newGame(Area())

        arena.onMouseMove { me ->
            game = game.moveShip(me.x)
        }

        arena.onMouseDown {
            game = game.fire()
        }

        arena.onKeyPressed { k ->
            if (k.char == FIRE_KEY) {
                game = game.fire()
            }
        }

        arena.onTimeProgress(FRAME_PERIOD) {
            game = game.next()
            arena.drawGame(game)
            if (game.over) {
                arena.close()
            }
        }

        arena.onTimeProgress(ALIEN_SHOT_PERIOD) {
            game = game.tryAlienShot()
        }
    }
    onFinish {
        println("Game Over  .")
    }
}
