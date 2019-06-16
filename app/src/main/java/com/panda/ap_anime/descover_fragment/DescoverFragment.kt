package com.panda.ap_anime.descover_fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import carbon.widget.RecyclerView
import com.panda.ap_anime.R
import com.panda.ap_anime.anmi_details.DetailsActivity
import com.panda.ap_anime.descover_fragment.data_repo.DescoverResponse
import com.panda.ap_anime.descover_fragment.data_repo.Result
import qenawi.panda.a_predator.network_Handeler.A_Predator_NWM
import qenawi.panda.a_predator.network_Handeler.A_Predator_NetWorkManger
import qenawi.panda.a_predator.network_Handeler.A_Predator_Throwable
import qenawi.panda.a_predator.network_Handeler.CService_DBase
import retrofit2.HttpException

import java.io.IOException
import java.lang.reflect.UndeclaredThrowableException
import java.net.SocketTimeoutException
import java.util.ArrayList
import java.util.HashMap

class DescoverFragment : Fragment() {

    internal var a_predator_netWorkManger: A_Predator_NetWorkManger? = null
    lateinit var swipeRefresh: SwipeRefreshLayout
    lateinit var recyclerView: RecyclerView
    internal var adapter: DiscoverAdapter? = null
    internal var page = 1
    internal var is_lastPage = false
    private var Cache: ArrayList<Result> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        // base Action
    }

    private fun init() {
        if (this@DescoverFragment.context != null)
            a_predator_netWorkManger =
                A_Predator_NetWorkManger(this@DescoverFragment.context, object : A_Predator_NWM.BaseActionHandeler {
                    override fun ShowSnackBar(t: String) {

                    }

                    override fun HandelUnAuthAction() {

                    }

                    override fun DetacHHandelers() {

                    }
                })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.descover_fragment, container, false)
        ButterKnife.bind(this, view)
        swipeRefresh = view.findViewById(R.id.swipeRefresh)
        recyclerView = view.findViewById(R.id.recyclerView)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        // attach
    }

    override fun onDetach() {
        super.onDetach()
        //detach
    }

    private fun initView() {

        val layoutManager = LinearLayoutManager(this@DescoverFragment.context)
        recyclerView.layoutManager = layoutManager
        adapter = DiscoverAdapter(object : DiscoverAdapter.OnSrelection {
            override fun selection(id: Int) {
                val i = Intent(this@DescoverFragment.activity!!, DetailsActivity::class.java)
                val bundle = Bundle()
                bundle.putInt("AnimeKey", id)
                i.putExtras(bundle)
                startActivity(i)
            }
        })


        recyclerView.adapter = adapter
        recyclerView.setPagination(object : RecyclerView.Pagination(layoutManager) {
            override fun isLoading(): Boolean {
                return swipeRefresh!!.isRefreshing
            }

            override fun isLastPage(): Boolean {
                return is_lastPage
            }

            override fun loadNextPage() {
                swipeRefresh.isRefreshing = true
                this@DescoverFragment.loadNextPage()

            }
        })

        swipeRefresh.setOnRefreshListener {

            Cache.clear()
            page = 1
            loadNextPage()

        }
        loadNextPage()
    }

    private fun loadNextPage() {


        val Headrs = HashMap<String, String>()
        val Body = object : HashMap<String, Any>() {
            init {
                put("api_key", ApiKey)
                put("language", "en")
                put("sort_by", "popularity.desc")
                put("with_genres", 16)
                put("page", page)

            }
        }
        val Url = "https://api.themoviedb.org/3/discover/movie"
        a_predator_netWorkManger?.FetchData(
            DescoverResponse(),
            Headrs,
            Url,
            Body,
            object : A_Predator_NWM.RequistResuiltCallBack {
                override fun <T : CService_DBase> Sucess(Resposne: T) {
                    val data = Resposne as DescoverResponse
                    if (!data.results!!.isEmpty()) {
                        Cache.addAll(data.results!!)
                        adapter?.submitList(Cache)
                        page++
                    } else {
                        is_lastPage = true
                    }

                    swipeRefresh.isRefreshing = false
                }

                override fun Faild(error: A_Predator_Throwable) {
                    is_lastPage = true
                    error.printStackTrace()
                    if (error.GetDoAction()) {
                        Toast.makeText(this@DescoverFragment.context, error.aCtion, Toast.LENGTH_SHORT).show()
                    }
                    swipeRefresh.isRefreshing = false
                }
            })

    }

    companion object {
        val ApiKey = "7d1dd3f8f443ecde27c323485a170bdc"
        val ApiKeyYoutube = "AIzaSyAcmzApxYnW0DRoZXPS8ZQav6f5UbMmspM"
    }


}
