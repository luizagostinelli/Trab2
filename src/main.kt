//Ponto de entrada do jogo, que liga o canvas, os eventos e os temporizadores ao estado do jogo.

import pt.isel.canvas.*

fun main() {
    onStart {
        val arena = Canvas(AREA_WIDTH, AREA_HEIGHT, BLACK)
        var game = newGame(Area())

        arena.onMouseMove { me -> game = game.moveShip(me.x) }

        arena.onMouseDown { game = game.fire() }

        arena.onKeyPressed { k -> if (k.char == FIRE_KEY) game = game.fire() }

        arena.onTimeProgress(FRAME_PERIOD) {
            game = game.next()
            arena.drawGame(game)
        }

        arena.onTimeProgress(ALIEN_SHOT_PERIOD) {
            game = game.tryAlienShot()
        }
    }
    onFinish {
        println("Finish")
    }
}

    data class Game(val gameArea: Area, val alienShot: List<Shot>, val spaceship: Spaceship, val over: Boolean)

    fun newGame(area:Area): Game = TODO()