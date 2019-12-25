package com.nijhoomt.ntrental.properties

import android.annotation.SuppressLint
import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nijhoomt.ntrental.R
import kotlinx.android.synthetic.main.row_property.view.*

/**
 * Created by N & T on 12/25/2019.
 * Under instructions of Varun, Manisha, Ansari, & Rahul
 */
class PropertiesListAdapter(
    @NonNull val application: Application
) : ListAdapter<Property, PropertiesListAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Property>() {
            override fun areItemsTheSame(oldItem: Property, newItem: Property): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Property, newItem: Property): Boolean {
                return oldItem.propertyaddress == newItem.propertyaddress &&
                        oldItem.propertycity == newItem.propertycity &&
                        oldItem.propertystate == newItem.propertystate &&

                        // Country is ZIPCODE
                        oldItem.propertycountry == newItem.propertycountry &&

                        oldItem.propertystatus == newItem.propertystatus &&
                        oldItem.propertypurchaseprice == newItem.propertypurchaseprice &&
                        oldItem.propertymortageinfo == newItem.propertymortageinfo &&
                        oldItem.propertyuserid == newItem.propertyuserid &&
                        oldItem.propertyusertype == newItem.propertyusertype &&
                        oldItem.propertylatitude == newItem.propertylatitude &&
                        oldItem.propertylongitude == newItem.propertylongitude
            }
        }
    }

    private lateinit var listener: OnItemClickListener

    fun getCartAt(position: Int): Property = getItem(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_property, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curNote = getItem(position)
        holder.bindDataToView(curNote, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindDataToView(
            curProperty: Property,
            position: Int
        ) {
            itemView.apply {

                tv_property_id.text = curProperty.id
                tv_property_address.text = "${curProperty.propertyaddress}, ${curProperty.propertycity}, ${curProperty.propertystate} ${curProperty.propertycountry}"
                tv_property_purchaseprice.text = curProperty.propertypurchaseprice

                setOnClickListener {
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(curProperty)
                    }
                }

            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(property: Property)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}