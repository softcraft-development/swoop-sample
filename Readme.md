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
