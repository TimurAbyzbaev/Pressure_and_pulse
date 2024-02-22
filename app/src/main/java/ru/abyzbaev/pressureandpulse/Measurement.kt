package ru.abyzbaev.pressureandpulse

data class Measurement(
    val upperPressure: Int = 0,
    val lowerPressure: Int = 0,
    val pulse: Int = 0,
    val timestamp: Long = 0
) {
    constructor() : this(0, 0, 0, 0)
}