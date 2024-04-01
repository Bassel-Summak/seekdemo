package com.basapps.seekdemo.job.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserDataRequest(


    @SerialName("query")
    val query: String ,

)
{
    constructor() : this(
        query = "query Getuserinfo { userinfo { _id username displayname } }",
    )
}



