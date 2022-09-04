package com.task.virtusa.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetData {

    @SerializedName("data")
    @Expose
    val data: Data? = null

    @SerializedName("support")
    @Expose
    val support: Support? = null

    class Support {
        @SerializedName("url")
        @Expose
        var url: String? = null

        @SerializedName("text")
        @Expose
        var text: String? = null
    }

    class Data {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("email")
        @Expose
        var email: String? = null

        @SerializedName("first_name")
        @Expose
        var firstName: String? = null

        @SerializedName("last_name")
        @Expose
        var lastName: String? = null

        @SerializedName("avatar")
        @Expose
        var avatar: String? = null
    }
}