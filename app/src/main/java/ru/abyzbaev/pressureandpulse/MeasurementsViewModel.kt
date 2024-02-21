package ru.abyzbaev.pressureandpulse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MeasurementsViewModel : ViewModel() {
    private val db = Firebase.firestore
    private val measurementsCollection = db.collection("measurements")

    fun addMeasurement(measurement: Measurement) {
        measurementsCollection.add(measurement)
    }

    fun getMeasurements(): LiveData<List<Measurement>> {
        val measurementsLiveData = MutableLiveData<List<Measurement>>()
        measurementsCollection.addSnapshotListener { value, _ ->
            val measurements = value?.documents?.mapNotNull { doc ->
                doc.toObject(Measurement::class.java)
            }
            measurementsLiveData.value = measurements
        }
        return measurementsLiveData
    }
}
