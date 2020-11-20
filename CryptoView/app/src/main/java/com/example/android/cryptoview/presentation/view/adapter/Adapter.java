package com.example.android.cryptoview.presentation.view.adapter;



import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.AndroidResourceSignature;
import com.example.android.cryptoview.R;
import com.example.android.cryptoview.data.repository.CryptoObject;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.CryptoViewHolder>{
    private List<CryptoObject> cryptoObjects;
    private static final String SELECTED_ARTICLE = "selectedArticle";

    public Adapter(@NonNull List<CryptoObject> cryptoObjects) {
        this.cryptoObjects = cryptoObjects;
    }

    public void setCryptoObjects(@NonNull List<CryptoObject> cryptoObjects) {
        this.cryptoObjects = cryptoObjects;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CryptoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crypto_item, parent, false);
        return new CryptoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoViewHolder holder, int position) {
        holder.bind(cryptoObjects.get(position), position);
    }

    @Override
    public int getItemCount() {
        return cryptoObjects.size();
    }

    public class CryptoViewHolder extends RecyclerView.ViewHolder {
        private TextView number;
        private ImageView icon;
        private TextView name;
        private TextView symbol;
        private TextView price;
        private TextView delta24h;
        private TextView delta7d;
        private TextView marketCap;
        private ImageView favoriteCrypto;
        private CryptoObject cryptoObject;


        public CryptoViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            icon = itemView.findViewById(R.id.icon);
            symbol = itemView.findViewById(R.id.symbol);
            number = itemView.findViewById(R.id.number);
            price = itemView.findViewById(R.id.price);
            delta24h = itemView.findViewById(R.id.delta_24h);
            delta7d = itemView.findViewById(R.id.delta_7d);
            marketCap = itemView.findViewById(R.id.market_cap);
            favoriteCrypto = itemView.findViewById(R.id.favorite_crypto);

            favoriteCrypto.setOnClickListener(v -> {
                favoriteCrypto.setImageResource(R.drawable.btn_star_big_on);
            });
        }

        public void bind(@NonNull CryptoObject cryptoObject, int position) {
            this.cryptoObject = cryptoObject;

            Context context = itemView.getContext();
            Glide.with(context)
                    .load(cryptoObject.getIconUrl())
                    .placeholder(R.drawable.ic_launcher_background)
                    .fitCenter()
                    .into(icon);

            this.number.setText(String.valueOf(position));
            this.name.setText(cryptoObject.getName());
            this.symbol.setText("(" + cryptoObject.getSymbol() + ")");
            this.price.setText("$" + String.format("%.2f", cryptoObject.getPrice()));

            if(cryptoObject.getPercentChange24h() >= 0) {
                delta24h.setText("▲" + String.format("%.2f", Math.abs(cryptoObject.getPercentChange24h())));
                delta24h.setTextColor(Color.GREEN);
            } else if(cryptoObject.getPercentChange24h() < 0) {
                delta24h.setText("▼" + String.format("%.2f", Math.abs(cryptoObject.getPercentChange24h())));
                delta24h.setTextColor(Color.RED);
            }

            if(cryptoObject.getPercentChange7d() >= 0) {
                delta7d.setText("▲" + String.format("%.2f", Math.abs(cryptoObject.getPercentChange7d())));
                delta7d.setTextColor(Color.GREEN);
            } else if(cryptoObject.getPercentChange7d() < 0) {
                delta7d.setText("▼" + String.format("%.2f", Math.abs(cryptoObject.getPercentChange7d())));
                delta7d.setTextColor(Color.RED);
            }

            this.marketCap.setText("$" + String.format("%.2f", cryptoObject.getMarketCap()));
        }
    }
}
