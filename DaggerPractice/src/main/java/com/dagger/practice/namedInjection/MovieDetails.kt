package com.dagger.practice.namedInjection

import android.util.Log
import javax.inject.Inject
import javax.inject.Named

private const val TAG = "MovieDetails"
class MovieDetails ( type:String, actor:String) {

    var type:String = type
    var actor:String = actor

        fun displayMovieDetails(){
          Log.d(TAG,"Movie details: Movie Type is $type and actor in movie is $actor")
         }
}