import java.io.File
import java.nio.charset.Charset

fun loadHtml(fileName: String): String {
    val file = File(fileName)
    return file.readText(Charset.defaultCharset())
}