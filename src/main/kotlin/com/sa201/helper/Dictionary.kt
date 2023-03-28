package com.sa201.helper

object Dictionary {
    private const val stackNamePrefix = "SA201-"
    fun genStackName(name: String) = stackNamePrefix + name

    val defaultTagging = mapOf<String, String>(
        "Department" to "SRE",
        "User" to "Greg",
        "Service" to "SystemMaintenance"
    )

    const val TARGET_VPC_ID = "vpc-65ff500d"
}
