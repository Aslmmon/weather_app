package com.test.utils.Bases

interface Communication {
    fun goToHomeTab()
    fun submitCartCount(cartCount:Int)
    fun goToProductTab()
    fun goToCartTab()

    fun goToServicesTab()
    fun invokeCartCounter()
    fun clearCartCounter()
    fun openDrawer()
    fun goToProductsCategories(idProduct:Int, nameProduct:String)
    fun goToProductDetails(idProduct: Int)
    fun goToVendorsActivity(idVendor:Int)
    fun setNewWalletBalance()
}

interface CheckOutCommunication{
    fun invokeGetShippmentFees(s: String, i: Int)
}