package com.panda.ap_anime

/**
 * Created by Andorid-win on 7/18/2018.
 */

object Constants {
    val BASE_URL = "https://api.themoviedb.org/3/"
    val MOVIES_TABLE_NAME = "movies"
    val MOVIES_TABLE_NAME_DETAILS = "moviesdetails"
    val DATABASE_NAME = "StackOverflow.db"
    val TMDP_API_KEY = "7d1dd3f8f443ecde27c323485a170bdc"
    val CONST_SEARCH_TEST_TITLE = "transformers"
    val INVALID_INDEX = "Invalid index"
    val UNSUPPORTED_OPERATION = "Unsupported operation"
    val QUESTIONS_END_POINT = "questions?site=stackoverflow"
    val QUESTIONS_END_POINT_QUERY = "tagged"

    object Movies {
        private val ImageBaseUrl = "https://image.tmdb.org/t/p/"
        val PopularURL = "movie/popular"
        val Top_RatedURL = "movie/top_rated"
        val Movie_Detail = "movie/"

        fun getImageBaseUrl(RequiredWidth: String): String {
            return "$ImageBaseUrl$RequiredWidth/"
        }

        fun getMovieDetailUrl(MovieID: Int): String {
            return Movie_Detail + MovieID.toString()
        }
    }

    object BundleFlags {
        val MOVIE_ID = "MOVIE_ID"
    }
}
