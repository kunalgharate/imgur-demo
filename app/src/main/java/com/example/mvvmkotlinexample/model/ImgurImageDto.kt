package com.example.mvvmkotlinexample.model


import com.google.gson.annotations.SerializedName

data class ImgurImageDto(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("status")
    val status: Int, // 200
    @SerializedName("success")
    val success: Boolean // true
) {
    data class Data(
        @SerializedName("account_id")
        val accountId: Int, // 171427605
        @SerializedName("account_url")
        val accountUrl: String, // kunalgharate
        @SerializedName("ad_type")
        val adType: Int, // 0
        @SerializedName("ad_url")
        val adUrl: String,
        @SerializedName("animated")
        val animated: Boolean, // false
        @SerializedName("bandwidth")
        val bandwidth: Int, // 0
        @SerializedName("datetime")
        val datetime: Int, // 1686209161
        @SerializedName("deletehash")
        val deletehash: String, // UfKEtK03r0sfZAR
        @SerializedName("description")
        val description: Any, // null
        @SerializedName("edited")
        val edited: String, // 0
        @SerializedName("favorite")
        val favorite: Boolean, // false
        @SerializedName("has_sound")
        val hasSound: Boolean, // false
        @SerializedName("height")
        val height: Int, // 1037
        @SerializedName("id")
        val id: String, // TBRRRNz
        @SerializedName("in_gallery")
        val inGallery: Boolean, // false
        @SerializedName("in_most_viral")
        val inMostViral: Boolean, // false
        @SerializedName("is_ad")
        val isAd: Boolean, // false
        @SerializedName("link")
        val link: String, // https://i.imgur.com/TBRRRNz.jpg
        @SerializedName("name")
        val name: String, // graudenz18
        @SerializedName("nsfw")
        val nsfw: Any, // null
        @SerializedName("section")
        val section: Any, // null
        @SerializedName("size")
        val size: Int, // 505648
        @SerializedName("tags")
        val tags: List<Any>,
        @SerializedName("title")
        val title: Any, // null
        @SerializedName("type")
        val type: String, // image/jpeg
        @SerializedName("views")
        val views: Int, // 0
        @SerializedName("vote")
        val vote: Any, // null
        @SerializedName("width")
        val width: Int // 1556
    )
}