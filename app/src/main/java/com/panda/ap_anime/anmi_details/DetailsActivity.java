package com.panda.ap_anime.anmi_details;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import carbon.widget.ImageView;
import carbon.widget.RecyclerView;
import carbon.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.panda.ap_anime.Constants;
import com.panda.ap_anime.R;
import com.panda.ap_anime.anmi_details.details_repo.*;
import com.panda.ap_anime.c_anmiations_utils.CAnmiation;
import qenawi.panda.a_predator.network_Handeler.A_Predator_NWM;
import qenawi.panda.a_predator.network_Handeler.A_Predator_NetWorkManger;
import qenawi.panda.a_predator.network_Handeler.A_Predator_Throwable;
import qenawi.panda.a_predator.network_Handeler.CService_DBase;

import java.util.ArrayList;
import java.util.HashMap;

import static com.panda.ap_anime.descover_fragment.DescoverFragment.ApiKey;

public class DetailsActivity extends YouTubeBaseActivity {
    CAnmiation cAnmiation;
    A_Predator_NetWorkManger a_predator_netWorkManger;
    @BindView(R.id.imageView)
    ImageView mainImageView;
    @BindView(R.id.descreption)
    TextView descreption;
    @BindView(R.id.recycler_categ)
    RecyclerView category_rv;
    @BindView(R.id.tralers_recycler)
    RecyclerView tralers_recycler;
    @BindView(R.id.stream_links)
    RecyclerView stream_links;
    private GenreAdapter genreAdapter;
    private TralersAdapter tralersAdapter;
    private StreamAdapter streamAdapter;
    int Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anmi_details_activity);
        ButterKnife.bind(this);
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey("AnimeKey")) {
            Id = getIntent().getExtras().getInt("AnimeKey");
        } else return;
        initView();
        getData();
    }

    private void getData() {
        HashMap<String, String> Headrs = new HashMap<String, String>();
        HashMap<String, Object> Body = new HashMap<String, Object>() {
            {
                put("api_key", ApiKey);
                put("language", "en");

            }
        };

        // https://api.themoviedb.org/3/movie/166428/videos?api_key=7d1dd3f8f443ecde27c323485a170bdc&language=en-US


        String Url = "https://api.themoviedb.org/3/movie/" + Id;
        String UrlVedios = Url + "/videos";
        a_predator_netWorkManger.FetchData(new Anmi_Details_Response(), Headrs, Url, Body, new A_Predator_NWM.RequistResuiltCallBack() {
            @Override
            public <T extends CService_DBase> void Sucess(T data) {
                Anmi_Details_Response anmi_details_response = (Anmi_Details_Response) data;
                Glide.with(DetailsActivity.this).load(Constants.Movies.getImageBaseUrl("w500") + (anmi_details_response.getBackdropPath())).into(mainImageView);
                descreption.setText(anmi_details_response.getOverview());
                if (((Anmi_Details_Response) data).getGenres() != null && !((Anmi_Details_Response) data).getGenres().isEmpty())
                    genreAdapter.replaceData(((Anmi_Details_Response) data).getGenres());
            }

            @Override
            public void Faild(A_Predator_Throwable error) {
                if (error.GetDoAction())
                    Toast.makeText(DetailsActivity.this, error.getACtion(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        a_predator_netWorkManger.FetchData(new TralerResponse(), Headrs, UrlVedios, Body, new A_Predator_NWM.RequistResuiltCallBack() {
            @Override
            public <T extends CService_DBase> void Sucess(T Resposne) {
                TralerResponse tralerResponse = (TralerResponse) Resposne;
                tralersAdapter.replaceData(tralerResponse.getResults());
            }

            @Override
            public void Faild(A_Predator_Throwable error) {
                if (error.GetDoAction())
                    Toast.makeText(DetailsActivity.this, error.getACtion(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initView() {
        cAnmiation = new CAnmiation(this, 150);
        a_predator_netWorkManger = new A_Predator_NetWorkManger(this, new A_Predator_NWM.BaseActionHandeler() {
            @Override
            public void ShowSnackBar(String t) {

            }

            @Override
            public void HandelUnAuthAction() {

            }

            @Override
            public void DetacHHandelers() {

            }
        });
        genreAdapter = new GenreAdapter(new ArrayList<Genre>(), id ->
        {

        });
        tralersAdapter = new TralersAdapter(new ArrayList<Trailer_obj>(), id -> {

        });
        streamAdapter = new StreamAdapter(new ArrayList<StreamObject>() {
            {
                add(new StreamObject("CimaClup", "720P", "sTeam0"));
                add(new StreamObject("KissCartoon", "720P", "sTeam0"));
                add(new StreamObject("AnimeLeek", "720P", "sTeam0"));
                add(new StreamObject("YouTube", "720P", "sTeam0"));

            }
        }, id ->
        {

        });

        stream_links.setLayoutManager(new LinearLayoutManager(this));
        stream_links.setHasFixedSize(true);
        stream_links.setAdapter(streamAdapter);


        category_rv.setLayoutManager(new GridLayoutManager(this, 4));
        category_rv.setHasFixedSize(true);
        category_rv.setAdapter(genreAdapter);

        tralers_recycler.setLayoutManager(new LinearLayoutManager(DetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
        tralers_recycler.setHasFixedSize(true);
        tralers_recycler.setAdapter(tralersAdapter);
    }

}
