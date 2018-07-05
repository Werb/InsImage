import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/") {
            call.respondText(loadHtml("html/main.html"), ContentType.Text.Html)
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
}
