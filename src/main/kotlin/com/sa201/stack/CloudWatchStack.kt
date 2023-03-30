package com.sa201.stack

import software.amazon.awscdk.core.*
import software.amazon.awscdk.services.logs.LogGroup
import software.amazon.awscdk.services.logs.LogStream
import software.amazon.awscdk.services.logs.RetentionDays

class CloudWatchStack(scope: Construct, id: String, props: StackProps): Stack(scope, id, props) {
    val logGroup = LogGroup.Builder.create(this, "log-test-group")
        .logGroupName("log-test-group")
        .retention(RetentionDays.ONE_DAY)
        .removalPolicy(RemovalPolicy.DESTROY)
        .build()

    val logStream = LogStream.Builder.create(this, "debugging-stream")
        .logStreamName("debugging-stream")
        .logGroup(logGroup)
        .build()

    init {
        CfnOutput.Builder.create(this, "region")
            .exportName("region")
            .value(props.env!!.region)
            .build()
        CfnOutput.Builder.create(this, "logGroupName")
            .exportName("logGroupName")
            .value(logGroup.logGroupName)
            .build()
        CfnOutput.Builder.create(this, "logStream")
            .exportName("logStream")
            .value(logStream.logStreamName)
            .build()
    }
}