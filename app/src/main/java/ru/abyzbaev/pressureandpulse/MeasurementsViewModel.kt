package ru.abyzbaev.pressureandpulse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class MeasurementsViewModel : ViewModel() {
    private val db = Firebase.firestore
    private val measurementsCollection = db.collection("measurements")

    fun addMeasurement(measurement: Measurement) {
        measurementsCollection.add(measurement)
    }

    fun getMeasurements(): LiveData<List<MeasurementGroup>> {
        val measurementsLiveData = MutableLiveData<List<MeasurementGroup>>()
        measurementsCollection.addSnapshotListener { value, _ ->
            val measurements = value?.documents?.mapNotNull { doc ->
                doc.toObject(Measurement::class.java)
            }
            val groupedMeasurements = measurements?.groupBy {
                SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date(it.timestamp))
            } ?: emptyMap()
            val measurementGroups = groupedMeasurements.map { (date, measurements) ->
                MeasurementGroup(date, measurements)
            }
            measurementsLiveData.value = measurementGroups
        }
        return measurementsLiveData
    }
}

