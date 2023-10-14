package com.example.particles.controllers

import com.example.particles.controllers.ParticleInteraction
import com.example.particles.models.ElementParticle
import kotlin.math.abs
import kotlin.math.sqrt
import kotlin.math.tan

class SandSandInteraction : ParticleInteraction {

    // Angle of Repose for sand in degrees, converted to radians
    private val angleOfRepose = Math.toRadians(30.0).toFloat()

    override fun appliesTo(particle1: ElementParticle, particle2: ElementParticle): Boolean {
        return particle1.elementName == "Sand" && particle2.elementName == "Sand"
    }

    override fun interact(particle1: ElementParticle, particle2: ElementParticle, interactionPoint: ParticleInteraction.Vector2) {
        val dx = particle1.x - particle2.x
        val dy = particle1.y - particle2.y
        val distance = sqrt((dx * dx + dy * dy).toDouble()).toFloat()

        // Change this radiusSum according to your particle size
        val radiusSum = 2.0f // Example value; adjust as needed

        if (distance < radiusSum) {
            // Compute overlap and correct positions to prevent overlap
            val overlap = radiusSum - distance
            val correctionFactor = overlap / distance  // Removed mass since it's not defined in your com.example.particles.models.ElementParticle

            particle1.x += correctionFactor * dx * 0.5f
            particle1.y += correctionFactor * dy * 0.5f
            particle2.x -= correctionFactor * dx * 0.5f
            particle2.y -= correctionFactor * dy * 0.5f

            // Calculate the slope of the line connecting the two particles
            val slope = abs(dy / dx)

            // Determine if the slope is less than the angle of repose
            if (slope < tan(angleOfRepose)) {
                // Particles should come to a rest
                particle1.velocityX = 0f
                particle1.velocityY = 0f
                particle2.velocityX = 0f
                particle2.velocityY = 0f
            } else {
                // Particles should roll down the pile
                // Adjust this based on your needs
                val rollForce = 0.1f
                if (dy > 0) {
                    particle1.velocityX += if (dx > 0) rollForce else -rollForce
                } else {
                    particle2.velocityX += if (dx > 0) -rollForce else rollForce
                }
            }
        }
    }
}

