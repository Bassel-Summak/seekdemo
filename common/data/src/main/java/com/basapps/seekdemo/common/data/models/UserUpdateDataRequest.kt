package com.basapps.seekdemo.common.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserUpdateDataRequest(


    @SerialName("query")
    val query: String ,

    @SerialName("variables")
    val variables: Variables

)
{
    constructor(variables: Variables) : this(
        query = "mutation Updateuser(\$name: String!) { updateuser(name: \$name) }",
        variables = variables
    )

    @Serializable
    data class Variables(
        val name: String,
    )
}



