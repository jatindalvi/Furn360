package co.chiraggada.furn360.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import co.chiraggada.furn360.R;
import co.chiraggada.furn360.models.CardsModel;

public class HomeCardsAdapters extends RecyclerView.Adapter<HomeCardsAdapters.homeCardsHolder> {

    private static final String TAG = "HomeCardAdapters";
    private LayoutInflater inflater;
    private ArrayList<CardsModel> cardsModelArrayList;
    private OnCardListener mOnCardListener;

    public HomeCardsAdapters(Context ctx, ArrayList<CardsModel> CardsModels, OnCardListener onCardListener){

        inflater = LayoutInflater.from(ctx);
        this.cardsModelArrayList = CardsModels;
        this.mOnCardListener = onCardListener;

    }

    @NonNull
    @Override
    public HomeCardsAdapters.homeCardsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.home_item,parent,false);
        homeCardsHolder holder = new homeCardsHolder(view,mOnCardListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCardsAdapters.homeCardsHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return cardsModelArrayList.size();
    }

    public class homeCardsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView text;
        ImageView iv;
        OnCardListener mOnCardListener;

        public homeCardsHolder(View itemView, OnCardListener onCardListener) {
            super(itemView);
            text = itemView.findViewById(R.id.htv);
            iv = itemView.findViewById(R.id.hiv);
            mOnCardListener = onCardListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG,"onCLick: "+ getAdapterPosition());
            mOnCardListener.cardListener(getAdapterPosition());

        }
    }

    public interface OnCardListener{
        void cardListener(int position);
    }
}
