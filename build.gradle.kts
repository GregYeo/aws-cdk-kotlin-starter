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
}



kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("com.sa201.MainKt")
}