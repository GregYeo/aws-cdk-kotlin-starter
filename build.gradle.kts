plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "com.sa201"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val AWS_CDK_VERSION = "1.193.0"
val AWS_SDK_VERSION = "2.20.35"

dependencies {
    implementation("software.amazon.awscdk:cdk:0.36.1")
    implementation("software.amazon.awscdk:ec2:$AWS_CDK_VERSION")
//    implementation("software.amazon.awscdk:s3:$AWS_CDK_VERSION")
    implementation("software.amazon.awscdk:cloudwatch:$AWS_CDK_VERSION")
//    implementation("software.amazon.awscdk:ecs:$AWS_CDK_VERSION")
//    implementation("software.amazon.awscdk:ecs-patterns:$AWS_CDK_VERSION")
//    implementation("software.amazon.awscdk:iam:$AWS_CDK_VERSION")
//    implementation("software.amazon.awscdk:elasticloadbalancing:$AWS_CDK_VERSION")
//    implementation("software.amazon.awscdk:ecr:$AWS_CDK_VERSION")

    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    testImplementation("org.apache.logging.log4j:log4j-core:2.8.2")
    testImplementation("org.apache.logging.log4j:log4j-slf4j-impl:2.1")

    testImplementation("software.amazon.awssdk:cloudwatch:$AWS_SDK_VERSION")
    testImplementation("software.amazon.awssdk:cloudwatchlogs:$AWS_SDK_VERSION")
    testImplementation("software.amazon.awssdk:cloudwatchevents:$AWS_SDK_VERSION")
}



kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("com.sa201.MainKt")
}

tasks.withType<Test>{
    useJUnitPlatform()
    testLogging.events = setOf(
        org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT,
        org.gradle.api.tasks.testing.logging.TestLogEvent.STARTED,
        org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
        org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED,
        org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
    )
}