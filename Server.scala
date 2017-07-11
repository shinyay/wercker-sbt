import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.ContentTypes
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer

object Server {

  implicit val system = ActorSystem("simple-rest-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val route: Route = {
    path("") {
      get {
        complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "Hello!"))
      }
    }
  }

  def main(args: Array[String]): Unit = {
    Http().bindAndHandle(route, "0.0.0.0", 9000)
  }

}
