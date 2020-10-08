# MarvelApp

MarvelApp is an Android app that presents marvel characters and its information from Marvel Developer API (https://developer.marvel.com/).

# MarvelApp Scope

  - Character List Screen that presents information through a infinite list fetching new information after reaching bottom of screen.
  - Character Detail Screen presents also the list of comics that character is after a selection in Character List Screen

# Features

  - Infinite Fetching Data for Characters
  - Caching locally comics information 
  - Multi language support - EN, PT-BR, ES
  - Multi Orientation Support (portrait and landscape)

# Technical Stack

MarvelApp is built using Kotlin and Android SDK.

  - MarvelApp follow the Clean Architecture and MVVM pattern in Presentation Layer
  - Layers integration and asynchronous operations is structure upon Kotlin Coroutines 
  - Dependency Injection structure is made upon Koin (https://github.com/InsertKoinIO/koin)
  - Image Downloading is made using Glide (https://github.com/bumptech/glide)
  - For caching data was used Room Database (https://developer.android.com/topic/libraries/architecture/room)
  - For REST Api connections Retrofit was used (https://github.com/square/retrofit)
  - In MVVM pattern the communication between View and ViewModel was made using LiveData from Architecture Components
  - For unit tests, Mockito Kotlin to mock objects was used. (https://github.com/nhaarman/mockito-kotlin)

 
> Third-Party Dependencies are obtained via gradle.

# Build

> To build the application was using **Kotlin Gradle DSL** to fast build speed and use of kotlin to create build scripts.

# Quality

MarvelApp project contains Unit Tests and UI Tests

Unit Tests

![alt text](https://github.com/RodrigoMRodovalho/marvelapp/blob/main/Screenshots/UnitTests.png)

Ui Tests

![alt text](https://github.com/RodrigoMRodovalho/marvelapp/blob/main/Screenshots/UITest_1.png)

![alt text](https://github.com/RodrigoMRodovalho/marvelapp/blob/main/Screenshots/UITest_2.png) 

### Installation

You have to add the API settings in **local.properties** file

> sdk.dir=/your/sdk/path
> apiKey=YOUR_API_KEY
> ts=YOUR_API_TS
> hash=YOUR_API_HASH

### Screenshots

![alt text](https://github.com/RodrigoMRodovalho/marvelapp/blob/main/Screenshots/androidCharacterList_1.png)

![alt text](https://github.com/RodrigoMRodovalho/marvelapp/blob/main/Screenshots/androidCharacterList_2.png)

![alt text](https://github.com/RodrigoMRodovalho/marvelapp/blob/main/Screenshots/androidCharacterDetail_1.png)

![alt text](https://github.com/RodrigoMRodovalho/marvelapp/blob/main/Screenshots/androidCharacterDetail_2.png)

![alt text](https://github.com/RodrigoMRodovalho/marvelapp/blob/main/Screenshots/androidCharacterDetail_3.png)

# Future Improvements

- Layout design
- Specific error handling
- Adding more ui tests
- Fix intermittent Koin issue in Ui Tests 
 
License
----
MIT