package com.example.android.cryptoview.presentation.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.android.cryptoview.R
import com.example.android.cryptoview.data.repository.CryptoObject
import com.example.android.cryptoview.presentation.view.MainActivity


class Adapter(private var cryptoObjects: List<CryptoObject>, private val activity: MainActivity) : RecyclerView.Adapter<Adapter.CryptoViewHolder>() {
    fun setCryptoObjects(cryptoObjects: List<CryptoObject?>?) {
        this.cryptoObjects = cryptoObjects as List<CryptoObject>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val v: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.crypto_item, parent, false)
        return CryptoViewHolder(v)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.bind(cryptoObjects[position], position)
    }

    override fun getItemCount(): Int {
        return cryptoObjects.size
    }

    inner class CryptoViewHolder(itemView: View) : ViewHolder(itemView) {
        private val number: TextView = itemView.findViewById(R.id.number)
        private val icon: ImageView = itemView.findViewById(R.id.icon)
        private val name: TextView = itemView.findViewById(R.id.name)
        private val symbol: TextView = itemView.findViewById(R.id.symbol)
        private val price: TextView = itemView.findViewById(R.id.price)
        private val delta24h: TextView = itemView.findViewById(R.id.delta_24h)
        private val delta7d: TextView = itemView.findViewById(R.id.delta_7d)
        private val marketCap: TextView = itemView.findViewById(R.id.market_cap)
        private val favoriteCrypto: ImageView = itemView.findViewById(R.id.favorite_crypto)
        private lateinit var cryptoObject: CryptoObject

        init {
            favoriteCrypto.setOnClickListener { v: View? -> activity.onClickItem(cryptoObject!!) }
        }

        fun bind(cryptoObject: CryptoObject, position: Int) {
            this.cryptoObject = cryptoObject

            if (cryptoObject.isFavourite!!) {
                favoriteCrypto.setImageResource(R.drawable.btn_star_big_on)
            } else {
                favoriteCrypto.setImageResource(R.drawable.btn_star_big_off)
            }
            Glide.with(itemView.context)
                    .load(cryptoObject.iconUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .fitCenter()
                    .into(icon)
            number.text = position.toString()
            name.text = cryptoObject.name
            symbol.text = "(" + cryptoObject.symbol + ")"
            price.text = "$" + String.format("%.2f", cryptoObject.price)
            if (cryptoObject.percentChange24h!! >= 0) {
                delta24h.text = "▲" + String.format("%.2f", Math.abs(cryptoObject.percentChange24h))
                delta24h.setTextColor(Color.GREEN)
            } else if (cryptoObject.percentChange24h < 0) {
                delta24h.text = "▼" + String.format("%.2f", Math.abs(cryptoObject.percentChange24h))
                delta24h.setTextColor(Color.RED)
            }
            if (cryptoObject.percentChange7d!! >= 0) {
                delta7d.text = "▲" + String.format("%.2f", Math.abs(cryptoObject.percentChange7d))
                delta7d.setTextColor(Color.GREEN)
            } else if (cryptoObject.percentChange7d < 0) {
                delta7d.text = "▼" + String.format("%.2f", Math.abs(cryptoObject.percentChange7d))
                delta7d.setTextColor(Color.RED)
            }
            marketCap.text = "$" + String.format("%.2f", cryptoObject.marketCap)
        }
    }

}
