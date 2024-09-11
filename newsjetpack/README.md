#### steps

- create buildSrc directory, create build.gradle.kts and create 2 files Dependencies and versions
- update values in app gradle file in the dependencies section
- create module utilitites, and update dependencies as above
- create object Module {} in Dependencies file
- add this to app's gradle file:
    ```
    implementation(project(Modules.utilities))
    ```
- create `isInternetConnected` function in utilities
- integrate hilt
- annotate NewsApplication with `@HiltAndroidApp`
- annotate MainActivity with `@AndroidEntryPoint`

- for navigation, we are adding new dependency `hiltNavigationCompose`
- this is added because we need to use ViewModel annotated with `@HiltViewModel` inside compose nav host 
- create `navigation` and `screens` folders and write code for the NavHost
- create `repository` and `viewmodel` packages
- create `NewsViewModel` with appropriate initialization
- then create param in `HomeScreen` composable, to use the viewModel
- add dependencies for retrofit
- then create `NewsRepository`
- create `data/api`, `data/datasource`, `data/entity`
