package com.mvvmcoroutine.retrofit.userlist

import com.mvvmcoroutine.retrofit.userlist.model.Data

interface ItemClickListener {
     fun setClickedInfo(data: Data)
}