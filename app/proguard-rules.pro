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

#-ignorewarnings
#-dontpreverify  #预校验
-optimizationpasses 5
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  # 混淆时所采用的算法
# 保留所有的本地native方法不被混淆
#-keepclasseswithmembernames class * {
#    native <methods>;
#}
##不混淆Activity 的子类
#-keep public class * extends android.app.Activity
##不混淆Application的子类
#-keep public class * extends android.app.Application
##不混淆Service的子类
#-keep public class * extends android.app.Service
##不混淆BroadcastReceiver的子类
#-keep public class * extends android.content.BroadcastReceiver
##不混淆ContentProvider的子类
#-keep public class * extends android.content.ContentProvider
#-keep public class * extends android.app.backup.BackupAgentHelper
#-keep public class * extends android.preference.Preference
#-keep public class com.android.vending.licensing.ILicensingService
##不混淆View的子类
#-keep public class * extends android.view.View
#-keep public class * extends android.view.View{
#*;
#}
#-keep public class * extends android.app.Fragment
#-keep public class * extends android.preference.Preference
#-keep public class * extends android.support.v4.**
#-keep public class * extends android.support.annotation.**
#-keep public class * extends android.support.v7.**

#
## 枚举类不能被混淆
#-keepclassmembers enum * {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}
## 保留自定义控件(继承自View)不能被混淆
#-keep public class * extends android.view.View {
#    public <init>(android.content.Context);
#    public <init>(android.content.Context, android.util.AttributeSet);
#    public <init>(android.content.Context, android.util.AttributeSet, int);
#    public void set*(***);
#    *** get* ();
#}

## 保留Parcelable序列化的类不能被混淆
#-keep class * implements android.os.Parcelable{
#    public static final android.os.Parcelable$Creator *;
#}

## 保留Serializable 序列化的类不被混淆
#-keepclassmembers class * implements java.io.Serializable {
#   static final long serialVersionUID;
#   private static final java.io.ObjectStreamField[] serialPersistentFields;
#   !static !transient <fields>;
#   private void writeObject(java.io.ObjectOutputStream);
#   private void readObject(java.io.ObjectInputStream);
#   java.lang.Object writeReplace();
#   java.lang.Object readResolve();
#}

## 对R文件下的所有类及其方法，都不能被混淆
#-keepclassmembers class **.R$* {
#    *;
#}

# support-v7
-dontwarn android.support.v7.**
-keep class android.support.v7.internal.** { *; }
-keep interface android.support.v7.internal.** { *; }
-keep class android.support.v7.** { *; }

-keep class com.rflash.basemodule.**{*;}

# support design
#@link http://stackoverflow.com/a/31028536
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
   @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
   @butterknife.* <methods>;
}
# rxlifecycle2
-dontwarn com.trello.rxlifecycle2.**

# rx
-dontwarn rx.**
-keepclassmembers class rx.** { *; }
# retrolambda
-dontwarn java.lang.invoke.*

-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

-keepattributes Signature
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}

#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}

#okio
-dontwarn okio.**
-keep class okio.**{*;}

-keep class com.trello.rxlifecycle2.*{*;}
-keep class javax.annotation.*{*;}

-keep class com.rflash.magiccube.http.BaseBean{*;}
-keep class com.rflash.magiccube.ui.batchrepay.BatchRepay{*;}
-keep class com.rflash.magiccube.ui.batchrepay.PayAccount{*;}
-keep class com.rflash.magiccube.ui.bill.Bill{*;}
-keep class com.rflash.magiccube.ui.cardrecord.CardRecord{*;}
-keep class com.rflash.magiccube.ui.login.Login{*;}
-keep class com.rflash.magiccube.ui.main.AppInfo{*;}
-keep class com.rflash.magiccube.ui.main.BillCount{*;}
-keep class com.rflash.magiccube.ui.operationrecord.OperationRecord{*;}
-keep class com.rflash.magiccube.ui.presentoperation.Operation{*;}
-keep class com.rflash.magiccube.ui.presentoperation.OperationItem{*;}
-keep class com.rflash.magiccube.ui.workreport.ThreeDayCount{*;}

-keep class * extends com.rflash.magiccube.mvp.BasePresenter
-keep class * extends com.rflash.magiccube.mvp.BasePresenterImpl
-keep class * extends com.rflash.magiccube.mvp.BaseView





