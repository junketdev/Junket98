package com.test.junket;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.mapsindoors.mapssdk.MapControl;
import com.mapsindoors.mapssdk.MapsIndoors;
import com.mapsindoors.mapssdk.Venue;
import com.mapsindoors.mapssdk.VenueCollection;

public class IndoorMapActivity extends AppCompatActivity {

    MapControl mMapControl;
    SupportMapFragment mMapFragment;
    GoogleMap mGoogleMap;

    static final LatLng VENUE_LAT_LNG = new LatLng( 57.05813067, 9.95058065 );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indoor_map);

        setupView();
    }

    @Override
    protected void onDestroy() {
        if( mMapControl != null ) {
            mMapControl.onDestroy();
        }
        super.onDestroy();
    }


    private void setupView()
    {
        final FragmentManager fm = getSupportFragmentManager();

        mMapFragment = (SupportMapFragment) fm.findFragmentById( R.id.mapfragment );

        mMapFragment.getMapAsync( mOnMapReadyCallback );
    }

    OnMapReadyCallback mOnMapReadyCallback = new OnMapReadyCallback() {
        @Override
        public void onMapReady( GoogleMap googleMap )
        {
            mGoogleMap = googleMap;
            mGoogleMap.moveCamera( CameraUpdateFactory.newLatLngZoom( VENUE_LAT_LNG, 15.0f ) );

            setupMapsIndoors();
        }
    };

    void setupMapsIndoors()
    {
        final Activity context = this;

        if( (context == null) || (mMapFragment == null) || (mMapFragment.getView() == null) )
        {
            return;
        }

        if( !MapsIndoors.getAPIKey().equalsIgnoreCase( getString( R.string.mi_api_key ) ) )
        {
            MapsIndoors.setAPIKey( getString( R.string.mi_api_key ) );
        }

        mMapControl = new MapControl( context );
        mMapControl.setGoogleMap( mGoogleMap, mMapFragment.getView() );

        mMapControl.init( miError -> {

            if( miError == null )
            {
                final Activity _context = this;
                if( _context != null )
                {
                    mMapControl.selectFloor( 1 );

                    final VenueCollection venues = MapsIndoors.getVenues();

                    if( venues != null )
                    {
                        final Venue currentVenue = venues.getCurrentVenue();

                        if( currentVenue != null )
                        {
                            final LatLngBounds latLngBounds = currentVenue.getLatLngBoundingBox();

                            if( (mGoogleMap != null) && (latLngBounds != null) )
                            {
                                mGoogleMap.animateCamera( CameraUpdateFactory.newLatLngBounds( latLngBounds, 10 ) );
                            }
                        }
                    }
                }
            }
        });
    }
}
