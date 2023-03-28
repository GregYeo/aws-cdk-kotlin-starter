package com.sa201

import com.sa201.helper.DefaultOption
import com.sa201.helper.Dictionary
import com.sa201.stack.CloudWatchStack
import com.sa201.stack.InfraStack
import software.amazon.awscdk.core.App

fun main(args: Array<String>) {
    val app = App()

    InfraStack(app, Dictionary.genStackName("infra"), DefaultOption.defaultStackProps)
    CloudWatchStack(app, Dictionary.genStackName("cloudwatch"), DefaultOption.defaultStackProps)

    app.synth()
}
