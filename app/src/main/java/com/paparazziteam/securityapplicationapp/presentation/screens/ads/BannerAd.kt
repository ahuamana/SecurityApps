package com.paparazziteam.securityapplicationapp.presentation.screens.ads

import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.paparazziteam.securityapplicationapp.BuildConfig
import com.paparazziteam.securityapplicationapp.R

@Composable
fun BannerAd(modifier: Modifier) {

    val addIdHomeProd = stringResource(id = R.string.paparazziteam_home_banner_id)
    val addIdHomeTest = stringResource(id = R.string.paparazziteam_home_banner_id_test)

    Column(modifier = modifier) {
        //AdView
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                AdView(context).apply {
                    setAdSize(AdSize.BANNER)
                    println("addIdHomeProd: $addIdHomeProd")
                    println("addIdHomeTest: $addIdHomeTest")
                    adUnitId = if(BuildConfig.DEBUG) addIdHomeTest else addIdHomeProd

                    //Request an Ad
                    PackageManager.GET_META_DATA
                    loadAd(AdRequest.Builder().build())
                }
            }
        )
    }
}
