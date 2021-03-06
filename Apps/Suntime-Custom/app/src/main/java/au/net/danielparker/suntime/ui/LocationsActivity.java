package au.net.danielparker.suntime.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import au.net.danielparker.suntime.R;
import au.net.danielparker.suntime.models.Location;

/**
 * Created by danielparker on 29/09/14.
 */

@EActivity(R.layout.activity_locations)
public class LocationsActivity extends ListActivity {
    private ArrayList<Location> listData = new ArrayList<Location>();
    private ArrayAdapter<Location> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialiseUI();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Location selectedItem = (Location) getListView().getItemAtPosition(position);
        showSuntimesForItem(selectedItem);
    }



    private void showSuntimesForItem(Location selectedItem) {
        Intent intent = new Intent(this, SuntimeActivity.class);
        intent.putExtra("location", selectedItem);
        startActivity(intent);
    }

    private void initialiseUI() {
        this.listData = Location.loadLocations(getApplicationContext());
        adapter = new LocationsAdapter(this, this.listData);

        setListAdapter(adapter);
    }

    @Click(R.id.custom_button)
    void onCustomButtonClick() {
        Intent intent = new Intent(this, AddNewLocation_.class);
        startActivity(intent);
    }
}
