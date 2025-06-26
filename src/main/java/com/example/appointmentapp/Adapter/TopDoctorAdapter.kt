package com.example.appointmentapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appointmentapp.Model.DoctorsModel
import com.example.appointmentapp.databinding.ViewholderTopDoctorBinding

class TopDoctorAdapter(private val items: List<DoctorsModel>) :
    RecyclerView.Adapter<TopDoctorAdapter.Viewholder>() {

    private lateinit var context: Context

    inner class Viewholder(val binding: ViewholderTopDoctorBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        context = parent.context
        val binding = ViewholderTopDoctorBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val doctor = items[position]

        // Setting data in ViewHolder
        holder.binding.nameTxt.text = doctor.name
        holder.binding.specialTxt.text = doctor.specialty
        holder.binding.scoreTxt.text = doctor.rating
        holder.binding.yearTxt.text = "${doctor.experience}"

        // Load doctor image using Glide with error handling
        Glide.with(holder.itemView.context)
            .load(doctor.image)
            .centerCrop()
            .error(com.example.appointmentapp.R.drawable.doctor) // Show default image on error
            .placeholder(com.example.appointmentapp.R.drawable.doctor) // Show while loading
            .into(holder.binding.img)
    }

    override fun getItemCount(): Int = items.size
}
