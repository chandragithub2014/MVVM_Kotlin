package com.mvvmcore.usecases

import com.mvvmcore.data.PostModel

class GetPostCount {

    operator  fun invoke(postModel: PostModel)  = getCount(postModel.title) + getCount(postModel.body)

    private fun getCount(str : String) =
       str.split(" ","\n")
           .filter {
                it.contains(Regex(".*[a-zA-Z].*"))
           }.count()

}