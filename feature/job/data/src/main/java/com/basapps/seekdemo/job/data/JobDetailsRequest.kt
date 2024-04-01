package com.basapps.seekdemo.job.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class JobDetailsRequest(


    @SerialName("query")
    val query: String ,

    @SerialName("variables")
    val variables: Variables


)
{
    constructor(variables: Variables) : this(
        query = "query Job(\$jobId: String!) { job(id: \$jobId) { _id description haveIApplied industry location positionTitle salaryRange { min max } } }",
        variables = variables
    )

    @Serializable
    data class Variables(
        val jobId: String,
    )
}
