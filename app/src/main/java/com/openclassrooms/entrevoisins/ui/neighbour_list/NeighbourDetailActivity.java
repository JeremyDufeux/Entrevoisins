package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.pref.UserPref;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

public class NeighbourDetailActivity extends AppCompatActivity {
    private NeighbourApiService mApiService;
    public static final String BUNDLE_EXTRA_NEIGHBOUR = "BUNDLE_EXTRA_NEIGHBOUR";
    private Neighbour mNeighbour;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.neighbour_detail_picture)
    ImageView mPictureIv;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.neighbour_detail_name)
    TextView mNameTv;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.neighbour_detail_info_name)
    TextView mNameInfoTv;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.neighbour_detail_info_address)
    TextView mAddressInfoTv;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.neighbour_detail_info_phone)
    TextView mPhoneInfoTv;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.neighbour_detail_info_web)
    TextView mWebInfoTv;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.neighbour_detail_about)
    TextView mAboutTv;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.neighbour_detail_add_to_favorites)
    FloatingActionButton mFavoriteFab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);
        ButterKnife.bind(this);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        mApiService = DI.getNeighbourApiService();

        Intent intent = getIntent();
        mNeighbour = (Neighbour) intent.getParcelableExtra(BUNDLE_EXTRA_NEIGHBOUR);


        Glide.with(mPictureIv.getContext())
                .load(mNeighbour.getAvatarUrl())
                .into(mPictureIv);
        mNameTv.setText(mNeighbour.getName());
        mNameInfoTv.setText(mNeighbour.getName());
        mAddressInfoTv.setText(mNeighbour.getAddress());
        mPhoneInfoTv.setText(mNeighbour.getPhoneNumber());
        mWebInfoTv.setText(mNeighbour.getWebUrl());
        mAboutTv.setText(mNeighbour.getAboutMe());

        Boolean isFavourite = DI.getUserPref().favoritesContains(mNeighbour.getId());
        if(isFavourite) {
            mFavoriteFab.setImageResource(R.drawable.ic_star_white_24dp);
            Log.d(TAG, "onCreate: " );
        }

        mFavoriteFab.setOnClickListener(v -> {
            if(!isFavourite) {
                DI.getUserPref().addFavoriteId(mNeighbour.getId());
                mFavoriteFab.setImageResource(R.drawable.ic_star_white_24dp);
            } else {
                DI.getUserPref().removeFavoriteId(mNeighbour.getId());
                mFavoriteFab.setImageResource(R.drawable.ic_star_border_white_24dp);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Used to navigate to this activity
     * @param activity activity from
     * @param neighbour the neighbour to display
     */
    public static void navigate(FragmentActivity activity, Neighbour neighbour) {
        Intent intent = new Intent(activity, NeighbourDetailActivity.class);
        intent.putExtra(BUNDLE_EXTRA_NEIGHBOUR, neighbour);
        ActivityCompat.startActivity(activity, intent, null);
    }
}