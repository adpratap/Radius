package com.noreplypratap.radius.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.noreplypratap.radius.R
import com.noreplypratap.radius.model.Option
import com.noreplypratap.radius.utilities.getIconResourceId

class FacilityAdapter(
    private val optionData: List<Option>
) : RecyclerView.Adapter<FacilityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_facility, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val option = optionData[position]
        holder.bind(option)
    }

    override fun getItemCount(): Int {
        return optionData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val facilityNameTextView: TextView = itemView.findViewById(R.id.tvFacilityName)
        private val facilityIconImageView: ImageView = itemView.findViewById(R.id.ivFacilityIcon)

        fun bind(option: Option) {
            facilityNameTextView.text = option.name
            facilityIconImageView.setImageResource(getIconResourceId(option.icon))

            itemView.setOnClickListener {
                onItemClicked?.let {
                    it(option)
                }
            }
        }

    }

    private var onItemClicked: ((Option) -> Unit)? = null

    fun setOnClickListener(listener: (Option) -> Unit) {
        onItemClicked = listener
    }
}

