package com.example.directory.presentation.colleagues

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.colleagues.domain.PeopleResponseItem
import com.example.directory.databinding.ItemLayoutColleagueBinding

class ColleaguesAdapter(
    private val colleagues: ArrayList<PeopleResponseItem>,
    private val clickListener: OnColleagueItemClick
) : RecyclerView.Adapter<ColleaguesAdapter.ColleaguesViewHolder>() {

    fun updateColleagues(newColleagues: List<PeopleResponseItem>) {
        colleagues.clear()
        colleagues.addAll(newColleagues)
        notifyDataSetChanged()
    }

    inner class ColleaguesViewHolder(val binding: ItemLayoutColleagueBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColleaguesViewHolder {
        val binding =
            ItemLayoutColleagueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ColleaguesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColleaguesViewHolder, position: Int) {
        with(holder) {
            with(binding.colleagueItem) {
                with(colleagues[position]) {
                    setLabelText("$firstName $lastName")
                    setIconStart(avatar)
                }
            }
        }
    }

    override fun getItemCount(): Int = colleagues.size

    class OnColleagueItemClick(val clickListener: (colleague: PeopleResponseItem) -> Unit) {
        fun onClick(colleague: PeopleResponseItem) = clickListener(colleague)
    }

}
