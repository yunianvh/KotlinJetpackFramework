#<基本指令>
-optimizationpasses 5
-dontskipnonpubliclibraryclassmembers
-optimizations !code/simplification/cast,!field/*,!class/merging/*
-keepattributes *Annotation*,InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
#记录生成的日志数据,gradle build时在本项目根目录输出apk 包内所有 class 的内部结构
-dump class_files.txt
#未混淆的类和成员
-printseeds seeds.txt
#列出从 apk 中删除的代码
-printusage unused.txt
#混淆前后的映射
-printmapping mapping.txt
#</基本指令>

#<基础>
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep class android.support.** {*;}
#</基础>

#<view相关>
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * {
   public void *(android.view.View);
}
#</view相关>

#<Serializable、Parcelable>
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep public class * implements java.io.Serializable {*;}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
#</Serializable、Parcelable>

#<R文件>
-keep class **.R$* {
 *;
}
#</R文件>

#<enum>
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#</enum>

#<natvie>
-keepclasseswithmembernames class * {
    native <methods>;
}
#</natvie>

#---------------------------------基本指令以及一些固定不混淆的代码--结束-----------



# -----------------------------------ViewBinding 混淆处理 start----------------------------------------
# -----------------------------------ViewBinding 混淆处理 start----------------------------------------
# -----------------------------------ViewBinding 混淆处理 start----------------------------------------
# 如果不添加以下混淆，在开启混淆时，BaseActivity和BaseFragment的val method = clazz2.getMethod("inflate", LayoutInflater::class.java)会抛出以下错误
# Caused by: java.lang.NoSuchMethodException: inflate [class android.view.LayoutInflater]
-keepclassmembers class * implements androidx.viewbinding.ViewBinding {
  public static * inflate(android.view.LayoutInflater);
}
# -----------------------------------ViewBinding 混淆处理 end----------------------------------------
# -----------------------------------ViewBinding 混淆处理 end----------------------------------------
# -----------------------------------ViewBinding 混淆处理 end----------------------------------------



# -----------------------EventBus混淆处理  start-------------------------------------------------------
# -----------------------EventBus混淆处理  start-------------------------------------------------------
# -----------------------EventBus混淆处理  start-------------------------------------------------------
# 开启eventbus混淆
# 如果不开启会抛出以下错误
# Subscriber class com.obtjusha.app.activity.SplashActivity and its super classes have no public methods with the @Subscribe annotation
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}
# -----------------------EventBus混淆处理  end-------------------------------------------------------
# -----------------------EventBus混淆处理  end-------------------------------------------------------
# -----------------------EventBus混淆处理  end-------------------------------------------------------

# ---------Retrofit混淆方法---------------
# ---------Retrofit混淆方法---------------
# ---------Retrofit混淆方法---------------
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
# ---------Retrofit混淆方法---------------
# ---------Retrofit混淆方法---------------
# ---------Retrofit混淆方法---------------


# -------------------------------ARouter 混淆处理 start------------------------------------------------
# -------------------------------ARouter 混淆处理 start------------------------------------------------
# -------------------------------ARouter 混淆处理 start------------------------------------------------
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep public class com.alibaba.android.arouter.facade.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}

# 如果使用了 byType 的方式获取 Service，需添加下面规则，保护接口
-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider

# 如果使用了 单类注入，即不定义接口实现 IProvider，需添加下面规则，保护实现
# -keep class * implements com.alibaba
# -------------------------------ARouter 混淆处理 end------------------------------------------------
# -------------------------------ARouter 混淆处理 end------------------------------------------------
# -------------------------------ARouter 混淆处理 end------------------------------------------------
