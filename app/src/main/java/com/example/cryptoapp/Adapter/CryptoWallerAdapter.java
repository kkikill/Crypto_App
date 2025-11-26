package com.example.cryptoapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cryptoapp.Domain.CryptoWallet;
import com.example.cryptoapp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.lang.Exception;

public class CryptoWallerAdapter extends RecyclerView.Adapter<CryptoWallerAdapter.Viewholder> {
    ArrayList<CryptoWallet> cryptoWallets;

    public CryptoWallerAdapter(ArrayList<CryptoWallet> cryptoWallets) {
        this.cryptoWallets = cryptoWallets;
        formatter=new DecimalFormat("###,###,###,###.##");
    }

    DecimalFormat formatter;
    
    @NonNull
    @Override
    public CryptoWallerAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_item,parent,false);
        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoWallerAdapter.Viewholder holder, int position) {
    holder.cryptoSymbolTxt.setText(cryptoWallets.get(position).getCryptoSymbol());
    holder.cryptoBalancedTxt.setText("$"+formatter.format(cryptoWallets.get(position).getCryptoBalance()));
    holder.changePercentTxt.setText(cryptoWallets.get(position).getChangePercent()+"%");
    holder.propertyAmountTxt.setText(cryptoWallets.get(position).getPropertyAmount()+" "+cryptoWallets.get(position).getCryptoSymbol());

    int drawableResourseId=holder.itemView.getContext().getResources()
            .getIdentifier(cryptoWallets.get(position).getPicUrl(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourseId)
                .into(holder.logoCrypto);

        try{
            if(cryptoWallets.get(position).getCryptoBalance()>=0){
                holder.changePercentTxt.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.green));
            }else{
                holder.changePercentTxt.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.red));
            }
        }catch (Exception e){

        }
    }

    @Override
    public int getItemCount() {
        return cryptoWallets.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView cryptoSymbolTxt,cryptoBalancedTxt,changePercentTxt,propertyAmountTxt;
        ImageView logoCrypto;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            cryptoSymbolTxt=itemView.findViewById(R.id.symbolTxt);
            cryptoBalancedTxt=itemView.findViewById(R.id.balanceTxt);
            changePercentTxt=itemView.findViewById(R.id.percentTxt);
            propertyAmountTxt=itemView.findViewById(R.id.amountTxt);
            logoCrypto=itemView.findViewById(R.id.pic);
        }
    }
}
