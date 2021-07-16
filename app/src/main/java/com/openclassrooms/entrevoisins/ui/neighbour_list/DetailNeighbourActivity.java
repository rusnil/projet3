package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailNeighbourActivity extends AppCompatActivity {

    @BindView(R.id.avatar)
    ImageView mAvatar;
    @BindView(R.id.nameAvatar)
    TextView mNameAvatar;
    @BindView(R.id.name2)
    TextView mName2;
    @BindView(R.id.adresse)
    TextView mAdresse;
    @BindView(R.id.phone)
    TextView mPhone;
    @BindView(R.id.facebook)
    TextView mFacebook;
    @BindView(R.id.aboutme)
    TextView mAboutMe;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.fab_favorite)
    FloatingActionButton mFavoriteFab;

    private NeighbourApiService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();

        long id = getIntent().getLongExtra("id",1);
        Neighbour neighbour=mApiService.getNeighbour(id);
        mAboutMe.setText(neighbour.getAboutMe());
        mPhone.setText(neighbour.getPhoneNumber());
        mName2.setText(neighbour.getName());
        mNameAvatar.setText(neighbour.getName());
        mAdresse.setText(neighbour.getAddress());
        mFacebook.setText("www.facebook.fr/"+neighbour.getName());
        setFavoristate(neighbour);
        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .centerCrop()
                .into(mAvatar);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailNeighbourActivity.this.finish();
            }
        });

        mFavoriteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!neighbour.isFavorite()) {
                    mApiService.addFavorite(neighbour);
                    mFavoriteFab.setImageResource(R.drawable.ic_star_white_24dp);
                }
                else {
                    mApiService.removeFavorite(neighbour);
                    mFavoriteFab.setImageResource(R.drawable.ic_star_border_white_24dp);
                }
            }
        });

    }
    private void setFavoristate(Neighbour neighbour){
        if (neighbour.isFavorite()){
            mFavoriteFab.setImageResource(R.drawable.ic_star_white_24dp);
        }
        else {
            mFavoriteFab.setImageResource(R.drawable.ic_star_border_white_24dp);
        }
    }

}