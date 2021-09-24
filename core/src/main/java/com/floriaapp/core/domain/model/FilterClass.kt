package com.floriaapp.core.domain.model

class FilterClass {

    companion object {
        var filterData = FilterData()
    }
}

data class FilterData(
    var min_price: Int?=null,
    var max_price: Int?=null,
    var categoriesList: MutableList<Int>?=null,
    var isFilterd:Boolean?=false
)