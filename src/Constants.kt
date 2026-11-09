/** Constantes numéricas partilhadas por todo o jogo. */

// Área de jogo
const val AREA_WIDTH = 700
const val AREA_HEIGHT = 500

// Nave
const val SHIP_WIDTH = 50
const val SHIP_HEIGHT = 10
const val SHIP_BASE_Y = 450
const val CANNON_WIDTH = 4
const val CANNON_HEIGHT = 5

// Projéteis
const val FIRE_KEY = ' '
const val SHOT_WIDTH = 4
const val SHOT_HEIGHT = 7
const val SHIP_SHOT_SPEED = 4
const val ALIEN_SHOT_VELOCITY = 1..4

// Temporização
const val FRAMES_PER_SEC = 70        // ~70 frames por segundo
const val FRAMES_PERIOD = 1000 / FRAMES_PER_SEC
const val ALIEN_SHOT_PROBABILITY = 0.5