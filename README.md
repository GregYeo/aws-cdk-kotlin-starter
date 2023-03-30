## Prerequisite
- JDK11
  - You would change it by [build.settings.kts](build.gradle.kts)
   ```text
    kotlin {
      jvmToolchain(11)
    }
   ```
- CDK CLI
  - [AWS CDK Toolkit (cdk command)](https://docs.aws.amazon.com/cdk/v2/guide/cli.html)
  - [HomeBrew aws-cdk](https://formulae.brew.sh/formula/aws-cdk)
- Change prefix of stack name properly
  - [Dictionary.kt](src/main/kotlin/com/sa201/helper/Dictionary.kt)
  ```kotlin
  private const val stackNamePrefix = "SA201-"
  ```

## Command
### Run it always before each step
TODO: Change it for your AWS environment
```shell
export CDK_DEPLOY_ACCOUNT=010101010101
export CDK_DEPLOY_REGION=ap-northeast-1
export AWS_PROFILE=[CREDENTIAL_PROFILE_NAME]
```
Or you would create .env and load it

```shell
source .env
```

### to initialize
Run it once for this project
```shell
cdk bootstrap
```

### To create AWS resource
```shell
cdk deploy --all --outputs-file ./cdk-outputs.json
```

### To remove AWS resource
```shell
cdk destroy --all
```

## Debugging
### Generated CloudFormation
Once you deployed AWS resource by `cdk deploy` you will se those files
- ./cdk.out/SA201-*.template.json
  ```shell
  ls -l ./cdk.out/*.template.json
  ```
You can specify output path by `--output` option during running `cdk deploy`
> cdk deploy --help
>
> ....
> 
>  -o, --output               Emits the synthesized cloud assembly into a
> directory (default: cdk.out)               [string]
