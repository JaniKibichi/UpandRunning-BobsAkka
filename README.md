# Up And Running - Bobs Akka {Ticket Selling App}
We test drive AKKA by creating the first APP - A ticket selling app. Notably. the following come together for this App:
- We will deploy the app to heroku. For this reason, we create a docker container to host the heroku tool belt
- We underpin the AKKA framework on Akka Version 2.4.9, and may push that higher up.
- Create the file: <b>com.github.janikibichi.bobsAkka.upandrunning.Main.scala<b>
- Create the file: <b>com.github.janikibichi.bobsAkka.upandrunning.RestApi.scala<b>
- Create the file: <b>com.github.janikibichi.bobsAkka.upandrunning.BoxOffice.scala<b>
- Create the file: <b>com.github.janikibichi.bobsAkka.upandrunning.TicketSeller.scala<b>
- Create the file: <b>com.github.janikibichi.bobsAkka.upandrunning.EventMarshalling.scala<b>
<br>
-Branch out to explore TDD with actors
````
git checkout -b tdd_with_actors master
````
- Create the file in the src/test:<b>com.github.janikibichi.bobsAkka.upandrunning.SilentTestActor.scala</b>
- Create the file in the src/test:<b>com.github.janikibichi.bobsAkka.upandrunning.FilteringTestActor.scala</b>
- Create the file in the src/test:<b>com.github.janikibichi.bobsAkka.upandrunning.SendingActorTest.scala</b>
- Create the file in the src/test:<b>com.github.janikibichi.bobsAkka.upandrunning.GreeterTest.scala</b>
-In the src/main module:
- Create the file: <b>com.github.janikibichi.bobsAkka.upandrunning.FilteringActor.scala</b>
- Create the file: <b>com.github.janikibichi.bobsAkka.upandrunning.Greeter.scala</b>
- Create the file: <b>com.github.janikibichi.bobsAkka.upandrunning.SendingActor.scala</b>
- Create the file: <b>com.github.janikibichi.bobsAkka.upandrunning.SilentActor.scala</b>
