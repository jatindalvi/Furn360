package co.chiraggada.furn360.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import co.chiraggada.furn360.R;
import co.chiraggada.furn360.models.bannerCardsModel;

public class CustomTextureAdapter extends RecyclerView.Adapter<CustomTextureAdapter.textureViewHolder> {

    private String TAG = "CustomTextureAdater";
    private LayoutInflater inflater;
    private ArrayList<bannerCardsModel> bannerCardsModelArrayList;
    private OnTextureListener onTextureListener;

    public  CustomTextureAdapter(Context ctx, ArrayList<bannerCardsModel> bannerCardsModelsArrayList,OnTextureListener onTextureListener){
        inflater = LayoutInflater.from(ctx);
        this.bannerCardsModelArrayList = bannerCardsModelsArrayList;
        this.onTextureListener = onTextureListener;
    }

    @NonNull
    @Override
    public textureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.cards_item,parent,false);
        textureViewHolder holder = new textureViewHolder(view,onTextureListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull textureViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return bannerCardsModelArrayList.size();
    }

    public class textureViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView iv;
        OnTextureListener onTextureListener;

        public textureViewHolder(View itemView,OnTextureListener onTextureListener) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            this.onTextureListener = onTextureListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG,"onCLick: " +getAdapterPosition());
            onTextureListener.textureListener(getAdapterPosition());
        }
    }

    public interface OnTextureListener{
        void textureListener(int position);
    }
}
