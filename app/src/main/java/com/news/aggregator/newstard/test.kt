import java.net.URL


class RedditAPI{
    private val BASE_URL = """http://www.reddit.com"""

    fun get_list(): String{
        val response = URL(BASE_URL+"/.json").readText()
        return response
    }
}

public fun main(){
    print(RedditAPI().get_list())
}