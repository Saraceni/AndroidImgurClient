# Android Imgur Client

This project is a simple example of an Application who serves images through an api, displays them in a grid layout with variable size columns, performs 
lazy loading of images and cache of images. I have chosen the Imgur Api which is open and free for non comercial usage to serve the application. For the 
cache of the images the Picasso library was chosen because it is one of the easiest to use and I personally like it. For the Api consumation it was used 
Retrofit 2 and RxJava 2 for for the Publish - Subscribe pattern which makes the code cleaner and more easy to maintain. In order to display the posts 
in columns with fixed width and variable height it was used a [RecyclerView](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.html) with a [StaggeredGridLayoutManager](https://developer.android.com/reference/android/support/v7/widget/StaggeredGridLayoutManager.html).

![](static/imgur_client.png)

## Getting Started

The first thing you need before running this project is to [Register an Imgur Application](https://api.imgur.com/#registerapp) in order to have access to the [Imgur Api](https://apidocs.imgur.com/). After you complete the registration of the application you will receive a Client ID. Go to the file package/api/client/ApiClient.java and change the variable CLIENT_ID to the one created for you.

```
private static final String CLIENT_ID = "YourImgurClientId";
```

After that you have to download the dependencies using Gradle and you are ready to run the Application.

## Authors

* **Rafael Saraceni** 

## Built With

* [Imgur Api](https://api.imgur.com/) - The Api used to consume images
* [Picasso](http://square.github.io/picasso/) - Image loading and cache
* [Retrofit](http://square.github.io/retrofit/) - Used to perform REST calls
* [Gson](https://github.com/google/gson) - Used to serialize / deserialize Java Objects into JSON
* [OkHttp](http://square.github.io/okhttp/) - Used to perform HTTP calls
* [RxJava](https://github.com/ReactiveX/RxJava) - Reactive Extension for Java

## Acknowledgments

* Hat tip to anyone who's code was used
* StackOverflow
* Google
* Linus Torvalds
* Tutankamon
