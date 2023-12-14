import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Dependencies {
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltAgp = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"

    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpLoggingIntercepter =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
}

fun DependencyHandler.room() {
    implementation(Dependencies.roomRuntime)
    implementation(Dependencies.roomKtx)
    kapt(Dependencies.roomCompiler)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitGson)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpLoggingIntercepter)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)
}

fun DependencyHandler.myMoudle() {
    implementation(project(":my-module"))
}