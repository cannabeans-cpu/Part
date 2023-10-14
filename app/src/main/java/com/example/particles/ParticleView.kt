package com.example.particles

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.os.Handler
import android.os.Looper
import com.example.particles.controllers.ElementInteractionManager
import com.example.particles.controllers.ParticleInteraction
import com.example.particles.models.ElementParticle
import com.example.particles.models.ParticleFactory
import kotlin.math.pow
import kotlin.math.sqrt


class ParticleView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val particles = mutableListOf<ElementParticle>()
    private val paint = Paint()
    private val interactionManager = ElementInteractionManager()

    // Define a suitable threshold for interaction
    private val interactionDistanceThreshold = 20.0 // adjust this as per your game's requirements

    init {
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        particles.forEach { particle ->
            paint.color = particle.color
            canvas.drawCircle(particle.x, particle.y, particle.size, paint)
        }
    }

    fun spawnSandParticle(x: Float, y: Float) {
        val particle = ParticleFactory.createSandParticle(x, y)
        particles.add(particle)
        invalidate() // triggers onDraw
    }

    fun spawnWaterParticle(x: Float, y: Float) {
        // TODO: You'll need to implement this in the com.example.particles.models.ParticleFactory
        // val particle = com.example.particles.models.ParticleFactory.createWaterParticle(x, y)
        // particles.add(particle)
        // invalidate() // triggers onDraw
    }

    fun clearParticles() {
        particles.clear()
        invalidate()
    }

    private val handler = Handler(Looper.getMainLooper())
    private val updateIntervalMs: Long = 16  // Approx 60fps

    private val updateRunnable = object : Runnable {
        override fun run() {
            updateParticles()
            invalidate()
            handler.postDelayed(this, updateIntervalMs)
        }
    }

    init {
        handler.post(updateRunnable)
    }

    private fun updateParticles() {
        val iterator = particles.iterator()
        while (iterator.hasNext()) {
            val particle = iterator.next()

            // Apply gravity
            particle.velocityY += particle.accelerationY

            // Update position
            particle.y += particle.velocityY
            particle.x += particle.velocityX

            // Simple floor collision detection
            if (particle.y > height - particle.size) {
                particle.y = height - particle.size
                particle.velocityY = 0f  // Stop moving when it hits the floor
            }
        }

        for (i in particles.indices) {
            for (j in i + 1 until particles.size) {
                if (particlesAreInteracting(particles[i], particles[j])) {
                    val interactionPoint = computeInteractionPoint(particles[i], particles[j])
                    interactionManager.handleInteraction(particles[i], particles[j], interactionPoint)
                }
            }
        }
    }

    private fun particlesAreInteracting(particle1: ElementParticle, particle2: ElementParticle): Boolean {
        val distance = sqrt(
            (particle2.x - particle1.x).pow(2.0f) +
                    (particle2.y - particle1.y).pow(2.0f)
        )
        return distance <= interactionDistanceThreshold
    }

    private fun computeInteractionPoint(particle1: ElementParticle, particle2: ElementParticle): ParticleInteraction.Vector2 {
        val midX = (particle1.x + particle2.x) / 2.0f
        val midY = (particle1.y + particle2.y) / 2.0f
        return ParticleInteraction.Vector2(midX, midY)
    }
}