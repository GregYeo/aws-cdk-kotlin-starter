package com.sa201

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.*
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient
import software.amazon.awssdk.services.cloudwatchlogs.model.GetLogEventsRequest
import software.amazon.awssdk.services.cloudwatchlogs.model.InputLogEvent
import software.amazon.awssdk.services.cloudwatchlogs.model.PutLogEventsRequest
import java.io.File
import java.time.Instant
import java.util.UUID


@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class CloudWatchLogTest {

    companion object {
        lateinit var logGroup: String
        lateinit var logStream: String
        var logStartTime: Long = 0L
        var logEndTime: Long = 0L

        val logIdentifier = UUID.randomUUID().toString()

        val cdkOutputsJson = File("cdk-outputs.json")
        val messages = listOf(
            "[$logIdentifier] Hello World",
            "[$logIdentifier] It is requested from SDK",
            "[$logIdentifier] Was it made by AWS CDK?"
        )

        lateinit var cloudWatchLogsClient: CloudWatchLogsClient
    }

    @Test
    @Order(1)
    fun clientSetting(){
        val mapper = ObjectMapper()
        val cdkOutput = mapper.readTree(cdkOutputsJson)

        val region = cdkOutput["SA201-cloudwatch"]["region"].textValue()
        logGroup = cdkOutput["SA201-cloudwatch"]["logGroupName"].textValue()
        logStream = cdkOutput["SA201-cloudwatch"]["logStream"].textValue()

        cloudWatchLogsClient = CloudWatchLogsClient.builder()
            .region(Region.of(region))
            .credentialsProvider(ProfileCredentialsProvider.create())
            .build()
    }

    @Test
    @Order(2)
    fun putLog(){
        logStartTime = Instant.now().toEpochMilli()

        messages.forEach { message ->
            cloudWatchLogsClient.putLogEvents(PutLogEventsRequest.builder()
                .logGroupName(logGroup)
                .logStreamName(logStream)
                .logEvents(listOf(InputLogEvent.builder()
                    .timestamp(Instant.now().toEpochMilli())
                    .message(message)
                    .build()))
                .build())
        }

        logEndTime = Instant.now().toEpochMilli()
    }


    @Test
    @Order(3)
    fun waitLog(){
        Thread.sleep(3000)
    }

    @Test
    @Order(4)
    fun checkLog(){
        val responseOfLog = cloudWatchLogsClient.getLogEvents(GetLogEventsRequest.builder()
            .logGroupName(logGroup)
            .logStreamName(logStream)
            .startTime(logStartTime)
            .endTime(logEndTime)
            .build())

        val foundLogs = responseOfLog.events().map { it.message() }

        Assertions.assertArrayEquals(messages.toTypedArray(), foundLogs.toTypedArray())
    }
}