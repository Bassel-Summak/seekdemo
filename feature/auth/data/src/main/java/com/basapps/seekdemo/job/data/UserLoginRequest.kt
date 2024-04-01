package com.basapps.seekdemo.job.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserLoginRequest(


    @SerialName("query")
    val query: String ,

    @SerialName("variables")
    val variables: Variables


)
{
    constructor(variables: Variables) : this(
        query = "mutation Authuser(\$username: String!, \$password: String!) { authuser(username: \$username, password: \$password) { _id username displayname token } }",
        variables = variables
    )
}


@Serializable
data class Variables(
    val username: String,
    val password: String
)
