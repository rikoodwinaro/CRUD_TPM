package com.example.crud.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.entity.DataCrypto;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder> {
   Context context;
   List<DataCrypto> list;
   MainContact.view mView;

   public MainAdapter(Context context, List<DataCrypto> list, MainContact.view mView){
       this.context = context;
       this.list = list;
       this.mView = mView;
   }

    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
       View view = LayoutInflater.from(context).inflate(R.layout.item_crypto,parent,false);
       return new viewHolder(view);
    }

    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
       final DataCrypto item = list.get(position);
        holder.tvCurrency.setText(item.getCurrency());
        holder.tvPrice.setText(item.getPrice());
        holder.tvYear.setText(item.getYear());
        holder.tvFounder.setText(item.getFounder());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mView.editData(item);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mView.deleteData(item);
                return true;
            }
        });
    }

    public int getItemCount() {
       return  list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvCurrency, tvPrice, tvYear, tvFounder, id;
        CardView cardView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvCurrency = itemView.findViewById(R.id.tvCurrency);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvYear = itemView.findViewById(R.id.tvYear);
            tvFounder = itemView.findViewById(R.id.tvFounder);
            cardView = itemView.findViewById(R.id.cv_item);
        }

   }
}
