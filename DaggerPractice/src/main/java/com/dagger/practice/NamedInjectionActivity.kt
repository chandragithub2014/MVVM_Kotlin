package com.dagger.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dagger.practice.namedInjection.DaggerMovieComponent
import com.dagger.practice.namedInjection.Movie
import com.dagger.practice.namedInjection.MovieDetails
import com.dagger.practice.namedInjection.MovieModule
import javax.inject.Inject
import javax.inject.Named

class NamedInjectionActivity : AppCompatActivity() {
    /*
       @Inject
       lateinit var movie: Movie*/
    @Inject
    @field:Named("type")
    lateinit var type: String

    @Inject
    @field:Named("actor")
    lateinit var actor: String

    @Inject
    lateinit var movieDetails: MovieDetails

    @Inject
    lateinit var movie: Movie


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_named_injection)
        // DaggerMoviewComponent
        var component =
            DaggerMovieComponent.builder().movieModule(MovieModule("Action", "Jaq")).build()
        component.inject(this)
        // movieDetails.displayMovieDetails()
        movie.displayMovie()

    }
}