package com.example.particles.models

import android.graphics.Color

data class ElementParticle(
    var x: Float,
    var y: Float,
    var velocityX: Float = 0f,
    var velocityY: Float = 0f,
    val accelerationY: Float = 0.5f,
    val lifespan: Long = 5000L,
    var size: Float = 5f, // this can be derived from particleSizeRange in future
    val elementName: String,
    val color: Int,
    val density: Float,
    val elasticity: Float,
    val friction: Float,
    val thermalConductivity: Float,
    val specificHeatCapacity: Float,
    val meltingPoint: Float,
    val boilingPoint: Float,
    val hardness: Float,
    val toughness: Float,
    val malleability: Boolean,
    val isFlammable: Boolean,
    val isCorrosive: Boolean,
    val isRadioactive: Boolean,
    val isInsulator: Boolean,
    val opacity: Float,
    val particleSizeRange: String,
    val mass: Float = 1.0f  // Assume a constant mass for all particles

)
