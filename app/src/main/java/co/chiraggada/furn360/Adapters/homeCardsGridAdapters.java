package co.chiraggada.furn360.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import co.chiraggada.furn360.R;

public class homeCardsGridAdapters extends BaseAdapter {

    private static final String TAG = "HomeCardGridAdapters";
    private Context mcontext;
    private final String[] title;
    private final int[] Imageid;

    public homeCardsGridAdapters(Context ctx, String[] title, int[] imageid){
        mcontext = ctx;

        this.Imageid = imageid;
        this.title = title;

    }


    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null){

            grid = new View(mcontext);
            grid = inflater.inflate(R.layout.home_item,null);
            TextView textView = grid.findViewById(R.id.htv);
            ImageView imageView = grid.findViewById(R.id.hiv);
            textView.setText(title[i]);
            imageView.setImageResource(Imageid[i]);
        }else{
            grid = (View) view;
        }
        return grid;
    }

}