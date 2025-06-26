package com.example.appointmentapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appointmentapp.Model.CategoryModel
import com.example.appointmentapp.Model.DoctorsModel
import com.example.appointmentapp.R

class MainViewModel : ViewModel() {
    private val _category = MutableLiveData<List<CategoryModel>>()
    val category: LiveData<List<CategoryModel>> = _category

    private val _doctors = MutableLiveData<List<DoctorsModel>>()
    val doctors: LiveData<List<DoctorsModel>> = _doctors

    fun loadCategory() {
        val categories = listOf(
            CategoryModel("Cardiology", R.drawable.ic_cardiology),
            CategoryModel("Dentistry", R.drawable.ic_dentistry),
            CategoryModel("Neurology", R.drawable.ic_neurology),
            CategoryModel("Orthopedics", R.drawable.ic_orthopedics),
            CategoryModel("Pediatrics", R.drawable.ic_pediatrics),
            CategoryModel("Dermatology", R.drawable.ic_dermatology)
        )
        _category.value = categories
    }

    fun loadDoctors() {
        val doctorsList = listOf(
            DoctorsModel("Dr. John Smith", "Cardiologist", "4.8", "15 years", R.drawable.doctor_1),
            DoctorsModel("Dr. Sarah Johnson", "Dentist", "4.9", "10 years", R.drawable.doctor_2),
            DoctorsModel("Dr. Michael Brown", "Neurologist", "4.7", "12 years", R.drawable.doctor_3)
        )
        _doctors.value = doctorsList
    }
}
