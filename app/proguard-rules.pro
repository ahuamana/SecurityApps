
 -keep class com.paparazziteam.securityapplicationapp.domain.** { *; }

 #Rules For OkHttp3

 #### OkHttp, Retrofit ####
 -dontwarn okhttp3.**
 -dontwarn okio.**
 -dontwarn javax.annotation.**
 -keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
 # Platform calls Class.forName on types which do not exist on Android to determine platform.
 -dontnote retrofit2.Platform
 # Platform used when running on Java 8 VMs. Will not be used at runtime.
 -dontwarn retrofit2.Platform$Java8

 #### GSON ####
 # Gson uses generic type information stored in a class file when working with fields. Proguard
 # removes such information by default, so configure it to keep all of it.
 -keepattributes Signature

 # For using GSON @Expose annotation
 -keepattributes Annotation

 # Gson specific classes
 -dontwarn sun.misc.**
 #-keep class com.google.gson.stream.** { *; }

 # Application classes that will be serialized/deserialized over Gson
 -keep class com.google.gson.examples.android.model.** { <fields>; }

 # Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
 # JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
 -keep class * extends com.google.gson.TypeAdapter
 -keep class * implements com.google.gson.TypeAdapterFactory
 -keep class * implements com.google.gson.JsonSerializer
 -keep class * implements com.google.gson.JsonDeserializer

 # Prevent R8 from leaving Data object members always null
 -keepclassmembers,allowobfuscation class * {
   @com.google.gson.annotations.SerializedName <fields>;
 }

 # Retain generic signatures of TypeToken and its subclasses with R8 version 3.0 and higher.
 -keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
 -keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken


#AndroidX View Model
-keep class * extends androidx.lifecycle.ViewModel { *; }
-keepclassmembers class * extends androidx.lifecycle.ViewModel { *; }
-keep class androidx.lifecycle.LiveData { *; }


##---------------End: proguard configuration for Gson  ----------
# Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
 -keep,allowobfuscation,allowshrinking interface retrofit2.Call
 -keep,allowobfuscation,allowshrinking class retrofit2.Response

 # With R8 full mode generic signatures are stripped for classes that are not
 # kept. Suspend functions are wrapped in continuations where the type argument
 # is used.
 -keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation