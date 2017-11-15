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