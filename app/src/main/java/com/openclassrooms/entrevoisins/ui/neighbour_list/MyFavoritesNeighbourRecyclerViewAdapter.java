package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.OpenNeighbourDetailsEvent;
import com.openclassrooms.entrevoisins.events.RemoveNeighbourFromFavoritesEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavoritesNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoritesNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighbours;

    public MyFavoritesNeighbourRecyclerViewAdapter(List<Neighbour> items) {
        mNeighbours = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorites_neighbour_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        holder.mRemoveButton.setOnClickListener(v -> {
            neighbour.setFavorite(false);
            EventBus.getDefault().post(new RemoveNeighbourFromFavoritesEvent(neighbour));
        });

        holder.mConstraintLayout.setOnClickListener(v -> EventBus.getDefault().post(new OpenNeighbourDetailsEvent(neighbour)));
    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.favorite_item_list_container)
        public ConstraintLayout mConstraintLayout;
        @BindView(R.id.favorite_item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.favorite_item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.favorite_item_list_remove_favorite_button)
        public ImageButton mRemoveButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
