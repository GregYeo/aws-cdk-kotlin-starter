package com.sa201.stack

import software.amazon.awscdk.core.Construct
import software.amazon.awscdk.core.Stack
import software.amazon.awscdk.core.StackProps

class InfraStack(scope: Construct, id: String, props: StackProps): Stack(scope, id, props) {
//    val vpc = Vpc.fromLookup(this, "vpc", VpcLookupOptions.builder().vpcId(Dictionary.TARGET_VPC_ID).build())
}