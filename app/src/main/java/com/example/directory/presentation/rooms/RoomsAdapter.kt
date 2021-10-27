package com.example.directory.presentation.rooms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.directory.databinding.ItemLayoutRoomsBinding
import com.example.rooms.domain.RoomsResponseItem

class RoomsAdapter(
    private val rooms: ArrayList<RoomsResponseItem>
) : RecyclerView.Adapter<RoomsAdapter.RoomsViewHolder>() {

    fun updateRooms(newRooms: List<RoomsResponseItem>) {
        rooms.clear()
        rooms.addAll(newRooms)
        notifyDataSetChanged()
    }


    class RoomsViewHolder(val binding: ItemLayoutRoomsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder {
        val binding =
            ItemLayoutRoomsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
        with(holder) {
            with(binding.roomItem) {
                with(rooms[position]) {
                    setLabelText(name)
                    is_occupied?.let {
                        when (it) {
                            true -> setActionEnd(0)
                            else -> setActionEnd(2)
                        }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = rooms.size
}