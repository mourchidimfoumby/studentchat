import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Dependencies {
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val drawerLayout = "androidx.drawerlayout:drawerlayout:${Versions.drawerLayout}"
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val contraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModel}"
    const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.viewModel}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activity}"

    const val firebase = "com.google.firebase:firebase-bom:${Versions.firebase}"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics"
    const val firebaseAuth = "com.google.firebase:firebase-auth"
    const val firebaseUi = "com.firebaseui:firebase-ui-auth:${Versions.firebaseUi}"
    const val firebaseDatabase = "com.google.firebase:firebase-database"
    const val googleServiceAuth = "com.google.android.gms:play-services-auth:${Versions.googleServiceAuth}"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val koin = "io.insert-koin:koin-android:${Versions.koin}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
//    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
}

fun DependencyHandler.appCompat(){
    implementation(Dependencies.appCompat)
}

fun DependencyHandler.constraintLayout(){
    implementation(Dependencies.contraintLayout)
}

fun DependencyHandler.material(){
    implementation(Dependencies.material)
}

fun DependencyHandler.fragment(){
    implementation(Dependencies.fragmentKtx)
}

fun DependencyHandler.recyclerView(){
    implementation(Dependencies.recyclerView)
}

fun DependencyHandler.viewPager2(){
    implementation(Dependencies.viewPager2)
}

fun DependencyHandler.drawerLayout(){
    implementation(Dependencies.drawerLayout)
}

fun DependencyHandler.viewModel() {
    implementation(Dependencies.viewModelKtx)
    implementation(Dependencies.activityKtx)
}

fun DependencyHandler.liveData(){
    implementation(Dependencies.liveDataKtx)
}

fun DependencyHandler.room() {
    implementation(Dependencies.roomRuntime)
    implementation(Dependencies.roomKtx)
//    kapt(Dependencies.roomCompiler)
}

fun DependencyHandler.firebase() {
    platformImplementation(Dependencies.firebase)
    implementation(Dependencies.firebaseUi)
    implementation(Dependencies.firebaseAnalytics)
    implementation(Dependencies.firebaseDatabase)
}

fun DependencyHandler.firebaseAuth(){
    implementation(platform(Dependencies.firebase))
    implementation(Dependencies.firebaseAuth)
    implementation(Dependencies.googleServiceAuth)
}

fun DependencyHandler.coroutines() {
    implementation(Dependencies.coroutines)
}

fun DependencyHandler.koin(){
    implementation(Dependencies.koin)
}

fun DependencyHandler.data(){
    implementation(project(":data"))
}
fun DependencyHandler.domain(){
    implementation(project(":domain"))
}
fun DependencyHandler.ui(){
    implementation(project(":domain"))
}
