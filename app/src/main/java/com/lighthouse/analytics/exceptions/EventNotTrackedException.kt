package com.lighthouse.analytics.exceptions

import com.lighthouse.analytics.AnalyticsServices
import com.lighthouse.analytics.events.base.Event

class EventNotTrackedException(message: String?, cause: Throwable?) : RuntimeException(message, cause) {
    constructor(analyticsServices: AnalyticsServices, event: Event, t: Throwable) : this("${analyticsServices.serviceName} Services couldn't fire \"${event.javaClass.name}\" event", t)
}