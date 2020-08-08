package com.lighthouse.analytics.exceptions

import com.lighthouse.analytics.events.base.Event

class UnsupportedEventException(event: Event) : UnsupportedOperationException() {
    override val message: String = "couldn't fire \"${event.javaClass.name}\" event"
}
