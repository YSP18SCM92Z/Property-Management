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
import com.bumptech.glide.Glide
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.model.Tenant
import kotlinx.android.synthetic.main.row_property_tenants.view.*

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

                tv_property_tenant_name.text = "Name: ${curTenant.tenantName}"
                tv_property_tenant_email.text = "Email: ${parseEmail(curTenant.tenantEmail)}"
                tv_property_tenant_mobile.text = "Mobile: ${parseMobile(curTenant.tenantMobile)}"

                val imageResourceId = getImageResourceId(curTenant.tenantName)
                Glide.with(this)
                    .load(imageResourceId)
                    .centerCrop()
                    .into(iv_property_tenant_image)

                setOnClickListener {
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(curTenant)
                    }
                }
            }
        }

        private fun getImageResourceId(tenantName: String): Int = with(tenantName.toLowerCase()) {
            when {
                contains("varun") -> R.drawable.varun_gupta
                contains("manisha") -> R.drawable.manisha_prasad
                contains("ansari") -> R.drawable.ansari
                contains("rahul") -> R.drawable.rahul_khurana
                contains("trump") -> R.drawable.donald_trump
                contains("navneet") -> R.drawable.navneet_singh
                contains("singh") -> R.drawable.navneet_singh
                else -> R.drawable.unknown_person
            }
        }

        private fun parseMobile(tenantMobile: String): CharSequence? {
            if (tenantMobile.length != 10) return "N/A"
            else {
                val mobileCharArr = tenantMobile.toCharArray()
                val stringBuilder = StringBuilder()
                stringBuilder.append('(')
                stringBuilder.append(mobileCharArr[0], mobileCharArr[1], mobileCharArr[2])
                stringBuilder.append(") ")
                stringBuilder.append(mobileCharArr[3], mobileCharArr[4], mobileCharArr[5])
                stringBuilder.append("-")
                stringBuilder.append(
                    mobileCharArr[6],
                    mobileCharArr[7],
                    mobileCharArr[8],
                    mobileCharArr[9]
                )
                return stringBuilder.toString()
            }
        }

        private fun parseEmail(tenantEmail: String): CharSequence? {
            val isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(tenantEmail).matches()
            return if (isEmailValid) tenantEmail else "N/A"
        }
    }

    interface OnItemClickListener {
        fun onItemClick(tenant: Tenant)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}