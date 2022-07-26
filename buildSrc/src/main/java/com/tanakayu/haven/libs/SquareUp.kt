package com.tanakayu.haven.libs

object SquareUp {
    private const val retrofitVersion = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    const val retrofitAdapterRxjava2 = "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"
    const val retrofitConverterScalars = "com.squareup.retrofit2:converter-scalars:$retrofitVersion"

    private const val okHttpVersion = "4.6.0"
    const val okHttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"

    private const val loggingInterceptorVersion = "4.6.0"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$loggingInterceptorVersion"

    private const val picassoVersion = "2.71828"
    const val picasso = "com.squareup.picasso:picasso:$picassoVersion"
}