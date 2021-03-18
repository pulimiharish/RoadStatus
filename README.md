# TFL Road Status
Road Status App

# Preview
<img src="/art/preview.gif"/>

## New repo setup
- Clone repository 
- Since this repository uses Jetpack compose you'll need to have a Canary build of Android Studio

## Usage
- Clone this repository to your local machine
- Open up the project on Android studio
- Sync the gradle files and download all the required libraries
- Update the TFL_KEY in gradle.properties file (eg: TFL_KEY="0123456789abcdef")
- Run the app
- Provide the Road Name as input in the TextField and you can see the result in the Road Status card.

## Tests
- To run Unit tests please use the below command
    ```
    ./gradlew test
    ```
- To run UI tests please use the below command
    ```
    ./gradlew connectedCheck
    ```
    
## Future improvements
- Use all the latest technologies suggested by Android
- Replace RxJava with Coroutines
- Implement Android navigation components
- Create a dynamic module and move the service code into the dynamic module which gets installed only on demand.
- Configure SonarCloud and achieve 100% code coverage through Unit tests and UI tests.
