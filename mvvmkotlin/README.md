kotlin, mvvm, hilt, retrofit, paging3

- add the dependencies
- enable internet access in Manifest
- if using http api, add this to manifest `android:usesCleartextTraffic="true"`
- create 2 fragments in ui/details and ui/movies path
- then create new Android Resource File and call it navigation (of navigation type)
- add the 2 fragments that we created and link the 2 (drag an arrow from MovieFragment to
  DetailsFragment)
- then add the <fragment/> tag in activity_main and set its name, defaultNavHost=true and navGraph
  accordingly

- then we will setup hilt
- add the dependencies (refer to actual docs for latest
  versions: [docs](https://dagger.dev/hilt/gradle-setup))
- create BaseClass and add @HiltAndroidApp annotation above
- then add this in the AndroidManifest.xml, `android:name=".BaseClass"`
- then add the @AndroidEntryPoint annotation in MainActivity, DetailsFragment and MovieFragment
- create hilt package and inside create object HiltModule and add the annotations

- setup retrofit, add dependencies
- we will be using omdbapi, get the api key
- create a constants file in utils package
- create new package called remote, and create interface MovieInterface (retrofit interface)
- create new pojo package called "data"
- example api url `http://www.omdbapi.com/?apikey=4483d3c6&s=Lucifer&page=1`
- write the code inside HiltModules

- setup paging, add dependency
- create MoviePaging class in ui/movie

- setup data binding
- add `buildFeature { dataBinding=true }` in build.gradle
- also add glide dependency
- create view_holder_movie.xml and edit fragment_movie.xml
- in view_holder_movie.xml, press `alt + Enter`, and create Data binding layout
- this creates the <data> tag, add the movie class inside and u can use this movie in android:text
  of the components
- create ui/BindingAdapters.kt to create a custom load property, that can be used in ImageView (
  check comment in view_holder_movie.xml)
- convert the fragment_movie.xml to Data binding layout too
- in MovieFragment, create var binding and assign it in onCreateView

- create ui/movie/MoviePagingAdapter and write the code
- it is for the recycler view in fragment_movie.xml

- fetching the list
- create MovieViewModel and make changes (create MutatableLiveData, a fn to setQuery in which we
  will postValue)
- in onViewCreated, use binding.movieSearch.set...., to perform search
- create a viewModel instance
- create and implement setRecylerView()
- finally create an observe, to check on the liveData

- implementing movie details page
- create a new package data/moviedetails and create models
- create a new fn in MovieInterface
- create ui/details/MovieDetailsRepository and write the getMovieDetails function
- create the Result.kt and Events.kt files
- in MovieViewModel, create another MutableLiveData and make the getMovieDetails function
- then in HiltModules, inject the movieRepository so that it can be used in MovieViewModel
- change fragment_details into Data binding layout and write the xml
- then go to DetailsFragment, create binding val and rest of standard stuff
- NOTE: to create navArgs, go to navigation.xml, and use the ui to add an argument for DetailsFragment
    - name: imdb_id, type: string, nullable checkbox, default: @null
    - after that, rebuild the project
- go to MoviePagingAdapter and create a var onClick function, and the onClick listeners in
  onBindingView to handle clicking on item
- then go to MovieFragment and add the movieAdapter.onMovieClick..., to move from MovieFragment to
  DetailsFragment