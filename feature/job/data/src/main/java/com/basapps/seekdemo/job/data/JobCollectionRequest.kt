package com.basapps.seekdemo.job.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class JobCollectionRequest(


    @SerialName("query")
    val query: String ,

    @SerialName("variables")
    val variables: Variables


)
{
    constructor(variables: Variables) : this(
        query = "query Active(\$appliedonly: Boolean, \$positiontitle: String, \$page: Int, \$limit: Int) { active(appliedonly: \$appliedonly, positiontitle: \$positiontitle, page: \$page, limit: \$limit) { page size hasNext total jobs { _id positionTitle description salaryRange { min max } location industry haveIApplied } } }",
        variables = variables
    )

    @Serializable
    data class Variables(
        val limit: Int,
        val page: Int,
        val positiontitle: String,
        val appliedonly: Boolean
    )
}


