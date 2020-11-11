package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourDetailActivity extends AppCompatActivity {

    @BindView(R.id.neighbour_detail_picture)
    ImageView mPictureIv;
    @BindView(R.id.neighbour_detail_name)
    TextView mNameTv;
    @BindView(R.id.neighbour_detail_info_name)
    TextView mNameInfoTv;
    @BindView(R.id.neighbour_detail_info_address)
    TextView mAddressInfoTv;
    @BindView(R.id.neighbour_detail_info_phone)
    TextView mPhoneInfoTv;
    @BindView(R.id.neighbour_detail_info_web)
    TextView mWebInfoTv;
    @BindView(R.id.neighbour_detail_about)
    TextView mAboutTv;

    public static final String BUNDLE_EXTRA_NEIGHBOUR = "BUNDLE_EXTRA_NEIGHBOUR";

    private Neighbour mNeighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        mNeighbour = (Neighbour) intent.getParcelableExtra(BUNDLE_EXTRA_NEIGHBOUR);
        Log.d("ContentValues", "onCreate: " + mNeighbour.getName());

        Glide.with(mPictureIv.getContext())
                .load(mNeighbour.getAvatarUrl())
                .into(mPictureIv);
        mNameTv.setText(mNeighbour.getName());
        mNameInfoTv.setText(mNeighbour.getName());
        mAddressInfoTv.setText(mNeighbour.getAddress());
        mPhoneInfoTv.setText(mNeighbour.getPhoneNumber());
        mWebInfoTv.setText(mNeighbour.getWebUrl());
        mAboutTv.setText(mNeighbour.getAboutMe());
    }
}