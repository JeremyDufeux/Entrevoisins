package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.RemoveNeighbourFromFavoritesEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.pref.UserPref;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {
    private NeighbourApiService mApiService;
    private List<Neighbour> mFavoritesNeighbours;
    private RecyclerView mRecyclerView;
    private MyFavoritesNeighbourRecyclerViewAdapter mAdapter;

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return view;
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {
        List<Neighbour> neighbours = mApiService.getNeighbours();
        mFavoritesNeighbours = new ArrayList<>();
        for(Neighbour neighbour : neighbours){
            if(UserPref.favoritesContains(neighbour.getId())) {
                mFavoritesNeighbours.add(neighbour);
            }
        }
        mAdapter = new MyFavoritesNeighbourRecyclerViewAdapter(mFavoritesNeighbours);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Unfavorite if the user clicks on a star button
     * @param event Neighbour
     */
    @Subscribe
    public void onRemoveNeighbourFromFavoritesEvent(RemoveNeighbourFromFavoritesEvent event) {
        UserPref.removeFavoriteId(event.neighbour.getId());
        initList();
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event Neighbour
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        initList();
    }

}