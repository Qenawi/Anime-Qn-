package com.panda.ap_anime.anmi_details

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.youtube.player.YouTubeBaseActivity
import com.panda.ap_anime.Constants
import com.panda.ap_anime.R
import com.panda.ap_anime.anmi_details.details_repo.*
import com.panda.ap_anime.c_anmiations_utils.CAnmiation
import kotlinx.android.synthetic.main.anmi_details_activity.*
import qenawi.panda.a_predator.network_Handeler.A_Predator_NWM
import qenawi.panda.a_predator.network_Handeler.A_Predator_NetWorkManger
import qenawi.panda.a_predator.network_Handeler.A_Predator_Throwable
import qenawi.panda.a_predator.network_Handeler.CService_DBase
import java.util.ArrayList
import java.util.HashMap
import android.content.Intent
import android.net.Uri
import android.view.KeyEvent
import okhttp3.Dispatcher

class DetailsActivity : YouTubeBaseActivity() {
    lateinit var cAnmiation: CAnmiation
    lateinit var a_predator_netWorkManger: A_Predator_NetWorkManger
    private var genreAdapter: GenreAdapter? = null
    private var tralersAdapter: TralersAdapter? = null
    private var streamAdapter: StreamAdapter? = null
    internal var Id: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.anmi_details_activity)
        if (intent.extras != null && intent.extras!!.containsKey("AnimeKey")) {
            Id = intent.extras!!.getInt("AnimeKey")
        } else
            return
        initView()
        getData()
    }

    private fun getData() {
        val Headrs = HashMap<String, String>()
        val Body = object : HashMap<String, Any>() {
            init {
                put("api_key", Constants.TMDP_API_KEY)
                put("language", "en")

            }
        }

        // https://api.themoviedb.org/3/movie/166428/videos?api_key=7d1dd3f8f443ecde27c323485a170bdc&language=en-US


        val Url = "https://api.themoviedb.org/3/movie/$Id"
        val UrlVedios = "$Url/videos"
        a_predator_netWorkManger.FetchData(
            Anmi_Details_Response(),
            Headrs,
            Url,
            Body,
            object : A_Predator_NWM.RequistResuiltCallBack {
                override fun <T : CService_DBase> Sucess(data: T) {
                    val anmi_details_response = data as Anmi_Details_Response
                    Glide.with(this@DetailsActivity)
                        .load(Constants.Movies.getImageBaseUrl("w500") + anmi_details_response.backdropPath)
                        .into(imageView)
                    descreption.text = anmi_details_response.overview
                    if ((data as Anmi_Details_Response).genres != null && !(data as Anmi_Details_Response).genres!!.isEmpty())
                        genreAdapter!!.submitList((data as Anmi_Details_Response).genres)
                }

                override fun Faild(error: A_Predator_Throwable) {
                    if (error.GetDoAction())
                        Toast.makeText(this@DetailsActivity, error.aCtion, Toast.LENGTH_SHORT).show()
                    finish()
                }
            })

        a_predator_netWorkManger.FetchData(
            TralerResponse(),
            Headrs,
            UrlVedios,
            Body,
            object : A_Predator_NWM.RequistResuiltCallBack {
                override fun <T : CService_DBase> Sucess(Resposne: T) {
                    val tralerResponse = Resposne as TralerResponse
                    tralersAdapter!!.submitList(tralerResponse.results)
                }

                override fun Faild(error: A_Predator_Throwable) {
                    if (error.GetDoAction())
                        Toast.makeText(this@DetailsActivity, error.aCtion, Toast.LENGTH_SHORT).show()
                }
            })


    }

    private fun initView() {
        cAnmiation = CAnmiation(this, 150)
        a_predator_netWorkManger = A_Predator_NetWorkManger(this, object : A_Predator_NWM.BaseActionHandeler {
            override fun ShowSnackBar(t: String) {

            }

            override fun HandelUnAuthAction() {

            }

            override fun DetacHHandelers() {

            }
        })
        genreAdapter = GenreAdapter()

        tralersAdapter = TralersAdapter(object : TralersAdapter.OnSrelection {
            override fun selection(str: String) {

                val videoId = str
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))
                intent.putExtra("VIDEO_ID", videoId)
                startActivity(intent)
            }
        })

        val nn: ArrayList<StreamObject> = ArrayList()
        nn.add(StreamObject("CimaClup", "720P", "sTeam0"))
        nn.add(StreamObject("KissCartoon", "720P", "sTeam0"))
        nn.add(StreamObject("AnimeLeek", "720P", "sTeam0"))
        nn.add(StreamObject("YouTube", "720P", "sTeam0"))
        streamAdapter = StreamAdapter()
        streamAdapter?.submitList(nn)

        stream_links.layoutManager = LinearLayoutManager(this)
        stream_links.adapter = streamAdapter


        recycler_categ.layoutManager = LinearLayoutManager(this@DetailsActivity, LinearLayoutManager.HORIZONTAL, false)
        recycler_categ.adapter = genreAdapter

        tralers_recycler.layoutManager =
            LinearLayoutManager(this@DetailsActivity, LinearLayoutManager.HORIZONTAL, false)
        tralers_recycler.adapter = tralersAdapter
    }

}
