package com.basapps.seekdemo.job.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class JobApplyRequest(


    @SerialName("query")
    val query: String ,

    @SerialName("variables")
    val variables: Variables


)
{
    constructor(variables: Variables) : this(
        query = "mutation Apply(\$jobId: String!) { apply(jobId: \$jobId) }",
        variables = variables
    )

    @Serializable
    data class Variables(
        val jobId: String,
    )
}


