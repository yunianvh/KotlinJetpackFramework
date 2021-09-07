# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# 如果要混淆打包，注意retorfit的混淆  和  阿里云ARouter的混淆
# 如果要混淆打包，注意retorfit的混淆  和  阿里云ARouter的混淆
# 如果要混淆打包，注意retorfit的混淆  和  阿里云ARouter的混淆
# 如果要混淆打包，注意retorfit的混淆  和  阿里云ARouter的混淆
# 如果要混淆打包，注意retorfit的混淆  和  阿里云ARouter的混淆
# 如果要混淆打包，注意retorfit的混淆  和  阿里云ARouter的混淆
# 如果要混淆打包，注意retorfit的混淆  和  阿里云ARouter的混淆
## ---------Retrofit混淆方法---------------
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
# OkHttp3
-dontwarn okhttp3.logging.**
-keep class okhttp3.internal.**{*;}
-dontwarn okio.**
# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}

# Gson
-keep class com.google.gson.stream.** { *; }
-keepattributes EnclosingMethod

# Gson
-keep class com.yangchoi.lib_network.**{*;} # 自定义数据模型的bean目录

# ARouter 混淆处理 start
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep public class com.alibaba.android.arouter.facade.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}

# 如果使用了 byType 的方式获取 Service，需添加下面规则，保护接口
-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider

# 如果使用了 单类注入，即不定义接口实现 IProvider，需添加下面规则，保护实现
# -keep class * implements com.alibaba
# 如果要混淆打包，注意retorfit的混淆  和  阿里云ARouter的混淆
# 如果要混淆打包，注意retorfit的混淆  和  阿里云ARouter的混淆
# 如果要混淆打包，注意retorfit的混淆  和  阿里云ARouter的混淆
# 如果要混淆打包，注意retorfit的混淆  和  阿里云ARouter的混淆
# 如果要混淆打包，注意retorfit的混淆  和  阿里云ARouter的混淆
# 如果要混淆打包，注意retorfit的混淆  和  阿里云ARouter的混淆
# 如果要混淆打包，注意retorfit的混淆  和  阿里云ARouter的混淆