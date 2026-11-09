/** Constantes partilhadas por todo o jogo. */

// Área de jogo
const val AREA_WIDTH = 700
const val AREA_HEIGHT = 500

// Nave
const val SHIP_WIDTH = 50
const val SHIP_HEIGHT = 10
const val SHIP_BASE_Y = 450

const val CANNON_WIDTH = 4
const val CANNON_HEIGHT = 5
const val SHIP_CANNON_Y = SHIP_BASE_Y - CANNON_HEIGHT

// Projéteis
const val FIRE_KEY = ' '
const val SHOT_WIDTH = 4
const val SHOT_HEIGHT = 7
const val SHIP_SHOT_SPEED = 4
val ALIEN_SHOT_SPEED = 1..4        // val, porque um intervalo não pode ser const
const val ALIEN_SHOT_PROBABILITY = 50                  // % de sucesso da tentativa de tiro dos aliens (valor entre 0 e 100)

// Temporização
const val MS_PER_SEC = 1000
const val FRAMES_PER_SEC = 70
const val FRAME_PERIOD = MS_PER_SEC / FRAMES_PER_SEC   // ~14 ms
const val ALIEN_SHOT_PERIOD = 250                       // ms