package com.basapps.seekdemo.common.domain.mappers

import com.basapps.seekdemo.common.data.models.JobApplyApiModel
import com.basapps.seekdemo.common.data.models.JobCollectionApiModel
import com.basapps.seekdemo.common.data.models.JobDetailsApiModel
import com.basapps.seekdemo.common.data.models.UserApiModel
import com.basapps.seekdemo.common.data.models.UserUpdateApiModel
import com.basapps.seekdemo.common.domain.models.Job
import com.basapps.seekdemo.common.domain.models.JobsCollection
import com.basapps.seekdemo.common.domain.models.Result
import com.basapps.seekdemo.common.domain.models.User
import com.basapps.seekdemo.common.utils.Mapper
import javax.inject.Inject

class UserMapper @Inject constructor() : Mapper<UserApiModel.Authuser, User> {
    override fun map(from: UserApiModel.Authuser): User =
        User(
            avatar = "https://avatar.iran.liara.run/public/boy?username=Ash",
            fullName = from.displayname,
            userName = from.username,
            id = from.id,
        )
}


class JobDetailsMapper @Inject constructor(private val mapper: JobMapper) : Mapper<JobDetailsApiModel, Job> {
    override fun map(from: JobDetailsApiModel): Job = mapper.map(from.job)
}

class JobMapper @Inject constructor() : Mapper<JobDetailsApiModel.Job, Job> {
    override fun map(from: JobDetailsApiModel.Job): Job =
        Job(
            id = from.id,
            description = from.description,
            haveIApplied = from.haveIApplied,
            industry = from.industry,
            location= from.location,
            positionTitle= from.positionTitle,
            maxSalary = from.salaryRange.max.toString(),
            minSalary= from.salaryRange.min.toString()

        )
}

class JobCollectionsMapper @Inject constructor(private val mapper: JobMapper) : Mapper<JobCollectionApiModel, JobsCollection> {
    override fun map(from: JobCollectionApiModel): JobsCollection  {

        val transformedList = ArrayList<Job>()

        from.active.jobs.forEach {
            transformedList.add(mapper.map(it))
        }
        return JobsCollection(
            jobs = transformedList,
            isThereMorePages = from.active.hasNext,
            page = from.active.page,
            size = from.active.size,
            total = from.active.total,
        )
    }



}

class JobApplyingMapper @Inject constructor() : Mapper<JobApplyApiModel, Result> {
    override fun map(from: JobApplyApiModel): Result =
        Result(
            sucess = from.apply,
            message = "user1",
        )
}

class UserUpdateMapper @Inject constructor() : Mapper<UserUpdateApiModel, Result> {
    override fun map(from: UserUpdateApiModel): Result =
        Result(
            sucess = from.updateuser,
            message = "user1",
        )
}