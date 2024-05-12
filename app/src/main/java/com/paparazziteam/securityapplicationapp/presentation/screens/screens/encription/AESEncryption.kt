package com.paparazziteam.securityapplicationapp.presentation.screens.screens.encription

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paparazziteam.securityapplicationapp.data.fake.FakeFridaItems
import com.paparazziteam.securityapplicationapp.domain.FridaItem
import com.paparazziteam.securityapplicationapp.presentation.screens.composables.AESEncryptionCard


@Composable
fun AESEncryption(
    modifier: Modifier = Modifier,
    onClickCard: (FridaItem) -> Unit,
    listItemFrida: List<FridaItem>) {

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        state = rememberLazyStaggeredGridState(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp
    ) {
        itemsIndexed(listItemFrida) { index, item ->
            AESEncryptionCard(
                label = item.name,
                icon = item.icon,
                onClickCard = { onClickCard(item) }
            )
        }
    }
}


@Preview
@Composable
private fun AESEncryptionPreview() {
    AESEncryption(
        onClickCard = {},
        listItemFrida = FakeFridaItems.getFridaItems()
    )
}