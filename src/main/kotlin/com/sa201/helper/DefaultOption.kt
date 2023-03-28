package com.sa201.helper

import software.amazon.awscdk.core.Environment
import software.amazon.awscdk.core.StackProps

object DefaultOption {
    val defaultEnv: Environment = run {
        var account: String? = null
        var region: String? = null
        account = account ?: System.getenv("CDK_DEPLOY_ACCOUNT")
        region = region ?: System.getenv("CDK_DEPLOY_REGION")
        account = account ?: System.getenv("CDK_DEFAULT_ACCOUNT")
        region = region ?: System.getenv("CDK_DEFAULT_REGION")
        Environment.builder()
            .account(account)
            .region(region)
            .build()
    }

    val defaultStackProps = StackProps
        .Builder()
        .env(defaultEnv)
        .tags(Dictionary.defaultTagging)
        .build()

}