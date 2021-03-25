package com.example.firebaseTraining.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseTraining.data.Car
import com.example.firebaseTraining.databinding.ListRowBinding

class CarAdapter(private val listener:OnCarItemLongClick):
RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    private val carsList = ArrayList<Car>()

    fun setCars(list: List<Car>) {
        carsList.clear()
        carsList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarAdapter.CarViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListRowBinding.inflate(layoutInflater,null,false)
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarAdapter.CarViewHolder, position: Int) {
        holder.name.text = carsList[position].name
        holder.productionYear.text = carsList[position].productionYear
    }

    override fun getItemCount(): Int {
        return carsList.size
    }

    fun removedCar(car:Car, position:Int) {
        carsList.remove(car)
        notifyItemRemoved(position)
    }
    inner class CarViewHolder(binding: ListRowBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnLongClickListener {
                listener.onCarLongClick(carsList[adapterPosition],adapterPosition)
                true
            }
        }
        val name = binding.carName
        val productionYear = binding.carProductionYear
        val image = binding.carImage
    }
}
interface OnCarItemLongClick {
    fun onCarLongClick(car:Car,position: Int)
}