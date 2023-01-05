# ComposeTesting
This project is intended to demonstrate how to do instrumented tests with the default Android tooling, including Compose, Jetpack Navigation and Hilt. As always, Google documentation is not really supporting the standard Android development environment, so I think it is good to have some extra guidelines to be able to instrument tests with the default tools. 

## Compose
In order to get Compose working, you have to add a Compose rule. In case of using Hilt, you also need to specify the execution order. As we will be testing ViewModel injected by Hilt based on activity lifecycle, we need to use an AndroidComposeRule with an activity.   
```
@get:Rule(order = 2)
val composeTestRule = createAndroidComposeRule<MainActivity>()
```

Compose is not supporting Junit5 right now ([see issue here](https://github.com/mannodermaus/android-junit5/issues/234)). For now, I will keep Junit4 for this repository. Be sure that your Instrumentation dependencies are using Junit4 (you can still use Junit5 for your unit tests). Maybe in the future I can add Junit5 support as well.

Dependencies can be found in the [Testing Compose reference](https://developer.android.com/jetpack/compose/testing) or in this repository.

## Hilt
A couple of steps are needed in order to get instrumented tests working. 
1. First of all, we need to create a custom test runner
[custom test runner](https://github.com/mathias21/ComposeTesting/blob/main/app/src/androidTest/java/com/batcuevasoft/composetesting/HiltTestRunner.kt) and configure that in our app module build.gradle file:

```
defaultConfig {
    [...]

    testInstrumentationRunner = "com.batcuevasoft.composetesting.HiltTestRunner"
}
```

2. Add HiltAndroidRule to the test class:

```
@get:Rule(order = 1)
var hiltRule = HiltAndroidRule(this)
```

3. Replace your dependencies
- Either via module replacement (configuration for all tests): this increases complexity to remove dependencies. If you want to make a reusable solution, we would have to make this new dependencies configurable, as you cannot place the module replacement in the test class, so **local mocks cannot be used in a simple way**.
- Or via module uninstall (single test affected): this will allow us to inject custom mocks from each test, which seems the optimal solution. But this will increase build times significantly.

* SideNote: uninstalling multiple modules is a bit confusing by how the annotation is built. If you want to uninstall multiple modules at the same time, you can use: 
```
@UninstallModules(
    ComposeTestingModule.BindsModule::class, 
    ComposeTestingModule::class
)
```

Dependencies can be found in the [End to end Hilt testing reference](https://developer.android.com/training/dependency-injection/hilt-testing#end-to-end) or in this repository.
