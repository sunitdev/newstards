package com.news.aggregator.newstard.models

data class News(var title: String, var upvotes: Int, var link: String)

object Supplier {

    val news_list = listOf(
        News(
            "Making a hand-house for tiny chicks",
            100,
            "https://www.independent.co.uk/news/world/middle-east/trump-iran-news-crisis-rouhani-tensions-middle-east-latest-a8973326.html"
        ),

        News(
            "What If I told you 4 years ago when Hardhome aired that this would be the last time you'd see a Whitewalker General fight ? You'd have called me crazy, right? Like who the fuck could abandon such characters with great build and story ? I mean What kind of stupid motherfucker(s) would ignore them ?",
            200,
            "https://www.independent.co.uk/news/world/middle-east/trump-iran-news-crisis-rouhani-tensions-middle-east-latest-a8973326.html"
        ),

        News(
            "AITA for refusing to pay for my step-daughter's expenses after she got pregnant?",
            12131,
            "https://www.reddit.com/r/AmItheAsshole/comments/c50s4i/aita_for_refusing_to_pay_for_my_stepdaughters/"
        ),

        News(
            "reupload cause the first one wasn’t censored",
            30000,
            "https://www.independent.co.uk/news/world/middle-east/trump-iran-news-crisis-rouhani-tensions-middle-east-latest-a8973326.html"
        ),

        News(
            "‘Biblical Flat Earth Society’ founder is charged with 56 counts of child porn",
            0,
            "https://deadstate.org/biblical-flat-earth-society-founder-is-charged-with-56-counts-of-child-porn/"
        )
    )
}
