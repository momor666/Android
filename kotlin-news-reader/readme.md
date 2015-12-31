<h1>BBC News Reader Application - Kotlin</h1>

<p>This application utilises the RSS feeds supplied by the BBC, to display current news, organised by news category.</p>

I started looking into the viability of Kotlin for developing Android applications, and started porting over my very simple BBC News RSS reader application.
It uses Kotlin and the MVP+I (Model, View, Presenter and Interactor) Pattern, I will be working on this in my free-time with the goal of creating a bootstrap Kotlin project template for future use.

The project contains skeleton base classes (BaseActivity, BasePresenter, BaseRecyclerAdapter, BaseRecyclerView), and sample implementations.

<p><b>Known Issues:</b></p>
The SimpleXML annotations don't play nicely with Kotlin data classes when implementing the Parcelable interface, this needs further investigation as it would be nice to have the entire project written with Kotlin code.  Thankfully because of the interoperability of Kotlin and Java classes, I've re-used the annotated POJOs from my previous project.


