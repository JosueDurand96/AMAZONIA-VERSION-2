package amazoniaresilientes.durand.josue.amazoniaresiliente.Inicio;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.cocoahero.android.geojson.Feature;
import com.cocoahero.android.geojson.FeatureCollection;
import com.cocoahero.android.geojson.GeoJSON;
import com.cocoahero.android.geojson.GeoJSONObject;
import com.cocoahero.android.geojson.Polygon;
import com.cocoahero.android.geojson.Position;
import com.cocoahero.android.geojson.PositionList;
import com.cocoahero.android.geojson.Ring;
import com.github.bkhezry.mapdrawingtools.model.DrawingOption;
import com.github.bkhezry.mapdrawingtools.utils.CalUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.maps.android.data.geojson.GeoJsonFeature;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;
import com.tbruyelle.rxpermissions.RxPermissions;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import amazoniaresilientes.durand.josue.amazoniaresiliente.R;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Observable;
import rx.functions.Action1;

public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener  {

    private final static String TAG = "MapaActivity";
    public static JSONObject JSON_OBJECT_COLLECTION = null;
    public static final int REQUEST_CODE = 1;
    private Observable<Location> lastKnownLocationObservable;
    private GoogleMap mMap;
    private DrawingOption.DrawingType currentDrawingType;
    private DrawingOption drawingOption;
    private ReactiveLocationProvider locationProvider;
    public static final String POINTS = "points";
    Location mLastLocation;
    GoogleApiClient mGoogleApiClient;
    LocationManager locationManager;
    Location currentLocation;
    LocationRequest mLocationRequest;
    private com.google.android.gms.maps.model.Polygon polygon;
    private Polyline polyline;
    //private Polygon polygon;
    private List<LatLng> points = new ArrayList<>();
    private List<Marker> markerList = new ArrayList<>();
    Marker mCurrLocationMarker;
    private TextView areaTextView;
    private TextView lengthTextView;
    View calLayout;
    String regionSeleccionado;
    String regionSeleccionadoTitulo;
    Toolbar toolbar4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        regionSeleccionado = getIntent().getStringExtra("region");
        regionSeleccionadoTitulo = getIntent().getStringExtra("regionTitulo");
        setContentView(R.layout.activity_mapa);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
//        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar4 = findViewById(R.id.toolbar44);
        //    setSupportActionBar(toolbar);
        currentDrawingType = DrawingOption.DrawingType.POLYGON;

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        calLayout = mapFragment.getView();
        mapFragment.getMapAsync(this);
        areaTextView = (TextView) findViewById(R.id.areaTextView);
        lengthTextView = (TextView) findViewById(R.id.lengthTextView);
        setUpButtons();
        //cargaDatos();
    }

    @Override
    protected void onStart() {
        super.onStart();
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) {
                            //new asyncGeoJsonAmazonas().execute();
                        } else {
                            Toast.makeText(MapaActivity.this, "Sorry, App did not work without Location permission", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        System.out.println("ZONA PRUEBA: "+regionSeleccionado);
        this.toolbar4.setTitle("LUGAR: " + regionSeleccionadoTitulo);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadGeoJson(regionSeleccionado, "areaconservacionprivada");
                        loadGeoJson2(regionSeleccionado, "areaconservacionregional");
                        loadGeoJson3(regionSeleccionado, "areanaturalprotegida");
                        loadGeoJson4(regionSeleccionado, "bosqueproduccionpermanante");
                        loadGeoJson5(regionSeleccionado, "comunidadescampesinas");
                        loadGeoJson6(regionSeleccionado, "comunidadesnativas");
                        loadGeoJson7(regionSeleccionado, "concesioncastana");
                        loadGeoJson8(regionSeleccionado, "concesionconservacion");
                        loadGeoJson9(regionSeleccionado, "concesionecoturismo");
                        loadGeoJson10(regionSeleccionado, "concesionmaderable");
                        loadGeoJson11(regionSeleccionado, "concesionmanejofaunasilvestre");
                        loadGeoJson12(regionSeleccionado, "concesionreforestacion");
                        loadGeoJson13(regionSeleccionado, "humedales");
//
                        //       loadGeoJson14(regionSeleccionado, "perdida2012");
//            loadGeoJson15(regionSeleccionado, "perdida2013");
//            loadGeoJson16(regionSeleccionado, "perdida2014");
//            loadGeoJson17(regionSeleccionado, "perdida2015");
                        //    loadGeoJson18(regionSeleccionado, "perdida2016");
//
//
                        loadGeoJson19(regionSeleccionado, "reservascomunales");
                        loadGeoJson20(regionSeleccionado, "reservasterritoriales");

                    }
                });
                mMap.setMyLocationEnabled(true);
                View locationButton = ((View) calLayout.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
                RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
                // position on right bottom


                rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                rlp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
                rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);

                rlp.addRule(RelativeLayout.ALIGN_PARENT_END, 0);
                rlp.addRule(RelativeLayout.ALIGN_END, 0);
                rlp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                rlp.setMargins(30, 0, 0, 40);
            }
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //   loadGeoJson(regionSeleccionado, "areaconservacionprivada");
                    //    loadGeoJson2(regionSeleccionado, "areaconservacionregional");
//          loadGeoJson3(regionSeleccionado, "areanaturalprotegida");
//          loadGeoJson4(regionSeleccionado, "bosqueproduccionpermanante");
//          loadGeoJson5(regionSeleccionado, "comunidadescampesinas");
//
//          loadGeoJson6(regionSeleccionado, "comunidadesnativas");
//          loadGeoJson7(regionSeleccionado, "concesioncastana");
//          loadGeoJson8(regionSeleccionado, "concesionconservacion");
//          loadGeoJson9(regionSeleccionado, "concesionecoturismo");
//          loadGeoJson10(regionSeleccionado, "concesionmaderable");
//
//          loadGeoJson11(regionSeleccionado, "concesionmanejofaunasilvestre");
//          loadGeoJson12(regionSeleccionado, "concesionreforestacion");
//          loadGeoJson13(regionSeleccionado, "humedales");
//
                    //   loadGeoJson14(regionSeleccionado, "perdida2012");
//          loadGeoJson14(regionSeleccionado, "perdida2013");
//          loadGeoJson14(regionSeleccionado, "perdida2014");
//          loadGeoJson14(regionSeleccionado, "perdida2015");
//          loadGeoJson14(regionSeleccionado, "perdida2016");
//
//
                    //   loadGeoJson19(regionSeleccionado, "reservascomunales");
//          loadGeoJson20(regionSeleccionado, "reservasterritoriales");
                }
            });
            mMap.setMyLocationEnabled(true);

            View locationButton = ((View) calLayout.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
            RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();

            rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            rlp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);

            rlp.addRule(RelativeLayout.ALIGN_PARENT_END, 0);
            rlp.addRule(RelativeLayout.ALIGN_END, 0);
            rlp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            rlp.setMargins(30, 0, 0, 40);
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-6.672653, -76.859112), 7));
    }
    //VERDE
    public void loadGeoJson(String region, String zona) {
        try {
            InputStream is = getAssets().open(region + "/" + "areaconservacionprivada" + ".geojson");
            GeoJSONObject geoJsonData = GeoJSON.parse(is);
            is.close();
            GeoJsonLayer layer = new GeoJsonLayer(mMap, geoJsonData.toJSON());
            layer.addLayerToMap();
            for (GeoJsonFeature feature : layer.getFeatures()) {
                GeoJsonPolygonStyle geoJsonPolygonStyle = new GeoJsonPolygonStyle();
                geoJsonPolygonStyle.setFillColor(Color.argb(5, 0, 255, 5));
                geoJsonPolygonStyle.setStrokeColor(Color.argb(50, 85, 85, 85));
                geoJsonPolygonStyle.setStrokeWidth(3);
                geoJsonPolygonStyle.setZIndex(100);
                feature.setPolygonStyle(geoJsonPolygonStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //VERDE
    public void loadGeoJson2(String region, String zona) {
        try {
            InputStream is = getAssets().open(region + "/" + "areaconservacionregional" + ".geojson");
            GeoJSONObject geoJsonData = GeoJSON.parse(is);
            is.close();
            GeoJsonLayer layer = new GeoJsonLayer(mMap, geoJsonData.toJSON());
            layer.addLayerToMap();
            for (GeoJsonFeature feature : layer.getFeatures()) {
                GeoJsonPolygonStyle geoJsonPolygonStyle = new GeoJsonPolygonStyle();
                geoJsonPolygonStyle.setFillColor(Color.argb(5, 0, 255, 5));
                geoJsonPolygonStyle.setStrokeColor(Color.argb(50, 85, 85, 85));
                geoJsonPolygonStyle.setStrokeWidth(3);
                geoJsonPolygonStyle.setZIndex(100);
                feature.setPolygonStyle(geoJsonPolygonStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //VERDE
    public void loadGeoJson3(String region, String zona) {
        try {
            InputStream is = getAssets().open(region + "/" + "areanaturalprotegida" + ".geojson");
            GeoJSONObject geoJsonData = GeoJSON.parse(is);
            is.close();
            GeoJsonLayer layer = new GeoJsonLayer(mMap, geoJsonData.toJSON());
            layer.addLayerToMap();
            for (GeoJsonFeature feature : layer.getFeatures()) {
                GeoJsonPolygonStyle geoJsonPolygonStyle = new GeoJsonPolygonStyle();
                geoJsonPolygonStyle.setFillColor(Color.argb(5, 0, 255, 5));
                geoJsonPolygonStyle.setStrokeColor(Color.argb(50, 85, 85, 85));
                geoJsonPolygonStyle.setStrokeWidth(3);
                geoJsonPolygonStyle.setZIndex(100);
                feature.setPolygonStyle(geoJsonPolygonStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //MARRON
    public void loadGeoJson4(String region, String zona) {
        try {
            InputStream is = getAssets().open(region + "/" + "bosqueproduccionpermanante" + ".geojson");
            GeoJSONObject geoJsonData = GeoJSON.parse(is);
            is.close();
            GeoJsonLayer layer = new GeoJsonLayer(mMap, geoJsonData.toJSON());
            layer.addLayerToMap();
            for (GeoJsonFeature feature : layer.getFeatures()) {
                GeoJsonPolygonStyle geoJsonPolygonStyle = new GeoJsonPolygonStyle();
                geoJsonPolygonStyle.setFillColor(Color.argb(110, 110, 24, 0));
                geoJsonPolygonStyle.setStrokeColor(Color.argb(100, 255, 0, 0));
                geoJsonPolygonStyle.setStrokeWidth(3);
                geoJsonPolygonStyle.setZIndex(100);
                feature.setPolygonStyle(geoJsonPolygonStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //MORADO
    public void loadGeoJson5(String region, String zona) {
        try {
            InputStream is = getAssets().open(region + "/" + "comunidadescampesinas" + ".geojson");
            GeoJSONObject geoJsonData = GeoJSON.parse(is);
            is.close();
            GeoJsonLayer layer = new GeoJsonLayer(mMap, geoJsonData.toJSON());
            layer.addLayerToMap();
            for (GeoJsonFeature feature : layer.getFeatures()) {
                GeoJsonPolygonStyle geoJsonPolygonStyle = new GeoJsonPolygonStyle();
                geoJsonPolygonStyle.setFillColor(Color.argb(100, 0, 0, 255));
                geoJsonPolygonStyle.setStrokeColor(Color.argb(100, 255, 0, 0));
                geoJsonPolygonStyle.setStrokeWidth(3);
                geoJsonPolygonStyle.setZIndex(100);
                feature.setPolygonStyle(geoJsonPolygonStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //NARANJA
    public void loadGeoJson6(String region, String zona) {
        try {
            InputStream is = getAssets().open(region + "/" + "comunidadesnativas" + ".geojson");
            GeoJSONObject geoJsonData = GeoJSON.parse(is);
            is.close();
            GeoJsonLayer layer = new GeoJsonLayer(mMap, geoJsonData.toJSON());
            layer.addLayerToMap();
            for (GeoJsonFeature feature : layer.getFeatures()) {
                GeoJsonPolygonStyle geoJsonPolygonStyle = new GeoJsonPolygonStyle();
                geoJsonPolygonStyle.setFillColor(Color.argb(50, 255, 80, 5));
                geoJsonPolygonStyle.setStrokeColor(Color.argb(50, 255, 120, 5));
                geoJsonPolygonStyle.setStrokeWidth(3);
                geoJsonPolygonStyle.setZIndex(100);

                feature.setPolygonStyle(geoJsonPolygonStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //AMARILLO
    public void loadGeoJson7(String region, String zona) {
        try {
            InputStream is = getAssets().open(region + "/" + "concesioncastana" + ".geojson");
            GeoJSONObject geoJsonData = GeoJSON.parse(is);
            is.close();
            GeoJsonLayer layer = new GeoJsonLayer(mMap, geoJsonData.toJSON());
            layer.addLayerToMap();
            for (GeoJsonFeature feature : layer.getFeatures()) {
                GeoJsonPolygonStyle geoJsonPolygonStyle = new GeoJsonPolygonStyle();
                geoJsonPolygonStyle.setFillColor(Color.argb(100, 255, 215, 5));
                geoJsonPolygonStyle.setStrokeColor(Color.argb(40, 255, 80, 5));
                geoJsonPolygonStyle.setStrokeWidth(3);
                geoJsonPolygonStyle.setZIndex(100);

                feature.setPolygonStyle(geoJsonPolygonStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //AMARILLO
    public void loadGeoJson8(String region, String zona) {
        try {
            InputStream is = getAssets().open(region + "/" + "concesionconservacion" + ".geojson");
            GeoJSONObject geoJsonData = GeoJSON.parse(is);
            is.close();
            GeoJsonLayer layer = new GeoJsonLayer(mMap, geoJsonData.toJSON());
            layer.addLayerToMap();
            for (GeoJsonFeature feature : layer.getFeatures()) {
                GeoJsonPolygonStyle geoJsonPolygonStyle = new GeoJsonPolygonStyle();
                geoJsonPolygonStyle.setFillColor(Color.argb(100, 255, 215, 5));
                geoJsonPolygonStyle.setStrokeColor(Color.argb(40, 255, 80, 5));
                geoJsonPolygonStyle.setStrokeWidth(3);
                geoJsonPolygonStyle.setZIndex(100);

                feature.setPolygonStyle(geoJsonPolygonStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //AMARILLO
    public void loadGeoJson9(String region, String zona) {
        try {
            InputStream is = getAssets().open(region + "/" + "concesionecoturismo" + ".geojson");
            GeoJSONObject geoJsonData = GeoJSON.parse(is);
            is.close();
            GeoJsonLayer layer = new GeoJsonLayer(mMap, geoJsonData.toJSON());
            layer.addLayerToMap();
            for (GeoJsonFeature feature : layer.getFeatures()) {
                GeoJsonPolygonStyle geoJsonPolygonStyle = new GeoJsonPolygonStyle();
                geoJsonPolygonStyle.setFillColor(Color.argb(100, 255, 215, 5));
                geoJsonPolygonStyle.setStrokeColor(Color.argb(40, 255, 80, 5));
                geoJsonPolygonStyle.setStrokeWidth(3);
                geoJsonPolygonStyle.setZIndex(100);

                feature.setPolygonStyle(geoJsonPolygonStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //AMARILLO
    public void loadGeoJson10(String region, String zona) {
        try {
            InputStream is = getAssets().open(region + "/" + "concesionmaderable" + ".geojson");
            GeoJSONObject geoJsonData = GeoJSON.parse(is);
            is.close();
            GeoJsonLayer layer = new GeoJsonLayer(mMap, geoJsonData.toJSON());
            layer.addLayerToMap();
            for (GeoJsonFeature feature : layer.getFeatures()) {
                GeoJsonPolygonStyle geoJsonPolygonStyle = new GeoJsonPolygonStyle();
                geoJsonPolygonStyle.setFillColor(Color.argb(100, 255, 215, 5));
                geoJsonPolygonStyle.setStrokeColor(Color.argb(40, 255, 80, 5));
                geoJsonPolygonStyle.setStrokeWidth(3);
                geoJsonPolygonStyle.setZIndex(100);

                feature.setPolygonStyle(geoJsonPolygonStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //AMARILLO
    public void loadGeoJson11(String region, String zona) {
        try {
            InputStream is = getAssets().open(region + "/" + "concesionmanejofaunasilvestre" + ".geojson");
            GeoJSONObject geoJsonData = GeoJSON.parse(is);
            is.close();
            GeoJsonLayer layer = new GeoJsonLayer(mMap, geoJsonData.toJSON());
            layer.addLayerToMap();
            for (GeoJsonFeature feature : layer.getFeatures()) {
                GeoJsonPolygonStyle geoJsonPolygonStyle = new GeoJsonPolygonStyle();
                geoJsonPolygonStyle.setFillColor(Color.argb(100, 255, 215, 5));
                geoJsonPolygonStyle.setStrokeColor(Color.argb(40, 255, 80, 5));
                geoJsonPolygonStyle.setStrokeWidth(3);
                geoJsonPolygonStyle.setZIndex(100);

                feature.setPolygonStyle(geoJsonPolygonStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //AMARILLO
    public void loadGeoJson12(String region, String zona) {
        try {
            InputStream is = getAssets().open(region + "/" + "concesionreforestacion" + ".geojson");
            GeoJSONObject geoJsonData = GeoJSON.parse(is);
            is.close();
            GeoJsonLayer layer = new GeoJsonLayer(mMap, geoJsonData.toJSON());
            layer.addLayerToMap();
            for (GeoJsonFeature feature : layer.getFeatures()) {
                GeoJsonPolygonStyle geoJsonPolygonStyle = new GeoJsonPolygonStyle();
                geoJsonPolygonStyle.setFillColor(Color.argb(100, 255, 215, 5));
                geoJsonPolygonStyle.setStrokeColor(Color.argb(40, 255, 80, 5));
                geoJsonPolygonStyle.setStrokeWidth(3);
                geoJsonPolygonStyle.setZIndex(100);

                feature.setPolygonStyle(geoJsonPolygonStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //CELESTE
    public void loadGeoJson13(String region, String zona) {
        try {
            InputStream is = getAssets().open(region + "/" + "humedales" + ".geojson");
            GeoJSONObject geoJsonData = GeoJSON.parse(is);
            is.close();
            GeoJsonLayer layer = new GeoJsonLayer(mMap, geoJsonData.toJSON());
            layer.addLayerToMap();
            for (GeoJsonFeature feature : layer.getFeatures()) {
                GeoJsonPolygonStyle geoJsonPolygonStyle = new GeoJsonPolygonStyle();
                geoJsonPolygonStyle.setFillColor(Color.rgb(161, 207, 255));
                geoJsonPolygonStyle.setStrokeColor(Color.argb(40, 255, 80, 5));
                geoJsonPolygonStyle.setStrokeWidth(3);
                geoJsonPolygonStyle.setZIndex(100);

                feature.setPolygonStyle(geoJsonPolygonStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ROJO
//  public void loadGeoJson14(String region, String zona) {
//
//    //TODO: ORIGINAL
//    try {
//      InputStream is = getAssets().open(region + "/" + zona + ".geojson");
//      GeoJSONObject geoJsonData = GeoJSON.parse(is);
//      is.close();
//      GeoJsonLayer layer = new GeoJsonLayer(mMap, geoJsonData.toJSON());
//      layer.addLayerToMap();
//      for (GeoJsonFeature feature : layer.getFeatures()) {
//        GeoJsonPolygonStyle geoJsonPolygonStyle = new GeoJsonPolygonStyle();
//        geoJsonPolygonStyle.setFillColor(Color.argb(100, 0, 0, 255));
//        geoJsonPolygonStyle.setStrokeColor(Color.argb(100, 255, 0, 0));
//        geoJsonPolygonStyle.setStrokeWidth(3);
//        geoJsonPolygonStyle.setZIndex(100);
//        feature.setPolygonStyle(geoJsonPolygonStyle);
//      }
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
    public void loadGeoJson144(String region, String zona) {
        try {
            InputStream is = getAssets().open(region + "/" + "perdida2012" + ".geojson");
            GeoJSONObject geoJsonData = GeoJSON.parse(is);
            is.close();
            GeoJsonLayer layer = new GeoJsonLayer(mMap, geoJsonData.toJSON());
            layer.addLayerToMap();
            for (GeoJsonFeature feature : layer.getFeatures()) {
                GeoJsonPolygonStyle geoJsonPolygonStyle = new GeoJsonPolygonStyle();
                geoJsonPolygonStyle.setFillColor(Color.rgb(255, 10, 10));
                geoJsonPolygonStyle.setStrokeColor(Color.argb(40, 20, 10, 225));
                geoJsonPolygonStyle.setStrokeWidth(3);
                geoJsonPolygonStyle.setZIndex(100);

                feature.setPolygonStyle(geoJsonPolygonStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ROJO
    public void loadGeoJson15(String region, String zona) {
        try {
            InputStream is = getAssets().open(region + "/" + "perdida2013" + ".geojson");
            GeoJSONObject geoJsonData = GeoJSON.parse(is);
            is.close();
            GeoJsonLayer layer = new GeoJsonLayer(mMap, geoJsonData.toJSON());
            layer.addLayerToMap();
            for (GeoJsonFeature feature : layer.getFeatures()) {
                GeoJsonPolygonStyle geoJsonPolygonStyle = new GeoJsonPolygonStyle();
                geoJsonPolygonStyle.setFillColor(Color.rgb(255, 10, 10));
                geoJsonPolygonStyle.setStrokeColor(Color.argb(40, 20, 10, 225));
                geoJsonPolygonStyle.setStrokeWidth(3);
                geoJsonPolygonStyle.setZIndex(100);

                feature.setPolygonStyle(geoJsonPolygonStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ROJO
    public void loadGeoJson16(String region, String zona) {
        try {
            InputStream is = getAssets().open(region + "/" + "perdida2014" + ".geojson");
            GeoJSONObject geoJsonData = GeoJSON.parse(is);
            is.close();
            GeoJsonLayer layer = new GeoJsonLayer(mMap, geoJsonData.toJSON());
            layer.addLayerToMap();
            for (GeoJsonFeature feature : layer.getFeatures()) {
                GeoJsonPolygonStyle geoJsonPolygonStyle = new GeoJsonPolygonStyle();
                geoJsonPolygonStyle.setFillColor(Color.rgb(255, 10, 10));
                geoJsonPolygonStyle.setStrokeColor(Color.argb(40, 20, 10, 225));
                geoJsonPolygonStyle.setStrokeWidth(3);
                geoJsonPolygonStyle.setZIndex(100);

                feature.setPolygonStyle(geoJsonPolygonStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ROJO
    public void loadGeoJson17(String region, String zona) {
        try {
            InputStream is = getAssets().open(region + "/" + "perdida2015" + ".geojson");
            GeoJSONObject geoJsonData = GeoJSON.parse(is);
            is.close();
            GeoJsonLayer layer = new GeoJsonLayer(mMap, geoJsonData.toJSON());
            layer.addLayerToMap();
            for (GeoJsonFeature feature : layer.getFeatures()) {
                GeoJsonPolygonStyle geoJsonPolygonStyle = new GeoJsonPolygonStyle();
                geoJsonPolygonStyle.setFillColor(Color.rgb(255, 10, 10));
                geoJsonPolygonStyle.setStrokeColor(Color.argb(40, 20, 10, 225));
                geoJsonPolygonStyle.setStrokeWidth(3);
                geoJsonPolygonStyle.setZIndex(100);

                feature.setPolygonStyle(geoJsonPolygonStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ROJO
    public void loadGeoJson18(String region, String zona) {
        try {
            InputStream is = getAssets().open(region + "/" + "perdida2016" + ".geojson");
            GeoJSONObject geoJsonData = GeoJSON.parse(is);
            is.close();
            GeoJsonLayer layer = new GeoJsonLayer(mMap, geoJsonData.toJSON());
            layer.addLayerToMap();
            for (GeoJsonFeature feature : layer.getFeatures()) {
                GeoJsonPolygonStyle geoJsonPolygonStyle = new GeoJsonPolygonStyle();
                geoJsonPolygonStyle.setFillColor(Color.rgb(255, 10, 10));
                geoJsonPolygonStyle.setStrokeColor(Color.argb(40, 20, 10, 225));
                geoJsonPolygonStyle.setStrokeWidth(3);
                geoJsonPolygonStyle.setZIndex(100);

                feature.setPolygonStyle(geoJsonPolygonStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // BLANCO
    public void loadGeoJson19(String region, String zona) {
        try {
            InputStream is = getAssets().open(region + "/" + "reservascomunales" + ".geojson");
            GeoJSONObject geoJsonData = GeoJSON.parse(is);
            is.close();
            GeoJsonLayer layer = new GeoJsonLayer(mMap, geoJsonData.toJSON());
            layer.addLayerToMap();
            for (GeoJsonFeature feature : layer.getFeatures()) {
                GeoJsonPolygonStyle geoJsonPolygonStyle = new GeoJsonPolygonStyle();
                geoJsonPolygonStyle.setFillColor(Color.rgb(255, 255, 255));
                geoJsonPolygonStyle.setStrokeColor(Color.argb(40, 20, 10, 225));
                geoJsonPolygonStyle.setStrokeWidth(3);
                geoJsonPolygonStyle.setZIndex(100);

                feature.setPolygonStyle(geoJsonPolygonStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // BLANCO
    public void loadGeoJson20(String region, String zona) {
        try {
            InputStream is = getAssets().open(region + "/" + "reservasterritoriales" + ".geojson");
            GeoJSONObject geoJsonData = GeoJSON.parse(is);
            is.close();
            GeoJsonLayer layer = new GeoJsonLayer(mMap, geoJsonData.toJSON());
            layer.addLayerToMap();
            for (GeoJsonFeature feature : layer.getFeatures()) {
                GeoJsonPolygonStyle geoJsonPolygonStyle = new GeoJsonPolygonStyle();
                geoJsonPolygonStyle.setFillColor(Color.rgb(255, 255, 255));
                geoJsonPolygonStyle.setStrokeColor(Color.argb(40, 20, 10, 225));
                geoJsonPolygonStyle.setStrokeWidth(3);
                geoJsonPolygonStyle.setZIndex(100);

                feature.setPolygonStyle(geoJsonPolygonStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressLint("RestrictedApi")
    private void setUpButtons() {
        final FloatingActionButton btnSatellite = (FloatingActionButton) findViewById(R.id.btnSatellite);
        btnSatellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(mMap.getMapType() == GoogleMap.MAP_TYPE_SATELLITE ? GoogleMap.MAP_TYPE_NORMAL : GoogleMap.MAP_TYPE_SATELLITE);
            }
        });

        //btnSatellite.setVisibility(drawingOption.getEnableSatelliteView() ? View.VISIBLE : View.GONE);
        /**/

        final FloatingActionButton btnMarker = (FloatingActionButton) findViewById(R.id.btnGPS);
        btnMarker.setVisibility(View.INVISIBLE);
        btnMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                    Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).draggable(true));
                    marker.setZIndex(102);
                    marker.setTag(latLng);
                    markerList.add(marker);
                    points.add(latLng);
                    areaTextView.setText(getString(R.string.area_label) + String.format(Locale.getDefault(), "%.2f", (CalUtils.getArea(points)) / 10000) + " ha");
                    PolygonOptions polygonOptions = new PolygonOptions();
                    polygonOptions.fillColor(Color.argb(100, 85, 85, 85));
                    polygonOptions.strokeColor(Color.argb(50, 85, 85, 85));
                    polygonOptions.strokeWidth(3);
                    polygonOptions.zIndex(101);
                    polygonOptions.addAll(points);
                    polygon = mMap.addPolygon(polygonOptions);
                    PositionList positionList = new PositionList();
                    for (LatLng lng : points) {
                        Position position = new Position(lng.latitude, lng.longitude);
                        positionList.addPosition(position);
                    }
                    positionList.addPosition(new Position(points.get(0).latitude, points.get(0).longitude));
                    Ring ring = new Ring();
                    ring.setPositions(positionList);
                    Polygon polygon = new Polygon(ring);
                    Feature feature = new Feature(polygon);
                    Log.i("feature", "" + feature.getProperties());
                    JSONObject jsonObject = feature.toJSON();
                    Log.i("GeoJson: ", "" + jsonObject);
                    FeatureCollection featureCollection = new FeatureCollection();
                    featureCollection.addFeature(feature);
                    JSON_OBJECT_COLLECTION = featureCollection.toJSON();
                    //cargaDatos();
                    Log.i("GeoJsonCollection: ", "" + JSON_OBJECT_COLLECTION);
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        AppCompatImageView walk = findViewById(R.id.btnWalk);
        walk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (currentLocation != null) {
                        //TODO:PROBANDO PRECISIÓN
                    //    lengthTextView.setText("Precision: " + currentLocation.getSpeedAccuracyMetersPerSecond());
                      //  float precision = currentLocation.getSpeedAccuracyMetersPerSecond();
                    }
                    btnMarker.setVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            }
        });

        /**/
        AppCompatImageView savePolygon = findViewById(R.id.savePolygon);
        savePolygon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MapaActivity.this);
                builder.setTitle("App");
                builder.setMessage("¿Desea continuar registrando los datos");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle bundle = new Bundle();
                        Intent intent = new Intent(MapaActivity.this, PhotoActivity.class);
                        intent.putExtra("GeoJson", JSON_OBJECT_COLLECTION.toString());
                        startActivity(intent);
                        finish();
                        Log.i("OK", "Si Clicked");
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        //ELIMIANR POLIGONO
        FloatingActionButton btnUndo = (FloatingActionButton) findViewById(com.github.bkhezry.mapdrawingtools.R.id.btnUndo);
        btnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (points.size() > 0) {
                    Marker marker = markerList.get(markerList.size() - 1);
                    marker.remove();
                    markerList.remove(marker);
                    points.remove(points.size() - 1);
                    if (points.size() > 0) {
                        if (currentDrawingType == DrawingOption.DrawingType.POLYGON) {
                            drawPolygon(points);
                        }
                        else if (drawingOption.getDrawingType() == DrawingOption.DrawingType.POLYLINE) {
                            drawPolyline(points);
                        }
                    }

                }
            }
        });
        AppCompatImageView drawPolygon = findViewById(R.id.drawPolygon);
        drawPolygon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        try {
                            Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).draggable(true));
                            marker.setZIndex(102);
                            marker.setTag(latLng);
                            markerList.add(marker);
                            points.add(latLng);
                            areaTextView.setText(getString(R.string.area_label) + String.format(Locale.getDefault(), "%.2f", (CalUtils.getArea(points)) / 10000) + " ha");
                            PolygonOptions polygonOptions = new PolygonOptions();
                            polygonOptions.fillColor(Color.argb(100, 85, 85, 85));
                            polygonOptions.strokeColor(Color.argb(50, 85, 85, 85));
                            polygonOptions.strokeWidth(3);
                            polygonOptions.zIndex(101);
                            polygonOptions.addAll(points);
                            polygon = mMap.addPolygon(polygonOptions);
                            PositionList positionList = new PositionList();
                            for (LatLng lng : points) {
                                Position position = new Position(lng.latitude, lng.longitude);
                                positionList.addPosition(position);
                            }
                            positionList.addPosition(new Position(points.get(0).latitude, points.get(0).longitude));
                            Ring ring = new Ring();
                            ring.setPositions(positionList);
                            Polygon polygon = new Polygon(ring);
                            Feature feature = new Feature(polygon);
                            Log.i("feature", "" + feature.getProperties());
                            JSONObject jsonObject = feature.toJSON();
                            Log.i("GeoJson: ", "" + jsonObject);
                            FeatureCollection featureCollection = new FeatureCollection();
                            featureCollection.addFeature(feature);
                            JSON_OBJECT_COLLECTION = featureCollection.toJSON();
                            //cargaDatos();
                            Log.i("GeoJsonCollection: ", "" + JSON_OBJECT_COLLECTION);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void drawPolyline(List<LatLng> latLngList) {
        if (polyline != null) {
            polyline.remove();
        }
        PolylineOptions polylineOptions = new PolylineOptions();
//    polylineOptions.color(drawingOption.getStrokeColor());
//    polylineOptions.width(drawingOption.getStrokeWidth());
        polylineOptions.addAll(latLngList);
        polyline = mMap.addPolyline(polylineOptions);
    }

    private void drawPolygon(List<LatLng> latLngList) {
        if (polygon != null) {
            polygon.remove();
        }

        PolygonOptions polygonOptions = new PolygonOptions();
//    polygonOptions.fillColor(drawingOption.getFillColor());
        //polygonOptions.strokeColor(drawingOption.getStrokeColor());
        //polygonOptions.strokeWidth(drawingOption.getStrokeWidth());
        polygonOptions.addAll(latLngList);
        polygon = mMap.addPolygon(polygonOptions);
    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }
    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }
    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);
        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            //buildGoogleApiClient();
                        }
                        //mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }
    private class ErrorHandler implements Action1<Throwable> {
        @Override
        public void call(Throwable throwable) {
            Toast.makeText(MapaActivity.this, "Error occurred.", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Error occurred", throwable);
        }
    }
}
