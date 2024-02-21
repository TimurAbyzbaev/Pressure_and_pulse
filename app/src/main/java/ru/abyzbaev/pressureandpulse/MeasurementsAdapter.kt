package ru.abyzbaev.pressureandpulse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class MeasurementsAdapter(var measurements: List<Measurement>) : RecyclerView.Adapter<MeasurementsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pressureTextView: TextView = view.findViewById(R.id.pressureTextView)
        val pulseTextView: TextView = view.findViewById(R.id.pulseTextView)
        val timestampTextView: TextView = view.findViewById(R.id.timestampTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_measurement, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val measurement = measurements[position]
        holder.pressureTextView.text = "Pressure: ${measurement.pressure}"
        holder.pulseTextView.text = "Pulse: ${measurement.pulse}"
        holder.timestampTextView.text = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date(measurement.timestamp))
    }

    override fun getItemCount(): Int {
        return measurements.size
    }
}
