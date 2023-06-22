package com.paparazziteam.securityapplicationapp.common

import android.content.Context
import android.content.Intent
import android.net.Uri

//openlink
fun openLink(context: Context, url:String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}