package com.proyecto_cafe_mario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private SliderLayout mDemoSlider;

    //metodos para los menus BMB ---------------------------------------------------------------------------------
    private static int imageResourceIndex = 0;

    static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }


    private static int coloresIndex = 0;

    static int getColorResource() {
        if (coloresIndex >= colores.length) coloresIndex = 0;
        return colores[coloresIndex++];
    }

    private static int tituloIndex = 0;

    static int getTituloResource() {
        if (tituloIndex >= titulos.length) tituloIndex = 0;
        return titulos[tituloIndex++];
    }

    private static int subtituloIndex = 0;

    static int getSubTituloResource() {
        if (subtituloIndex >= subtitulos.length) subtituloIndex = 0;
        return subtitulos[subtituloIndex++];
    }

    //arreglos para las imagenes de los menu
    private static int[] imageResources = new int[]{
            R.mipmap.ic_ayuda,
            R.mipmap.ic_info,
            R.mipmap.ic_desarrolladores,

    };



    //arreglo para los colores
    private static int[] colores = new int[]{
            R.color.colorPrimary,
            R.color.colorPrimaryDark,
            R.color.colorAccent,
    };

    //arreglo para los titulos
    private static int[] titulos = new int[]{
            R.string.ayuda,
            R.string.acerca,
            R.string.desa,
    };

    //arreglo para los subtitulos
    private static int[] subtitulos = new int[]{
            R.string.manual,
            R.string.app_name,
            R.string.equidesa,
    };

    // fin de arreglos para los BMB--------------------------------------------------------------------




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        HashMap<String, String> url_maps = new HashMap<String, String>();

        url_maps.put("Imagen 1", "https://static.iris.net.co/dinero/upload/images//2018/8/15/261065_1.jpg");
        url_maps.put("Imagen 2", "https://www.portafolio.co/files/article_main/uploads/2017/02/22/58ae197f8d9d3.jpeg");
        url_maps.put("Imagen 3", "https://i.pinimg.com/originals/13/d8/50/13d85004008fc84e820e3f47044d8e3f.jpg");


        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(MainActivity.this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(MainActivity.this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOutSlide);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(MainActivity.this);
        //hasta aqui codigos para el slider

        // funcion de los BMB
        BoomMenuButton rightBmb = (BoomMenuButton) findViewById(R.id.action_bar_right_bmb_garzon);
        rightBmb.setButtonEnum(ButtonEnum.Ham);
        rightBmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_3);//numero de botones
        rightBmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_3);
        for (int i = 0; i < rightBmb.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {


                            switch (index){
                                case  0:
                                    //Intent manual = new Intent(InicioActivity.this,ReciclerManual.class);
                                    //startActivity(manual);
                                    Toast.makeText(MainActivity.this, "Manual de usuario", Toast.LENGTH_SHORT).show();
                                    break;

                                case 1:
                                    Intent acerca = new Intent(MainActivity.this,Informacion.class);
                                    startActivity(acerca);
                                    break;

                                case 2:
                                    Intent desa = new Intent(MainActivity.this,Desarrolladores.class);
                                    startActivity(desa);
                                    break;


                            }
                        }
                    })
                    .pieceColor(Color.WHITE)
                    .normalImageRes(getImageResource())
                    .normalTextRes(getTituloResource())
                    .subNormalTextRes(getSubTituloResource())
                    .textSize(20)
                    .subTextSize(15)
                    .normalColorRes(getColorResource());
            rightBmb.addBuilder(builder);
        }//fin de funcion BMB


        CardView protocolos = findViewById(R.id.card1);
        protocolos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent protocolos = new Intent(MainActivity.this,Protocolos.class);
                startActivity(protocolos);
            }
        });

        CardView decretos = findViewById(R.id.card2);
        decretos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent decretos = new Intent(MainActivity.this,Decretos.class);
                startActivity(decretos);
            }
        });

        CardView noticias = findViewById(R.id.card3);
        noticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent noticias = new Intent(MainActivity.this,Noticias.class);
                startActivity(noticias);
            }
        });

        CardView encuesta = findViewById(R.id.card4);
        encuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent encuesta = new Intent(MainActivity.this,Encuesta.class);
                startActivity(encuesta);
            }
        });

        CardView recomendador = findViewById(R.id.card5);
        recomendador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recomendador = new Intent(MainActivity.this,Recomendador.class);
                startActivity(recomendador);
            }
        });

        CardView reportes = findViewById(R.id.card6);
        reportes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reportes = new Intent(MainActivity.this,Reporte.class);
                startActivity(reportes);
            }
        });




    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}