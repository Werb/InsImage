import org.jsoup.Jsoup
import org.jsoup.nodes.Document

object InsUrlParser {


    fun insUrl(url: String): String {
        val document: Document = Jsoup.connect(url).userAgent("Mozilla").get()
        val meta = document.select("meta[property=og:image]")
        return meta.attr("content")
    }

}