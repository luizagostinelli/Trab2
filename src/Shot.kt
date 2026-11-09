import pt.isel.canvas.*

data class Shot(val x: Int, val y: Int, val speed: Int)

fun tryAlienShot(): Boolean = (1..100).random() <= ALIEN_SHOT_PROBABILITY

