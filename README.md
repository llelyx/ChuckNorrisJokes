# ChuckNorrisJokes

This project is an app that displayes some Chuck Norris jokes taken on ChuckAPI : https://api.chucknorris.io/

## Part 1 - Create a UI List component

In this part, we first created a list of jokes manually.

Then, we instantiated our RecyclerView (with the following dependency: androidx.recyclerview:recyclerview), Adapter and ViewHolder to set up the views. Each view corresponds to a joke.


## Part 2 - Fetch jokes

This part aims to get jokes from Chuck Norris web API.

To do that, we first created the data model matching the API by serializing the returned json. We used Kotlinx.serialization lib.

Then, we had to make some requests to the ChuckAPI in order to get jokes, so we used Retrofit & Rx Java.


## Part 3 - Display jokes on screen

Now that we are able to get jokes from the ChuckAPI, we want to add the jokes we get inside our RecyclerView.

So, we added our jokes to our JokeAdapter, then we created a loader to see the state of the request and finally, we implemented a button that adds 10-by-10 jokes.


## Part 4 - Make UI great again

Here, we added two buttons: one that saves a joke and the other that shares the joke. But, we didn't totally succeed.





**Lou Lély & Marc de Vaugiraud**
