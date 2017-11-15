Sample Project for Swoop
========================
Author: Craig Walker (https://github.com/softcraft-development)

This is my submission for the sample programming challenge project for Swoop. In addition to instructions and documentation, I'm also using this Readme file to discuss my philosophies, techniques, thought processes, and decisions. Additional comments are dispersed throughout the code where they are relevant; I recommend reading through the code to fully understand the choices and approaches I've made.

Quick Start
-----------

This is the simplest way to get the project operating. Other methods are described below.

Prerequisites:
1. Docker installed locally
1. An Amdoren API key. You can contact me privately for mine, or get your own at https://www.amdoren.com/developer/signup/

Run the following command, replacing `[your_smdoren_api_key]` with the appropriate key:

    docker run -p 80:8080 -e SWOOP_SAMPLE_AMDOREN_API_KEY=[your_smdoren_api_key] softcraft/swoop-sample:latest
    
Feel free to change `80` in the port mapping above to whatever port you like.

You'll see a console message similar to the following:

    Tomcat started on port(s): 8080 (http)
    
Once you see that, you can visit the following URLs as described in the sample spec:

* [http://localhost/math/add?n1=3&n2=7.5](http://localhost/math/add?n1=3&n2=7.5)
* `POST` a form body with the `n1` and `n2` parameters to `http://localhost/math/add `
* [http://localhost/time/now](http://localhost/time/now)

Be sure to adjust the server name and/or port as necessary to suit your local environment. 

Thoughts on Sample Projects For Job Applications
------------------------------------------------

When hiring software developers and other personnel for critical and technical roles, I believe it's very important to get solid evidence that they can have the skills they claim they do. This is by no means an easy task, but programming challenges like this one are probably the best tool we have for accomplishing this.

That being said, programming challenges are very different than working in the real world. They have a lot of different desires and requirements relative to a real-life project, and some of them are mutually exclusive with others. For instance, sample projects:
* Should be simple enough to be well understood by both the implementer and the reviewers.
* Should be complex enough to allow the implementer to demonstrate the skills necessary in a complex field.
* Should be small enough to implement and review quickly.
* Should be large enough to allow the implementer to demonstrate the breadth of knowledge they have and will need.
* Should incorporate the technologies relevant to the hiring organization.
* Should not get too attached to specific technologies, as technologies are always changing.
* Should use technologies that the implementer is familiar with, so that they can demonstrate their speed & efficiency.
* Should not use only technologies that the implementer is familiar with, so that they can demonstrate their ability to learn new things efficiently.
* Should be as close as possible to how the organization actually handles projects (in terms of specifications and implementation goals).
* Should not get bogged down with details that are relevant to the real-world organization, but not to the sample project. (ex: building with a continuous integration server)
* Should have a specification that gives the implementer enough detail in order to implement the project.
* Should not have such a detailed specification that it removes all ambiguity. Knowing where ambiguity exists & how to deal with ambiguity is an important part of being a software developer. 
* Should not take a lot of effort for the organization to create. Developers are busy people and juggle lots of priorities.

It becomes a very tall order (if not an actual impossibility) to create a sample project spec that can satisfy all of these points. I've done several programming projects like this over the years, and every one of them has had difficulties in one area or another. 
 
So, I tend to treat sample projects less as an actual project to be implemented, and more as a point of discussion. The individual technical decisions that I make on a minute-to-minute basis are less important here than they are for a real-world project. Instead, they become opportunities to describe (typically in textual fashion) the reasoning behind why I made a particular decision.

In this respect, the Swoop challenge was a good one, because on a technical level it was extremely easy and simple. That gives more time for discussion, which is where the real value lies.

You'll note that I talk repeatedly about "real-world apps" in my commentary. I believe it's important to stress when the decisions I make in this project aren't necessarily the same as the ones I would make in an actual product development environment, and to explain why there is a discrepancy. Again, in my opinion, it's the thought process here that matters, not the actual results.

Similarly, I use phrases like "clarifying specifications". In my experience, specification are *always* ambiguous, especially compared to the precise detail required to actually implement a successful software project. In the real world, I'm constantly acquiring accurate details about what is necessary for a project. For a sample project, that's generally not possible, so instead I'll make reasonable assumptions and then document them. Assumptions are a bad thing for a real-world project and should be eliminated as often as possible. 

Notes On New Technologies
-------------------------

While I've spent over a decade been building web applications in Java, I haven't done much recently. Instead I've worked more in Ruby on Rails, ASP.NET (including WebAPI), and Node.js. Creating this project in Java was an interesting homecoming because it gave me a chance to discover how the Java landscape had evolved in the years I was away.

I had these thoughts on technologies that I learned specifically for this sample project:
* Spring Boot seems to be an excellent MVC framework for Java RESTful API applications. It is very clean and unintrusive. The documentation was very well written, and I didn't have much difficulty at all getting it to work as expected. Of course, I did not go deeply into the details of a complex Spring application, but from what I've seen it's a great place to start. I'd put it on par with (or above) all of the other web application frameworks I've used.
* Docker has so far lived up to the hype. Between the functionality, the documentation, and the tooling, it was indeed very easy to build an image and get it to run. Based on what I've read so far, it looks very powerful, and should easily support taking an application from development through to a production deployment in a load-balanced cluster of (micro)services.
* Gradle looks like a pretty effective build & dependency tool for Java. I didn't get very deep into it, but it seems mature & capable enough.
* Jackson is a capable JSON library, although it's not necessarily ideal to always have to declare a class for every JSON document you want to create or consume. There may be better ways out there.
* I wasn't terribly impressed with the Apache HttpClient library, even the Fluent (ie: easy-to-use) version. The documentation was relatively difficult to read, but it was functional at least.

Service Design and Implementation Preferences
---------------------------------------------

When I'm developing software and managing projects, I start with two basic principles:
* KISS: Keep It Simple, Stupid.
* YAGNI: You Ain't Gonna Need It.

Both of these amount to the same thing: don't complicate things unnecessarily, and don't build things in advance of actually needing them. This is even more important for this project with Swoop, which has expressed a preference for faster results over depth and completness. 

With that in mind, there weren't many service design decisions to be made for this project. The specs outlined an extremely simple set of functionality, so there wasn't much room to make many design or architectural choices. (Balancing this with time to implement and evaluate is one of the difficulties in creating an effective sample project specification.) 

I can say that my starting premise for any modern web application is a RESTful web service. REST sets out a path for a standardized way to organize API calls; it makes things very clear. I'm keeping an eye on GraphQL as an alternative, but REST is certainly good enough for now. 

Having said that, the API outlined by the spec is not particularly RESTful, as it deals more with actions/verbs rather than resources/nouns. In a simple project like this, that's fine, but in a more complex project I'd raise the issue for discussion. 
 
Developer.Aero vs Amdoren Time Zone APIs
----------------------------------------
 
I wanted to use the Developer.Aero API as described in the spec. However, the website currently does not provide a mechanism for creating an Application / API key, so I was not able to integrate with it. Instead, I found another free API that returns time & zone information for a location at [Amdoren](https://www.amdoren.com). It's simple enough to create an account & API key at that site. If you like though, I'll share my API key through a private channel.

API Key Security
----------------

The spec makes a comment regarding some of the desired artifacts:

     security choices regarding api keys or credentials
     
Any application secrets or authentication information (user names, passwords, API keys, public/private keys, etc) should *never* be included in any file that goes into source control. If someone *does* commit a secret to source control, then one of two things needs to happen:
1. The secret needs to be invalidated and re-issued.
1. The source history must be rewritten to remove the commit with the secret. Git makes this possible, but it's not terribly easy, and is time-consuming.

With this in mind, the Amdoren API key is acquired from outside the app, and is not stored in either the source code, Docker file, or this Readme file. Unfortunately, this makes the application harder to run, because the executor needs to acquire the key and somehow pass it into the application/container.

I've worked to make this as painless as possible. There are brief instructions on acquiring a key in this document, and I'll just supply my own key in another communication channel. 

The key is fed into the application via the `SWOOP_SAMPLE_AMDOREN_API_KEY` environment variable. This mechanism is universally available across all platforms. Docker lets you pass it in via the `docker run` command, but also gives you more options for customization across a container farm via Docker Compose. (I haven't gone as far as to start building a Docker Compose configuration in the interests of time and simplicity.) For simple key-value pairs, environment variables are quite capable, and are simpler (especially in a Docker environment) than trying to set configuration properties via files. Lastly, configuration via environment variable is the preferred way of configuring apps on Heroku, with which I have a significant amount of experience. Thus, this is my preferred way of storing secrets & other simple configuration information.
