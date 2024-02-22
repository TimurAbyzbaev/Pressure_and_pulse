package ru.abyzbaev.pressureandpulse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class MeasurementsAdapter(var measurements: List<Measurement>) : RecyclerView.Adapter<MeasurementsAdapter.ViewHolder>() {

    init {
        val sortedMeasurements = measurements.sortedByDescending { it.timestamp }
        // Присвойте отсортированный список переменной класса
        this.measurements = sortedMeasurements
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pressureTextView: TextView = view.findViewById(R.id.pressureTextView)
        val pulseTextView: TextView = view.findViewById(R.id.pulseTextView)
        val timestampTextView: TextView = view.findViewById(R.id.timestampTextView)
        val timestampTimeTextView: TextView = view.findViewById(R.id.timestampTimeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_measurement, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val measurement = measurements[position]
        holder.pressureTextView.text = "${measurement.upperPressure}  /  ${measurement.lowerPressure}"
        holder.pulseTextView.text = "${measurement.pulse}"
val date = Date(measurement.timestamp)
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        holder.timestampTextView.text = dateFormat.format(date)
        holder.timestampTimeTextView.text = timeFormat.format(date)
    }

    override fun getItemCount(): Int {
        return measurements.size
    }
}
