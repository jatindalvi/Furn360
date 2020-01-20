package co.chiraggada.furn360.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import java.util.ArrayList;

import co.chiraggada.furn360.AR.ArActivity;
import co.chiraggada.furn360.Adapters.BannerCardsAdapter;
import co.chiraggada.furn360.Adapters.HomeCardsAdapters;
import co.chiraggada.furn360.Adapters.homeCardsGridAdapters;
import co.chiraggada.furn360.R;
import co.chiraggada.furn360.models.CardsModel;

public class HomeFragment extends Fragment implements HomeCardsAdapters.OnCardListener {

    private static final String TAG = "HomeFragment";

    //ui components
    private SliderLayout sliderShow;
    private RecyclerView mrecyclerView,homerecyclerview;
    private GridView home_grid;

    //vars
    private ArrayList<CardsModel> list = new ArrayList<>();

    private ArrayList<CardsModel> cardsModelArrayList;
    private BannerCardsAdapter bannerCardsAdapter;

    private HomeCardsAdapters mHomeCardsAdapter;

    private int[] bannerlist = new int[]{R.drawable.sofa,R.drawable.storage,R.drawable.single,R.drawable.desk,R.drawable.dining};
    private String[] bannername = new String[]{"sofa","storage","Bed","Desk","Dining"};


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        View view = inflater.inflate(R.layout.fragment_cultural, container, false);
//        Toast.makeText(getActivity(), "chirag was here", Toast.LENGTH_SHORT).show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        sliderShow = view.findViewById(R.id.slider);
        mrecyclerView = view.findViewById(R.id.recycler);
        homerecyclerview = view.findViewById(R.id.menurecycler);
        home_grid = view.findViewById(R.id.grid_home);



        cardsModelArrayList = banners();
        inflatemenu();
//        inithomeRecyclerview();
        bannerCardsAdapter = new BannerCardsAdapter(getActivity(), cardsModelArrayList);
        mrecyclerView.setAdapter(bannerCardsAdapter);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        homeCardsGridAdapters ad = new homeCardsGridAdapters(getActivity(),bannername,bannerlist);
        home_grid.setAdapter(ad);
        home_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),bannername[+i],Toast.LENGTH_SHORT).show();
            }
        });

        inflateImageSlider();

        return view;
    }

    private void inflatemenu() {



    }

    private ArrayList<CardsModel> banners(){

        for(int i=0;i<5;i++){
            CardsModel bannerModel = new CardsModel();
            bannerModel.setImage_drawable(bannerlist[i]);
            bannerModel.setName(bannername[i]);
            list.add(bannerModel);
        }
        return list;
    }

    private void inithomeRecyclerview(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        homerecyclerview.setLayoutManager(linearLayoutManager);
        mHomeCardsAdapter = new HomeCardsAdapters(getActivity(),cardsModelArrayList,this);
        homerecyclerview.setAdapter(mHomeCardsAdapter);

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

    @Override
    public void cardListener(int position) {
        Toast.makeText(getActivity(), Integer.toString(position), Toast.LENGTH_SHORT).show();
    }
}
