import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.routing.Routing

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        staticContent()
        api()
    }
}
