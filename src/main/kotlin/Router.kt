import io.ktor.application.call
import io.ktor.content.resource
import io.ktor.content.resources
import io.ktor.content.static
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post

fun Route.staticContent() {
    static {
        resource("/", "main.html")
        resource("*", "main.html")
        static("static") {
            resources("static")
        }
    }
}

fun Route.api() {
    get("/") {
        call.respondRedirect("static/main.html", true)
    }
    post("/ins"){
        val receive = call.receiveParameters()
        val share = receive["share"]
        share?.let {
            val insUrl = InsUrlParser.insUrl(it)
            call.respondText(insUrl, ContentType.Text.Html)
        } ?: run {
            call.respond(HttpStatusCode.NotFound, "404")
        }
    }
}