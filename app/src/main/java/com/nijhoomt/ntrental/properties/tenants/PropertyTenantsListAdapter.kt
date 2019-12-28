package com.nijhoomt.ntrental.properties.tenants

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
import com.nijhoomt.ntrental.model.Tenant
import kotlinx.android.synthetic.main.row_property.view.*

/**
 * Created by N & T on 12/25/2019.
 * Under instructions of Varun, Manisha, Ansari, & Rahul
 */
class PropertyTenantsListAdapter(
    @NonNull val application: Application
) : ListAdapter<Tenant, PropertyTenantsListAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Tenant>() {
            override fun areItemsTheSame(oldItem: Tenant, newItem: Tenant): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Tenant, newItem: Tenant): Boolean {
                return oldItem.tenantName == newItem.tenantName &&
                        oldItem.tenantAddress == newItem.tenantAddress &&
                        oldItem.tenantEmail == newItem.tenantEmail &&
                        oldItem.tenantMobile == newItem.tenantMobile &&
                        oldItem.landlordId == newItem.landlordId &&
                        oldItem.propertyId == newItem.propertyId
            }
        }
    }

    private lateinit var listener: OnItemClickListener

    fun getTenantAt(position: Int): Tenant = getItem(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_property_tenants, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curTenant = getItem(position)
        holder.bindDataToView(curTenant, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindDataToView(
            curTenant: Tenant,
            position: Int
        ) {
            itemView.apply {

                tv_property_id.text = "Id: ${curTenant.id}"
                tv_property_address.text = "Address: ${curTenant.tenantAddress}"

                setOnClickListener {
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(curTenant)
                    }
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(tenant: Tenant)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}