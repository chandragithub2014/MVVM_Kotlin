package com.dagger.practice.namedInjection

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class MovieModule(movieType:String,movieActor:String){
    var movieType:String = movieType
    var movieActor:String = movieActor

    @Provides
    @Named("type")
    fun provideMovieType():String = movieType

    @Provides
     @Named("actor")
    fun provideMovieActor():String = movieActor

   @Provides
    fun provideMovieDetails():MovieDetails =  MovieDetails(movieType,movieActor)


    @Provides
    fun provideMovie(movieDetails: MovieDetails):Movie = Movie(movieDetails)


}