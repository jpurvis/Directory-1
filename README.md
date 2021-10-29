# Directory
The App helps users to see and use all their colleague contact details and user can see which rooms in the office is currently occupied. The App contain list/details page for the 
people and list for the room.

The App was developed in MVVM architecture with android jetpack components to make the project lifecycle aware by following the clean code architecture with mutiple modules 
which ensure that the app can be easily expanded both in terms of codebase and UX. Material Component design is added to make the UI reusable.

Added unit test for each modules including repository and viewmodel testing.

Our project to have three different ENVs (environments) namely, dev (development), stg (staging) and prod (production). We want to use different API URLs for each ENVs. We want 
to use different application IDs for each of the ENVs so that, builds with different ENVs can co-exist in a device. 

Technologies used: Kotlin: Clean code architecture with multiple modules, MVVM architecture, ConstrainLayout, view binding, Navigation Graph, Koin Dependency Injection, jUnit, mock, 
RxJava with executor and scheduler, Retrofit, Glide for loading images.




