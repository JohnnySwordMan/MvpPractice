## MVP模板
### [AbstractView](https://github.com/JohnnySwordMan/MvpPractice/blob/master/app/src/main/java/com/jaygege/mvppractice/base/view/AbstractView.java)
+ 所有view接口的基类，定义了页面交互的方式，例如loading框、toast之类的  

### [AbstractPresenter](https://github.com/JohnnySwordMan/MvpPractice/blob/master/app/src/main/java/com/jaygege/mvppractice/base/presenter/AbstractPresenter.java)  
+ 所有Presenter接口的基类，定义了attachView和detachView等方法  

### [BasePresenter](https://github.com/JohnnySwordMan/MvpPractice/blob/master/app/src/main/java/com/jaygege/mvppractice/base/presenter/BasePresenter.java)
+ 思考：考虑到Presenter中需要经常执行一些耗时操作，Presenter又持有Activity的强引用，如果在网络请求结束之前，Activity被销毁，但此时Activity对象无法被回收，发生内存泄漏。  

+ 解决方式：通过弱引用，配合Activity的生命周期，将Presenter与Activity关联起来。  

### [MvpContract](https://github.com/JohnnySwordMan/MvpPractice/blob/master/app/src/main/java/com/jaygege/mvppractice/MvpContract.java)  
定义了Activity需要用到的View和Presenter接口  
### [MvpPresenter](https://github.com/JohnnySwordMan/MvpPractice/blob/master/app/src/main/java/com/jaygege/mvppractice/MvpPresenter.java)  
+ 定义了请求数据的方法，在该方法中，调用了model层去真正进行网络请求，在MvpModel中进行网络请求后，通过MvpCallback回调给MvpPresenter进行数据处理，并调用视图view的方法。
 
+ MvpPresenter继承BasePresenter，并实现MvpContract.Presenter，不需要关注与Activity的绑定、解绑等工作，只需要关注业务逻辑的处理，然后响应给view层，由view层与用户交互。

+ 在MvpModel中成功请求数据后，并没有直接调用MvpPresenter的方法，而是通过回调，让MvpPresenter执行逻辑操作，减少Prsenter与Model的耦合。  

### [BaseActivity](https://github.com/JohnnySwordMan/MvpPractice/blob/master/app/src/main/java/com/jaygege/mvppractice/base/activity/BaseActivity.java)  
+ 定义createPresenter抽象方法，子类实现该方法

+ 在生命周期的方法中，执行presenter的attachView等方法，将Activity与presenter进行绑定、解绑，因此MainActivity不需要关注绑定与解绑等操作。  

### [MainActivity](https://github.com/JohnnySwordMan/MvpPractice/blob/master/app/src/main/java/com/jaygege/mvppractice/MainActivity.java)  
+ 实现createPresenter方法，在该方法中，创建MvpPresenter对象并返回，这样BaseActivity会拿到MvpPresenter对象与Activity进行绑定

+ 关注用户交互，例如点击获取数据按钮，则会调用MvpPresenter中方法，在MvpPresenter中又会调用MvpModel中方法去真正请求网路数据，得到数据后，通过MvpCallback回调给MvpPresenter，在MvpPresenter中进行数据处理，然后针对不同的场景调用MvpView的方法，而Activity实现了MvpView的具体方法，将用户点击按钮后的反馈显示在页面上。这就是一整套的逻辑。
