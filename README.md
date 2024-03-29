# MovieRankApp

MovieRankApp is designed based on mvvm architecture. Also, it uses android architecutre components.
I have implemented asynchronous programming using coroutines to communicate with the server.
I used to repository pattern to use to local DB according to the network state.

<img src="https://user-images.githubusercontent.com/34837583/137613612-004e58f8-31b3-4fd6-ae02-f5dcbaa83039.png" width="250"> <img src="https://user-images.githubusercontent.com/34837583/137617500-56486b8c-256d-4847-b699-8b955b103929.png" width="250"> <img src="https://user-images.githubusercontent.com/34837583/137617473-383d27b8-c386-4256-bd6a-6608de4d56e7.png" width="250">
<img src="https://user-images.githubusercontent.com/34837583/137617584-9694b0ae-ef0e-4679-92a5-9e36b992afaf.png" width="250"> <img src="https://user-images.githubusercontent.com/34837583/137617830-7e4a177b-427c-46b5-b28c-cea8335b0bba.png" width="250"> <img src="https://user-images.githubusercontent.com/34837583/137617743-4a33212c-7bff-4b49-b653-5b138bff4380.png" width="250">
<br>

## Library
- Minimum SDK 23
- Kotlin based
- Jetpack
  - LiveData : an observable data holder class
  - Navigation : Use fragment so easily
  - ViewModel : Designed to store and manage UI-related in a lifecycle.
  - Room : Local DB
  - Databiding : Allow you to bind UI components in your layouts
- Daager2 : Dependency Injection
- Glide : Image loading
- Gson : Convert data class to JSON
- Retrofit : RestApi library
- Okhttp3 : Use the httpLoggingInterceptor 
- Coroutine : Implement asynchronous programming easily
<br>

## MAD Score
<img src="https://user-images.githubusercontent.com/34837583/137618755-c5ee345e-c2f0-4887-95fb-2258059a397f.png" width="500">
<img src="https://user-images.githubusercontent.com/34837583/137618878-9d035fdf-4723-4863-b4d1-dc503981cdd9.png" width="500">
<img src="https://user-images.githubusercontent.com/34837583/137618789-8981f0ab-2128-4bb1-af19-3bc9e501cb46.png" width="500">
<br>

## Architecture
I implemented it based on mvvm architecture using databinding, livedata, viewmodel.
Communication with the server is implemented to call the local DB or server according to the network rather than one data source.
With the same interface, the data source can be determined by itself.

![image](https://user-images.githubusercontent.com/34837583/137619107-57a33b4f-48b7-44f1-8373-88278a17e9a6.png)


