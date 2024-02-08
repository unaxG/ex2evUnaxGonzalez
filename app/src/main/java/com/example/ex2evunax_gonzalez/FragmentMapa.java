package com.example.ex2evunax_gonzalez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;
import com.example.ex2evunax_gonzalez.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import android.content.Intent;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.List;

public class FragmentMapa extends Fragment {

    ArrayList<OverlayItem> puntos = new ArrayList<>();
    MapView mapa;
    //boton para centrar el mapa
    FloatingActionButton but;
    //array de los puntos de ubicacion

    IMapController mapController;
    int datos = 0;

    public FragmentMapa() {
        // Required empty public constructor
    }

    public static FragmentMapa newInstance() {
        FragmentMapa fragment = new FragmentMapa();
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mapa, container, false);








            mapa = view.findViewById(R.id.mapaView);

            //seteamos el control del mapa
            mapa.setMultiTouchControls(true);
            //centramos el mapa
            GeoPoint centro = new GeoPoint(43.135, -2.5391);

            mapController = mapa.getController();
            mapController.setCenter(centro);
            mapController.setZoom(16.0);

            //boton que centra en el sitio de las ubicaciones, en caso de perdernos en el mapa



            //añadimos los puntos, y ponemos los colores correspondientes
            añadirPuntos();


            //si clicka la ubicación, sale el nombre de la ubicación
            ItemizedOverlayWithFocus<OverlayItem> overlays = new ItemizedOverlayWithFocus<OverlayItem>(getActivity().getApplicationContext(), puntos, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                @Override
                public boolean onItemSingleTapUp(int index, OverlayItem item) {
                    return true;
                }

                @Override
                public boolean onItemLongPress(int index, OverlayItem item) {
                    //si se mantiene, se comprueba que se va en orden
                    int id = index+1;
                    if(index == datos){
                        //y se va al activity del juego corresppondiente


                            getActivity().getSupportFragmentManager().beginTransaction().remove(FragmentMapa.this).commit();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.add(R.id.fragmentContainerView, FragmentInfo.newInstance());
                        fragmentTransaction.commit();


                    }
                    return true;
                }
            });

            //se colocan los puntos
            overlays.setFocusItemsOnTap(true);
            mapa.getOverlays().add(overlays);


        return view;
    }



        //función que añade los puntos, que se recogen de la base de datos
        void añadirPuntos(){




                //se crea el punto de geolocalizacion, con la latitud y longitud
                GeoPoint geo1 = new GeoPoint(43.135, -2.5391);
                //se crea el punto en el mapa y se añade a la lista
                OverlayItem punto1 = new OverlayItem("nombre", "Número ", geo1);
                puntos.add(punto1);

        }

        @Override
        public void onPause() {
            super.onPause();
            mapa.onPause();
        }

        @Override
        public void onResume() {
            super.onResume();
            mapa.onResume();
        }





    }




