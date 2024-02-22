package ru.abyzbaev.pressureandpulse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class MeasurementsAdapter(var measurementGroups: List<MeasurementGroup>) : RecyclerView.Adapter<MeasurementsAdapter.MeasurementGroupViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasurementGroupViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.measurement_group_item, parent, false)
        return MeasurementGroupViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MeasurementGroupViewHolder, position: Int) {
        val measurementGroup = measurementGroups[position]
        holder.bind(measurementGroup)
    }

    override fun getItemCount(): Int {
        return measurementGroups.size
    }

    // ViewHolder для элементов списка
    class MeasurementGroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(measurementGroup: MeasurementGroup) {
            // Привяжите данные к ViewHolder для отображения в RecyclerView
            itemView.findViewById<TextView>(R.id.dateTextView).text = measurementGroup.date
            //itemView.dateTextView.text = measurementGroup.date

            // Создайте и настройте внутренний RecyclerView для отображения измерений для данной даты
            val measurementsRecyclerView = itemView.findViewById<RecyclerView>(R.id.measurementsRecyclerView)
            measurementsRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
            val measurementsAdapter = MeasurementsInnerAdapter(measurementGroup.measurements)
            measurementsRecyclerView.adapter = measurementsAdapter
        }
    }
}

// Внутренний адаптер RecyclerView для отображения измерений для каждой даты
class MeasurementsInnerAdapter(private val measurements: List<Measurement>) : RecyclerView.Adapter<MeasurementsInnerAdapter.MeasurementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasurementViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.measurement_item, parent, false)
        return MeasurementViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MeasurementViewHolder, position: Int) {
        val measurement = measurements[position]
        holder.bind(measurement)
    }

    override fun getItemCount(): Int {
        return measurements.size
    }

    // ViewHolder для элементов списка
    class MeasurementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(measurement: Measurement) {
            itemView.findViewById<TextView>(R.id.timestampTimeTextView).text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(measurement.timestamp))
            itemView.findViewById<TextView>(R.id.pressureTextView).text = "${measurement.upperPressure}  /  ${measurement.lowerPressure}"
            itemView.findViewById<TextView>(R.id.pulseTextView).text = "${measurement.pulse}"
        }
    }
}
