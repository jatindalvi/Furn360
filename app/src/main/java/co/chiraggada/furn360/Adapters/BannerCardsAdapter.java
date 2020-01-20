package co.chiraggada.furn360.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import co.chiraggada.furn360.R;
import co.chiraggada.furn360.models.CardsModel;

public class BannerCardsAdapter extends RecyclerView.Adapter<BannerCardsAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<CardsModel> cardsModelArrayList;

    public BannerCardsAdapter(Context ctx, ArrayList<CardsModel> cardsModelsArraylist){
        inflater = LayoutInflater.from(ctx);
        this.cardsModelArrayList = cardsModelsArraylist;
    }

    @NonNull
    @Override
    public BannerCardsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cards_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BannerCardsAdapter.MyViewHolder holder, int position) {

        holder.iv.setImageResource(cardsModelArrayList.get(position).getImage_drawable());
        holder.text.setText(cardsModelArrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return cardsModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView iv;
        public MyViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.tv);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}