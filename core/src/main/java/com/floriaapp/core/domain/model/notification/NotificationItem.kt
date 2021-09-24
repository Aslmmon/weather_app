package com.floriaapp.core.domain.model.notification


import com.google.gson.annotations.SerializedName

data class NotificationItem(
    @SerializedName("body")
    val body: String,
    @SerializedName("created_at")
    val createdAt: CreatedAt,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_read")
    var isRead: Boolean,
    @SerializedName("model")
    val model: String,
    @SerializedName("model_id")
    val modelId: Int,
    @SerializedName("read_at")
    val readAt: String,
    @SerializedName("title")
    val title: String
)