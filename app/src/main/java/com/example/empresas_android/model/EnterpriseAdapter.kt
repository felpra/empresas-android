package com.example.empresas_android.model

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.empresas_android.R.*
import com.example.empresas_android.api.Constants
import com.example.empresas_android.ui.DetailActivity
import java.util.*


class EnterpriseAdapter(private var context: Activity,
                        private var enterpriseArrayList: ArrayList<Enterprise>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val rootView: View = LayoutInflater.from(context).inflate(layout.recyclerview_item, parent, false)
        val intent = Intent(context, DetailActivity::class.java)
        return RecyclerViewViewHolder(rootView).listen { pos, _ ->
            intent.putExtra("photo", Constants.BASE_URL + enterpriseArrayList[pos].photo)
            intent.putExtra("description", enterpriseArrayList[pos].description)
            intent.putExtra("name", enterpriseArrayList[pos].name)
            context.startActivity(intent)
        }
    }

    private fun <RecyclerViewViewHolder : RecyclerView.ViewHolder> RecyclerViewViewHolder.listen(event: (position: Int, type: Int) -> Unit): RecyclerViewViewHolder {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val enterprise: Enterprise = enterpriseArrayList[position]
        val viewHolder = holder as RecyclerViewViewHolder
        Glide.with(context).load(Constants.BASE_URL + enterprise.photo).into(viewHolder.imgviewIcon)
        viewHolder.txtviewTitle.text = enterprise.name
        viewHolder.txtviewDescription.text = enterprise.eterprise_type.type
        viewHolder.txtView_country.text = enterprise.country
    }

    override fun getItemCount(): Int {
        return enterpriseArrayList.size
    }

    internal inner class RecyclerViewViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var imgviewIcon: ImageView = itemView.findViewById(id.icon)
        var txtviewTitle: TextView = itemView.findViewById(id.title_enterprise)
        var txtviewDescription: TextView
        var txtView_country: TextView

        init {
            txtviewDescription = itemView.findViewById(id.enterprise_description)
            txtView_country = itemView.findViewById(id.enterprise_country)
        }
    }

}