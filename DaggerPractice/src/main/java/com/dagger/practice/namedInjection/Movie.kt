package com.dagger.practice.namedInjection

import javax.inject.Inject

class Movie  constructor(movieDetails: MovieDetails) {

    var movieDetails: MovieDetails = movieDetails

    fun displayMovie() {
        movieDetails.displayMovieDetails()
    }
}
