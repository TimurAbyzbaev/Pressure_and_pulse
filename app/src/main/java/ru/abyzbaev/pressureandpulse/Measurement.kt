package ru.abyzbaev.pressureandpulse

data class Measurement(
    val pressure: Int = 0,
    val pulse: Int = 0,
    val timestamp: Long = 0
) {
    constructor() : this(0, 0, 0)
}