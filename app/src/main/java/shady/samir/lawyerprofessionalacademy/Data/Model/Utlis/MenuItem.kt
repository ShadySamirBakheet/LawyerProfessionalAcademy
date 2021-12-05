package com.aplexgold.aplexshipping.Data.Model.Home


data class MenuItem(
    var name: String,
    var icon: Int,
    var isMain: Boolean,
    var id: Int,
    val child: ArrayList<MenuItem>?
)
