package com.lighthouse.analytics.events

import com.lighthouse.analytics.AnalyticsKit
import com.lighthouse.analytics.events.base.Event

interface ContentViewEvent : Event {

    fun getViewName(kit : AnalyticsKit): String

}