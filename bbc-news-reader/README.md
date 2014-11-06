<h1>BBC News Reader Application</h1>

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