object Dependencies {
    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val material by lazy { "com.google.android.material:material:${Versions.material}" }
    val lifecycleRuntime by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntime}" }
    val activityCompose by lazy { "androidx.activity:activity-compose:${Versions.activityCompose}" }
    val composeBom by lazy { "androidx.compose:compose-bom:${Versions.composeBom}" }
    val androidxUi by lazy { "androidx.compose.ui:ui" }
    val androidxUiGraphics by lazy { "androidx.compose.ui:ui-graphics" }
    val androidxUiTooling by lazy { "androidx.compose.ui:ui-tooling" }
    val androidxUiToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview" }
    val androidxUiTestManifest by lazy { "androidx.compose.ui:ui-test-manifest" }
    val androidxUiTestJUnit4 by lazy { "androidx.compose.ui:ui-test-junit4" }
    val androidxMaterial by lazy { "androidx.compose.material3:material3" }
    val junit by lazy { "junit:junit:${Versions.junit}" }
    val androidxJunit by lazy { "androidx.test.ext:junit:${Versions.androidxJunit}" }
    val androidxEspressoCore by lazy { "androidx.test.espresso:espresso-core:${Versions.androidxEspressoCore}" }

    val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
    val hiltAndroidCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hilt}" }
    val hiltCompiler by lazy { "androidx.hilt:hilt-compiler:${Versions.hiltCompiler}" }

    val navigationCompose by lazy { "androidx.navigation:navigation-compose:${Versions.navigationCompose}" }
    val hiltNavigationCompose by lazy { "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}" }

    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val okhttp by lazy { "com.squareup.okhttp3:okhttp:${Versions.okhttp}" }
    val gsonConverter by lazy { "com.squareup.retrofit2:converter-gson:${Versions.gsonConverter}" }
    val moshi by lazy { "com.squareup.moshi:moshi-kotlin:${Versions.moshi}" }
    val moshiConverter by lazy { "com.squareup.retrofit2:converter-moshi:${Versions.moshiConverter}" }
    val loggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}" }

    val coroutinesCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}" }
    val coroutinesAndroid by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}" }

    val splashScreen by lazy { "androidx.core:core-splashscreen:${Versions.splashScreen}" }

    val coil by lazy { "io.coil-kt:coil-compose:${Versions.coil}" }
}

object Modules {
    const val utilities = ":utilities"
}
