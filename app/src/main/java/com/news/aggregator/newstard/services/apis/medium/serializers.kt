package com.news.aggregator.newstard.services.apis.medium

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

/**
 * Custom serializer to prevent order of fields
 */
class PopularPostGraphQlJsonSerializer: JsonSerializer<PopularPostsGraphQlQuery> {

    override fun serialize(
        src: PopularPostsGraphQlQuery?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        val jsonObject = JsonObject()

        // Add properties in order
        with(jsonObject){
            add("operationName", context!!.serialize(src!!.operationName))
            add("variables", context.serialize(src.variables))
            add("query", context.serialize(src.query))
        }

        return jsonObject
    }
}
