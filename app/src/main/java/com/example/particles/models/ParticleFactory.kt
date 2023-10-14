package com.example.particles.models

import android.graphics.Color

object ParticleFactory {

    fun createSandParticle(x: Float, y: Float): ElementParticle {
        return ElementParticle(
            x = x,
            y = y,
            elementName = "Sand",
            color = Color.rgb(194, 178, 128), // Example sand color
            density = 2.65f,
            elasticity = 0.45f,
            friction = 0.6f,
            thermalConductivity = 0.25f,
            specificHeatCapacity = 830f,
            meltingPoint = 1710f,
            boilingPoint = 2230f,
            hardness = 7f,
            toughness = 1.0f,
            malleability = false,
            isFlammable = false,
            isCorrosive = false,
            isRadioactive = false,
            isInsulator = true,
            opacity = 1f,
            particleSizeRange = "0.0625 mm to 2 mm"
        )
    }

    // In the future, you can add more methods here for different types of particles, like:
    // fun createWaterParticle(x: Float, y: Float): ElementParticle { ... }
}