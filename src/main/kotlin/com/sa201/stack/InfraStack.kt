package com.sa201.stack

import com.sa201.helper.Dictionary
import software.amazon.awscdk.core.CfnOutput
import software.amazon.awscdk.core.Construct
import software.amazon.awscdk.core.Stack
import software.amazon.awscdk.core.StackProps
import software.amazon.awscdk.services.ec2.Vpc
import software.amazon.awscdk.services.ec2.VpcLookupOptions

class InfraStack(scope: Construct, id: String, props: StackProps): Stack(scope, id, props) {
    val vpc = Vpc.fromLookup(this, "vpc", VpcLookupOptions.builder().vpcId(Dictionary.TARGET_VPC_ID).build())

    init {
        CfnOutput.Builder.create(this, "vpc-az")
            .exportName("vpc-az")
            .value(vpc.availabilityZones.joinToString(", "))
            .build()
    }
}