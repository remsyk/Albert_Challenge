# Albert_Challenge

This is a coding challenge for android development role at a company called Albert. 

## How to use

* Search is live, so as user types books will appear in list
* **Single Press**, opens a webview revealing more data about book
* **Long Press**, either adds or deletes a wish list item depending on what tab user is on
* **Back Button**, takes user out of webview

## Built With

* [Kotlin](https://kotlinlang.org/) - Main language
* [Retrofit](https://square.github.io/retrofit/)- HTTP client
* [Realm](https://realm.io/) - Database
* [RxJava2](https://github.com/ReactiveX/RxJava) - Java VM implementation of Reactive Extensions

## Issues 

I would have addressed these if there was more time

* With the current work around, when back button is pressed it resets the application rather than bringing user back to the previous activity. Before it would take multiple rapid presses of the back button to return to the previous activity
* updateRecyclerView is called way to many times
* Memory management could be better
* When books are deleted from wishlist, the ordering is not preserved
* The search functionality is very strange, the first result almost always ends up being Laws and then Hamlet. 
* Ordering of search results does not seems to have a particular logic to it at times, but will return acceptable results






