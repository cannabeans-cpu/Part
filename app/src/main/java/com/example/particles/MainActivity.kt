package com.example.particles

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var particleView: ParticleView
    private lateinit var particleTypeSpinner: Spinner
    private var selectedParticleType: String = "Sand"  // Default selection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        particleView = findViewById(R.id.particleView)
        particleTypeSpinner = findViewById(R.id.particleTypeSpinner)

        // Populate the spinner with particle types
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf("Sand", "Water"))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        particleTypeSpinner.adapter = adapter

        // Listen for selection events on spinner
        particleTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedParticleType = parent?.getItemAtPosition(position).toString()
            }
        }

        // Handle screen taps to spawn particles based on selected particle type
        particleView.setOnTouchListener { _, event ->
            when (selectedParticleType) {
                "Sand" -> particleView.spawnSandParticle(event.x, event.y)
                "Water" -> particleView.spawnWaterParticle(event.x, event.y)
                // Add more cases as you expand the types of particles
            }
            true
        }

        // Button to clear particles
        findViewById<Button>(R.id.clearButton).setOnClickListener {
            particleView.clearParticles()
        }
    }
}