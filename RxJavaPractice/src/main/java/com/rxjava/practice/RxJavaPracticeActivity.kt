package com.rxjava.practice

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding4.widget.TextViewTextChangeEvent
import com.jakewharton.rxbinding4.widget.textChangeEvents
import hu.akarnokd.rxjava3.math.MathObservable
import io.reactivex.functions.Function3
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableSubscriber
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rx_java_practice.*
import org.reactivestreams.Subscription
import java.lang.Exception
//import rx.observables.MathObservable
import java.util.concurrent.TimeUnit

private const val TAG = "RxJavaPracticeActivity"
class RxJavaPracticeActivity : AppCompatActivity() {
    lateinit var disposable: Disposable
    lateinit var compositeDisposable: CompositeDisposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java_practice)
        compositeDisposable = CompositeDisposable()
        observableAndObserverDemo()
        executeJustOperatorDemo()
        DisposableObserverDemo()
        rangeOperatorDemo()
       bufferOperatorDemo()
        deBounceDemo()
        skipAndMathOperatorDemo()
        concatAndMergeDemo()
        mapOperatorDemo()
        flatMapOperatorDemo()
        switchMapOperatorDemo()

            //hotObservablesBackPressureDemo()
    }

    private fun deBounceDemo(){
      compositeDisposable.add(
              search_ed.textChangeEvents()
              .debounce(300, TimeUnit.MILLISECONDS)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
                  .subscribeWith(searchQuery())

             )

    }


    private fun searchQuery(): DisposableObserver<TextViewTextChangeEvent> {
        return object :DisposableObserver<TextViewTextChangeEvent>(){
            override fun onComplete() {
               Log.d(TAG,"Completed DEbounce...")
            }

            override fun onNext(t: TextViewTextChangeEvent) {
               search_tv.text = "Query : ${t.text.toString()}"
            }

            override fun onError(e: Throwable) {
                TODO("Not yet implemented")
            }

        }

    }

    private fun skipAndMathOperatorDemo(){
        val observable = Observable.range(1,20)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .skip(4)
            .filter { it%2 ==1 }



        val observer = object : Observer<Int>{
            override fun onComplete() {
                Log.d(TAG,"Skip Operator Demo Completed")
            }

            override fun onSubscribe(d: Disposable?) {
                Log.d(TAG,"Skip Operator Demo onSubscribe")
            }

            override fun onNext(t: Int?) {
               Log.d(TAG,"Skip Operator  and Math operator Max onNext :::: $t")
            }

            override fun onError(e: Throwable?) {
               Log.d(TAG,"Error in Skip Operator ${e?.localizedMessage}")
            }

        }
        MathObservable.max(observable).subscribe(observer)

        MathObservable.min(observable).subscribe(observer)
    }
private fun observableAndObserverDemo(){
    println("::::::::::::::::::Basic Observable and Observer   Demo with Filter Operator::::::::::::::::::::::::::::::::")
    val observable = createObservable()
    subscribeObservable(observable)
}

private  fun createObservable() : Observable<Employee> {

    return Observable
                         .fromIterable(DataSource.createEmployeeList())
                      .subscribeOn(Schedulers.io())
        .filter { it.isFromIndia &&  it.name.toLowerCase().startsWith("s")

               }
        .observeOn(AndroidSchedulers.mainThread())

}

private fun subscribeObservable(observable: Observable<Employee>) {
    observable.subscribe(object : Observer<Employee> {
        override fun onComplete() {
           println("In OnComplete")
        }

        override fun onSubscribe(d: Disposable) {
            println("In onSubscribe")

        }

        override fun onNext(t: Employee) {
           println("Employee Details Emp Name : ${t.name} \t EmpDescription::${t.description} \t isFromIndia ${t.isFromIndia}")
        }

        override fun onError(e: Throwable) {
           println("Exception occured ${e.localizedMessage}")
        }


    })
}

private fun executeJustOperatorDemo(){
    println("::::::::::::::::::Just Operator Demo::::::::::::::::::::::::::::::::")
    justOperatorObsever(justOperatorObservableDemo())
}
private fun justOperatorObservableDemo() : Observable<String>{
    return  Observable
                      .just("India","America","Australia","SouthAfrica")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}

private fun justOperatorObsever(observable: Observable<String>){
         observable.subscribe(object: Observer<String> {
             override fun onComplete() {
                 println("Completed Just Operator Demo")
                 disposable.dispose()
             }

             override fun onSubscribe(d: Disposable) {
                 println(" Just Operator Subscribed")
                 disposable = d

             }

             override fun onNext(t: String) {
                println("Just Operator Emission $t")
             }

             override fun onError(e: Throwable) {
                println("Just operator Throws an Error ${e.localizedMessage}")
             }

         })
}

 private fun DisposableObserverDemo(){
     println(":::::::::::Disposable Observers Demo ::::::::::::::::::")
     val arrayOfNumbers = arrayOf(1,2,3,4,5,6,7,8,9,10)
     val observable = Observable
                        .fromArray(intArrayOf(1,2,3,4,5,6,7,8,9,10, 20, 30, 40, 50))
         .subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread())

     var observer:DisposableObserver<IntArray> = object : DisposableObserver<IntArray>() {
         override fun onComplete() {
           //  TODO("Not yet implemented")
         }



         override fun onError(e: Throwable) {
          //   TODO("Not yet implemented")
         }

         override fun onNext(t: IntArray) {
            for( i in  t ){
                print("Emissions from Observable $i \t")
            }
         }

     }

     observable.subscribe(observer)


 }

    private fun rangeOperatorDemo(){
        println("::::::::Range Operator Demo ::::::::::::::::::::")
        Observable.range(1, 10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            .filter { it %2 ==0 }
            .map{ it.toString()+"is Integer Number"}
            //.repeat(2)
            .subscribe(object : DisposableObserver<String?>() {

                override fun onError(e: Throwable) {}
                override fun onComplete() {
                    Log.e(TAG, "All numbers emitted!")
                }

                override fun onNext(t: String?) {
                    Log.i(TAG, "Number: $t")
                }
            })


    }


    private fun bufferOperatorDemo(){
        println("::::::::::::::::::::::Buffer Operator Demo ::::::::::::::::::::::::")
           val observable = Observable.range(1,40)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .filter { it%2 == 1 }
               .map { "$it Is odd Number" }
               .buffer(3)

           val observer = object  : DisposableObserver<List<String>>(){
               override fun onComplete() {
                   println("All Emissions Completed")
                   Log.d(TAG, "All Emissions Completed")
               }

               override fun onNext(t: List<String>) {
                  Log.d(TAG, "In onNext")
                   Log.d(TAG,"Emitted Val is $t")
               }

               override fun onError(e: Throwable) {
                   Log.e(TAG,"Error Thrown ${e.localizedMessage}")
               }

           }

        observable.subscribe(observer)



    }

private fun hotObservablesBackPressureDemo(){
println("::::::::::::::::::::::::::::::In hotObservableBackPressure Demo :::::::::::::::::::::::::::::::::::::::::::::::")
    Flowable.range(0,1000000)
        .onBackpressureBuffer()
       // .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.computation())
        .subscribe(object : FlowableSubscriber<Int> {
            override fun onComplete() {
                println("onComplete hotObservablesBackPressureDemo")
            }

            override fun onSubscribe(s: Subscription) {
               println("onSubscribe hotObservablesBackPressureDemo")
            }

            override fun onNext(t: Int?) {
               println("Flowable Data $t")
            }

            override fun onError(t: Throwable?) {
                if (t != null) {
                    println("Error thrown on handling back Pressure ${t.localizedMessage}")
                }
            }

        })
}


    private fun concatAndMergeDemo(){
      val concatObservable = Observable.concat(fetchMaleObservable(),fetchFemaleObservable())
      val mergerObservable = Observable.merge(fetchMaleObservable(),fetchFemaleObservable())

      val observer = object : Observer<User>{
          override fun onComplete() {
              println("Completed Concat Observable")
          }

          override fun onSubscribe(d: Disposable?) {
              println("Subscribed Concat Observable")
          }



          override fun onError(e: Throwable?) {
              println("Error on Concat Observable")
          }



          override fun onNext(t: User?) {
              try{
                  Thread.sleep(200)
                  println("Concate Observable ::::User Name is ${t?.name} and Gender is ${t?.gender} ")
              }catch (e : Exception){

              }

          }

      }

        val mergeObserver = object : Observer<User>{
            override fun onComplete() {
                println("Completed Merge Observable")
            }

            override fun onSubscribe(d: Disposable?) {
                println("Subscribed Merge Observable")
            }



            override fun onError(e: Throwable?) {
                println("Error on Merge Observable")
            }



            override fun onNext(t: User?) {
                try{
                    Thread.sleep(200)
                    println("Merge Observable ::User Name is ${t?.name} and Gender is ${t?.gender} ")
                }catch (e : Exception){

                }

            }

        }
        concatObservable.subscribe(observer)
        mergerObservable.subscribe(mergeObserver)
    }

    private fun fetchMaleObservable() : Observable<User>{

        return Observable.fromIterable(DataSource.createMaleUserList())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    private fun fetchFemaleObservable(): Observable<User>{
        return Observable.fromIterable(DataSource.createFeMaleUserList())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun updateUser(user : User) : User{
        user.email = "${user.name}@email.com"
        return  user
    }

    private fun mapOperatorDemo(){
        var observable = fetchFemaleObservable().map{
         updateUser(it)
        }



        val observer = object:Observer<User>{
            override fun onComplete() {
               println("Completed Map Operator Demo")
            }

            override fun onSubscribe(d: Disposable?) {
                println("Subscribd Map Operator Demo")
            }

            override fun onNext(t: User?) {
                println("User Info $t")
            }

            override fun onError(e: Throwable?) {
                println("Error on  Map Operator Demo")
            }

        }

        observable.subscribe(observer)
    }

    private fun flatMapOperatorDemo(){
        println("Flat Map Operator Demo ::::::::::")
        val observable = fetchMaleObservable()
            .map { updateUser(it) }
            .flatMap { fetchAddressObsevable(it) }
        val observer = object  : Observer<User>{
            override fun onComplete() {
                println("In Flat Map OnComplete")
            }

            override fun onSubscribe(d: Disposable?) {
                println("In Flat Map onSubscribe")
            }

            override fun onNext(t: User?) {
               println("Flat Map User Info :: $t")
            }

            override fun onError(e: Throwable?) {
                println("In Flat Map onError  ${e?.localizedMessage}")
            }

        }

        observable.subscribe(observer)
    }

    //Concat FlatMap Maintains the order
    private fun concatFlatMapDemo(){
        println("concat Map Operator Demo ::::::::::")
        val observable = fetchMaleObservable()
            .map { updateUser(it) }
            .concatMap { fetchAddressObsevable(it) }
        val observer = object  : Observer<User>{
            override fun onComplete() {
                println("In concat Map OnComplete")
            }

            override fun onSubscribe(d: Disposable?) {
                println("In concat Map onSubscribe")
            }

            override fun onNext(t: User?) {
                println("concat Map User Info :: $t")
            }

            override fun onError(e: Throwable?) {
                println("In concat Map onError  ${e?.localizedMessage}")
            }

        }

        observable.subscribe(observer)
    }

    private fun switchMapOperatorDemo(){
        println("Switch Map Operator Demo ::::::::::")
        val observable = fetchMaleObservable()
            .map { updateUser(it) }
            .switchMap { fetchAddressObsevable(it) }
        val observer = object  : Observer<User>{
            override fun onComplete() {
                println("In Switch Map OnComplete")
            }

            override fun onSubscribe(d: Disposable?) {
                println("In Switch Map onSubscribe")
            }

            override fun onNext(t: User?) {
                println("Switch Map User Info :: $t")
            }

            override fun onError(e: Throwable?) {
                println("In Switch Map onError  ${e?.localizedMessage}")
            }

        }

        observable.subscribe(observer)
    }
    private fun fetchAddressObsevable(user : User)  : Observable<User>{
        var address = Address("${user.name}1600 Amphitheatre Parkway, Mountain View, CA 94043")
        user.address = address

       val addressObservable =  Observable.just(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        return  addressObservable
    }
}



/*
Difference between RXJava and RxAndroid
A lot of people don't know there's a difference between RxJava and RxAndroid.
It's not much a of a difference, but it's recommended to get the most recent RxJava dependency,
along with the most recent RxAndroid dependency.

Here are the main things you need to know about the difference between RxJava and RxAndroid:

    RxAndroid contains reactive components that make using RxJava in Android easier and hassle free.
     More specifically it provides a Scheduler that can schedule tasks on the main thread or any other looper - aka any thread. (It makes threading on Android very simple).

    You could use just the RxAndroid dependency, but the releases of RxAndroid updates are less frequent.
     To make sure you're up-to-date with the newest RxJava components, use the newest RxJava dependency.


//https://codingwithmitch.com/courses/rxjava-rxandroid-for-beginners/flowables/

 */

/*
Difference b/w Observables and Flowables:
Basically in RxJava2 you have two 'Observable' object classes instead of one:

    (1)Observable

    (2) Flowable

    Flowables were introduced in RxJava2 as an answer to a problem.
    The problem was related to something called backpressure.

    The property that distinguishes one from the other is backpressure.

    Observables are not backpressure-aware

    Flowables are backpressure-aware

What is Back Pressure?
Like if you have too much data emitting to the Observers and the Observers can't handle it.
In situations like that you might see an Out of Memory exception. This situation is called Back Pressure.

There are 2 types of Sources:
(1) Hot Sources : This is called push strategy. Observables keep on emitting/Pushing  the data.
 It is up to the observes to keep or handle the data that is emitted by Observables.
 If Observers are not able to handle , Observables need to use some kind of
  buffering strategy to buffer the objects.
(2) Cold Sources
    This is called Pull strategy. That means the data is emitted lazily by the Observables.
     When the Observers are ready then only Observables will push data and send data at the rate
     at which Observers can handle.
     The objects emitted by the Observables do not need to be buffered because the whole process
      is basically at the discretion of the Observer.


Back Pressure Strategies:

1. onBackpressureBuffer()
This method provides an unbounded buffer between upstream source and downstream operator.
The word "unbounded" means  this operator  can handle almost any amount of data coming from source as
long as JVM does n't run out of memory.

2.

 */

/*
Observables produce stream of data to be observed by Observer.
The various ways this stream can be emitted as :
- Items are to be emitted one by one.
- Items are to be emitted by controlling the producers emission(with back pressure).
- Only one item need to be emitted as part of success.
- Item has to be emitted based on conditions

There are 5 types of Observables:
(1) Observable: Observable emits 0..N elements, and then completes successfully on error
(2) Flowable: Similar to Observable but with a back pressure strategy.
(3)Single: It completes with a value successfully or an error.(doesn’t have onComplete callback, instead onSuccess(val)).
(4) Maybe : It completes with/without a value or completes with an error.
(5)Completable: It just signals if it has completed successfully or with an error.
 */

/*
Disposable: When an Observer no longer wants to listen to Observable ,then it uses Disposable.
In order disposables are used to avoid memory leaks.
We use dispose() method in onDestroy() to dispose an observable.
 */