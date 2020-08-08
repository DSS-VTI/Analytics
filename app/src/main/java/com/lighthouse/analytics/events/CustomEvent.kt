package com.lighthouse.analytics.events

import com.lighthouse.analytics.AnalyticsKit
import com.lighthouse.analytics.events.base.Event

interface CustomEvent : Event {

    fun getEventName(kit: AnalyticsKit): String

    fun getParameters(kit: AnalyticsKit): MutableMap<String, Any> {
        return mutableMapOf()
    }

}