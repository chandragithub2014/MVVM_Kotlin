package com.mvvmcoroutine.retrofit.userlist.model

data class RetroResult(val page : Int, val per_page:Int,val total:Int,val total_pages :Int,val data : List<Data>,val ad : Ad)

data class  Data(val id : Int,val email:String,val first_name:String,val last_name:String,val avatar:String)

data class Ad(val url : String , val company  : String , val text : String)


//https://www.freecodeformat.com/json2pojo.php