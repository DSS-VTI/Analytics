package com.lighthouse.analytics

import com.lighthouse.analytics.events.ContentViewEvent
import com.lighthouse.analytics.events.CustomEvent
import com.lighthouse.analytics.events.base.Event
import com.lighthouse.analytics.exceptions.UnsupportedEventException

interface AnalyticsServices {

    val init: Boolean

    val kit: AnalyticsKit

    val serviceName: String

    fun init()

    fun trackContentView(contentView: ContentViewEvent)

    fun trackCustomEvent(event: CustomEvent)


    fun track(event: Event) {
        // track the event only if it is not configured as excluded
        if (event.isConsideredIncluded(kit)) {
            var handled = false
            // track for each type differently, including multiple implementations
            if (event is CustomEvent) {
                trackCustomEvent(event)
                handled = true
            }

            if (event is ContentViewEvent) {
                trackContentView(event)
                handled = true
            }

            if (!handled) {
                throw UnsupportedEventException(event)
            }

        }
    }


}

