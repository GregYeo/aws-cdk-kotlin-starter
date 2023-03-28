package com.sa201.stack

import software.amazon.awscdk.core.Construct
import software.amazon.awscdk.core.RemovalPolicy
import software.amazon.awscdk.core.Stack
import software.amazon.awscdk.core.StackProps
import software.amazon.awscdk.services.logs.LogGroup
import software.amazon.awscdk.services.logs.RetentionDays

class CloudWatchStack(scope: Construct, id: String, props: StackProps): Stack(scope, id, props) {
    val logGroup = LogGroup.Builder.create(this, id)
        .logGroupName("log-test-group")
        .retention(RetentionDays.ONE_DAY)
        .removalPolicy(RemovalPolicy.DESTROY)
        .build()
}