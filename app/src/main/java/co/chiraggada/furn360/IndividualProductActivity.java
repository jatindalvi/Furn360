package co.chiraggada.furn360;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import java.util.ArrayList;

import co.chiraggada.furn360.AR.ArActivity;
import mbanje.kurt.fabbutton.FabButton;

public class IndividualProductActivity extends AppCompatActivity {

    private SliderLayout product_slider_show;

    //ui
    FabButton goAR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_product);

        //ui component
        product_slider_show = findViewById(R.id.product_slider);
        goAR = (FabButton) findViewById(R.id.goAR);

        //start
        inflateProductSlider();

        goAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IndividualProductActivity.this, ArActivity.class));
            }
        });
    }

    private void inflateProductSlider() {

        //populating Image Slider
        ArrayList<String> sliderImages = new ArrayList<>();
        sliderImages.add("https://ii1.pepperfry.com/media/catalog/product/f/u/568x284/fuego-three-seater-sofa-in-garnet-red-colour-by-casacraft-fuego-three-seater-sofa-in-garnet-red-colo-62fqrt.jpg");
        sliderImages.add("https://ii2.pepperfry.com/media/catalog/product/f/u/568x284/fuego-three-seater-sofa-in-garnet-red-colour-by-casacraft-fuego-three-seater-sofa-in-garnet-red-colo-0gjmol.jpg");
        sliderImages.add("https://ii3.pepperfry.com/media/catalog/product/f/u/568x284/fuego-three-seater-sofa-in-garnet-red-colour-by-casacraft-fuego-three-seater-sofa-in-garnet-red-colo-0zrqlp.jpg");
        sliderImages.add("https://ii2.pepperfry.com/media/catalog/product/f/u/568x284/fuego-three-seater-sofa-in-garnet-red-colour-by-casacraft-fuego-three-seater-sofa-in-garnet-red-colo-qdjkgi.jpg");

        for (String s : sliderImages){
            DefaultSliderView sliderView = new DefaultSliderView(this);
            sliderView.image(s);
            product_slider_show.addSlider(sliderView);
        }

    }


}
