package com.panda.ap_anime.descover_fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import carbon.widget.RecyclerView;
import com.panda.ap_anime.R;
import com.panda.ap_anime.anmi_details.DetailsActivity;
import com.panda.ap_anime.descover_fragment.data_repo.DescoverResponse;
import com.panda.ap_anime.descover_fragment.data_repo.Result;
import qenawi.panda.a_predator.network_Handeler.A_Predator_NWM;
import qenawi.panda.a_predator.network_Handeler.A_Predator_NetWorkManger;
import qenawi.panda.a_predator.network_Handeler.A_Predator_Throwable;
import qenawi.panda.a_predator.network_Handeler.CService_DBase;
import retrofit2.HttpException;

import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;

public class DescoverFragment extends Fragment {
    public static final String ApiKey = "7d1dd3f8f443ecde27c323485a170bdc";
    public static final String ApiKeyYoutube="AIzaSyAcmzApxYnW0DRoZXPS8ZQav6f5UbMmspM";

    A_Predator_NetWorkManger a_predator_netWorkManger;

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    DiscoverAdapter adapter;
    int page=1;
    boolean is_lastPage = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        init();
        // base Action
    }

    private void init()
    {
        if (DescoverFragment.this.getContext() != null)
            a_predator_netWorkManger = new A_Predator_NetWorkManger(DescoverFragment.this.getContext(), new A_Predator_NWM.BaseActionHandeler() {
                @Override
                public void ShowSnackBar(String t)
                {

                }

                @Override
                public void HandelUnAuthAction() {

                }

                @Override
                public void DetacHHandelers() {

                }
            });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.descover_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        // attach
    }
    @Override
    public void onDetach() {
        super.onDetach();
        //detach
    }

    private void initView()
    {

        LinearLayoutManager layoutManager = new LinearLayoutManager(DescoverFragment.this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DiscoverAdapter(new ArrayList<Result>(), new DiscoverAdapter.OnSrelection()
        {
            @Override
            public void selection(int id)
            {

                Intent intent =new Intent(DescoverFragment.this.getActivity(), DetailsActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("AnimeKey",id);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);

        recyclerView.setPagination(new RecyclerView.Pagination(layoutManager) {
            @Override
            protected boolean isLoading() {
                return swipeRefresh.isRefreshing();
            }

            @Override
            protected boolean isLastPage() {
                return is_lastPage;
            }

            @Override
            protected void loadNextPage()
            {
                swipeRefresh.setRefreshing(true);
                DescoverFragment.this.loadNextPage();

            }
        });

        swipeRefresh.setOnRefreshListener(() ->
        {
            adapter.replaceData(new ArrayList<>());
            page = 1;
            loadNextPage();

        });
        loadNextPage();
    }

    private void loadNextPage() {


        HashMap<String, String> Headrs = new HashMap<String, String>();
        HashMap<String, Object> Body = new HashMap<String, Object>() {
            {
                put("api_key", ApiKey);
                put("language", "en");
                put("sort_by", "popularity.desc");
                put("with_genres", 16);
                put("page", page);

            }
        };
        String Url = "https://api.themoviedb.org/3/discover/movie";
        a_predator_netWorkManger.FetchData(new DescoverResponse(), Headrs, Url, Body, new A_Predator_NWM.RequistResuiltCallBack()
        {
            @Override
            public <T extends CService_DBase> void Sucess(T Resposne) {
                DescoverResponse data = (DescoverResponse) Resposne;
                if (!data.getResults().isEmpty())
                {
                    if (page > 1)
                        adapter.updateData((ArrayList<Result>)data.getResults());
                    else adapter.replaceData(data.getResults());

                    page++;
                } else
                {
                    is_lastPage = true;
                }

                swipeRefresh.setRefreshing(false);
            }

            @Override
            public void Faild(A_Predator_Throwable error)
            {
                is_lastPage = true;
                error.printStackTrace();
                if (error.GetDoAction())
                {
                    Toast.makeText(DescoverFragment.this.getContext(),error.getACtion(),Toast.LENGTH_SHORT).show();
                }
                swipeRefresh.setRefreshing(false);
            }
        });

    }







}
