package com.floriaapp.core.domain.model.notification


import com.google.gson.annotations.SerializedName

data class NotificationsData(
    @SerializedName("data")
    val `data`: List<NotificationItem>?,
    @SerializedName("links")
    val links: Links,
    @SerializedName("meta")
    val meta: Meta
)