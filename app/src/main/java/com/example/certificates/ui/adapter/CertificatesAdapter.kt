package com.example.certificates.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.certificates.R
import com.example.certificates.databinding.CertificateCardBinding
import com.example.certificates.api.Certificate
import com.squareup.picasso.Picasso

class CertificatesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var certificate: List<Certificate> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val categoryCard =
            CertificateCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(categoryCard)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as CategoryViewHolder
        val certificateItems = certificate[position]

        holder.bind(certificateItems)
    }

    override fun getItemCount(): Int {
        return certificate.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(certificateList: List<Certificate>) {
        this.certificate = certificateList
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(
        private val binding: CertificateCardBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(certificate: Certificate) {
            binding.apply {
                Picasso.get().load(certificate.image)
                    .placeholder(R.drawable.loading)
                    .error(android.R.drawable.stat_notify_error)
                    .into(certificateIcon)
                certificateTv.text = certificate.name

                certificateCard.animation =
                    AnimationUtils.loadAnimation(itemView.context, R.anim.recycler_animation)
            }
        }
    }
}

