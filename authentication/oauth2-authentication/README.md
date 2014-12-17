<h1>OAuth 2.0 Client</h1>

<p>This project is written against the demo oauth server published here: <a href="https://github.com/sbhachu/JEE/tree/master/gradle-oauth2-demo">https://github.com/sbhachu/JEE/tree/master/gradle-oauth2-demo</a></p>

<p>The core business logic of the application uses Android Annotations and the Spring for Android REST Template.</p>
<p>Although, Android Annotations supports Basic Authentication with Spring REST template, the support for OAuth is limited.</p>

I had problems using the append bearer token functionality, so had to roll my own authentication header manager.

The project is written for Lollipop and uses the new support v7 library for backward compatibility.

<p><b>Known Issues:</b></p>
<ol>
<li>The error messages from the server are in JSON format, and for some reason the RestErrorHandler in Android Annotations strips the body from the response before re-throwing the exception (needs better error management).</li>
<li>The form validation needs to be better, at present only validates that fields aren't empty and that the email addresses are correctly formatted.</li>
</ol>

<p><b>Credits:</b></p>
<ol>
<li><a href="http://androidannotations.org/">Android Annotations</a><br/></li>
<li><a href="http://projects.spring.io/spring-android/">Spring for Android (REST Template)</a><br/></li>
<li><a href="https://github.com/FasterXML/jackson-databind/">Jackson Databind</a><br/></li>
<li><a href="http://commons.apache.org/proper/commons-lang/">Apache Commons Lang</a><br/></li>
</ol>

<p><b>Screenshots:</b></p>
<ol>
<li><b>Login Form</b><br/><br/><img src="https://github.com/sbhachu/Android/blob/master/authentication/oauth2-authentication/images/1.png"/></li><br/>
<li><b>Registration Form</b><br/><br/><img src="https://github.com/sbhachu/Android/blob/master/authentication/oauth2-authentication/images/2.png"/></li><br/>
<li><b>User Authenticated</b><br/><br/><img src="https://github.com/sbhachu/Android/blob/master/authentication/oauth2-authentication/images/3.png"/></li><br/>
<li><b>Fetch Protected Resource</b><br/><br/><img src="https://github.com/sbhachu/Android/blob/master/authentication/oauth2-authentication/images/4.png"/></li><br/>
</ol>