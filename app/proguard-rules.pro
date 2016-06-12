# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\android_studio\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-dontwarn java.awt.**
-dontwarn com.squareup.okhttp.**
-dontwarn javax.security.sasl.**
-dontwarn java.beans.Beans
-dontwarn com.sun.mail.imap.protocol.IMAPSaslAuthenticator
-dontwarn javax.security.auth.callback.NameCallback

-keepattributes Signature
-keepattributes InnerClasses

-dontnote android.net.http.SslCertificate
-dontnote android.net.http.SslError
-dontnote android.net.http.SslCertificate$DName
-dontnote org.apache.http.conn.scheme.SocketFactory
-dontnote org.apache.http.conn.scheme.HostNameResolver
-dontnote org.apache.http.conn.ConnectTimeoutException
-dontnote org.apache.http.params.HttpParams
-dontnote com.google.vending.licensing.ILicensingService
-dontnote com.android.vending.licensing.ILicensingService
-dontnote android.support.annotation.Keep
-dontnote com.google.gson.internal.UnsafeAllocator
-dontnote com.squareup.picasso.Utils

-dontnote org.apache.harmony.awt.datatransfer.DTK
-dontnote com.google.gson.internal.UnsafeAllocator



