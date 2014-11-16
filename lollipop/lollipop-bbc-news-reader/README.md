<h1>BBC News Reader Application</h1>

<h4>This version of the BBC News Reader is optimised for Android Lollipop (5.0) and makes use of the new <a href="https://developer.android.com/reference/android/support/v7/widget/RecyclerView.html">RecyclerView</a> and <a href="https://developer.android.com/reference/android/support/v7/widget/CardView.html">CardView</a> widgets in place of a standard <a href="http://developer.android.com/reference/android/widget/ListView.html">ListView</a>.  The standard <a href="http://developer.android.com/reference/android/app/ActionBar.html">ActionBar</a> is also replaced with a <a href="http://developer.android.com/reference/android/support/v7/widget/Toolbar.html">Toolbar</a> according to the new Material design guidelines.</h4>

<p>This application utilises the RSS feeds supplied by the BBC, to display current news, organised by news category.</p>

The core business logic of the application uses Android Annotations and the Spring for Android REST Template. The REST template offers offers support for RSS and Atom through Android ROME Feed Reader, which deserializes RSS XML into simple POJOs.  However, as we're working with Android we can take advantage of <a href="http://developer.android.com/reference/android/os/Parcelable.html">Parcelable</a> objects (which serialize and deserialize faster because they don't require reflection to infer object structure, the structure is explicitly defined).  The XML mapping was done manually using custom objects and the Simple XML framework.


<p>Credits:</p>
<ol>
<li><a href="http://www.bbc.co.uk/news/10628494">BBC RSS News Feeds</a><br/></li>
<li><a href="http://androidannotations.org/">Android Annotations</a><br/></li>
<li><a href="http://projects.spring.io/spring-android/">Spring for Android (REST Template)</a><br/></li>
<li><a href="http://simple.sourceforge.net/">Simple XML Framework</a><br/></li>
<li><a href="http://square.github.io/picasso/">Picasso Image Library</a><br/></li>
<li><a href="http://commons.apache.org/proper/commons-lang/">Apache Commons Lang</a><br/></li>
</ol>