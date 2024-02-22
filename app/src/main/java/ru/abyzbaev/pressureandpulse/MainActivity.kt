package ru.abyzbaev.pressureandpulse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MeasurementsViewModel
    private lateinit var adapter: MeasurementsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val fab = findViewById<FloatingActionButton>(R.id.fab)

        viewModel = ViewModelProvider(this).get(MeasurementsViewModel::class.java)

        adapter = MeasurementsAdapter(emptyList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.getMeasurements().observe(this, { measurementGroups ->
            adapter.measurementGroups = measurementGroups
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            val measurement = Measurement(
                upperPressure = Random.nextInt(100, 180),
                lowerPressure = Random.nextInt(40, 120),
                pulse = Random.nextInt(50, 130),
                timestamp = System.currentTimeMillis()
            )
            viewModel.addMeasurement(measurement)
        }
    }
}

