package co.chiraggada.furn360.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import java.util.ArrayList;

import co.chiraggada.furn360.AR.ArActivity;
import co.chiraggada.furn360.Adapters.BannerCardsAdapter;
import co.chiraggada.furn360.R;
import co.chiraggada.furn360.models.bannerCardsModel;

public class HomeFragment extends Fragment {


    private SliderLayout sliderShow;
    private RecyclerView recyclerView;
    private ArrayList<bannerCardsModel> bannerCardsModelArrayList;
    private BannerCardsAdapter bannerCardsAdapter;
    private int[] bannerlist = new int[]{R.drawable.sofa,R.drawable.storage,R.drawable.sofa,R.drawable.storage};
    private String[] bannername = new String[]{"sofa1","storage1","sofa2","storag2"};


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        View view = inflater.inflate(R.layout.fragment_cultural, container, false);
        Toast.makeText(getActivity(), "chirag was here", Toast.LENGTH_SHORT).show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        sliderShow = view.findViewById(R.id.slider);

        recyclerView = view.findViewById(R.id.recycler);

        bannerCardsModelArrayList = banners();
        bannerCardsAdapter = new BannerCardsAdapter(getActivity(),bannerCardsModelArrayList);
        recyclerView.setAdapter(bannerCardsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));


        inflateImageSlider();

        return view;
    }

    private ArrayList<bannerCardsModel> banners(){
        ArrayList<bannerCardsModel> list = new ArrayList<>();

        for(int i=0;i<4;i++){
            bannerCardsModel bannerModel = new bannerCardsModel();
            bannerModel.setImage_drawable(bannerlist[i]);
            bannerModel.setName(bannername[i]);
            list.add(bannerModel);
        }
        return list;
    }

    private void inflateImageSlider() {

        // Using Image Slider -----------------------------------------------------------------------


        //populating Image slider
        ArrayList<String> sliderImages = new ArrayList<>();
        sliderImages.add("https://ii2.pepperfry.com/media/wysiwyg/banners/Promo_912020.jpg");
        sliderImages.add("https://ii2.pepperfry.com/media/wysiwyg/banners/02_1012020.jpg");
        sliderImages.add("https://ii2.pepperfry.com/media/wysiwyg/banners/03_1012020.jpg");
        sliderImages.add("https://ii2.pepperfry.com/media/wysiwyg/banners/04_1012020.jpg");

        for (String s : sliderImages) {
            DefaultSliderView sliderView = new DefaultSliderView(getActivity());
            sliderView.image(s);
            sliderShow.addSlider(sliderView);
        }

        sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);

    }

    public void cardsActivity(View view) {
        startActivity(new Intent(getActivity(), ArActivity.class));
    }

    public void tshirtActivity(View view) {
        startActivity(new Intent(getActivity(), ArActivity.class));
    }

    public void bagsActivity(View view) {
        startActivity(new Intent(getActivity(), ArActivity.class));
    }

    public void stationaryAcitivity(View view) {
        startActivity(new Intent(getActivity(), ArActivity.class));
    }

    public void calendarsActivity(View view) {
        startActivity(new Intent(getActivity(), ArActivity.class));
    }

    public void keychainsActivity(View view) {
        startActivity(new Intent(getActivity(), ArActivity.class));
    }

}
