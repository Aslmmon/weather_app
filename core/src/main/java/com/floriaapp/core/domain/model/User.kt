package com.floriaapp.core.domain.model

data class User(val first_name:String,val last_name:String,val phone:String,var avatar:String,
                var gender:String,var email:String,var country:String?=null,var nationality:String?=null,
var wallet_balance:Double)
