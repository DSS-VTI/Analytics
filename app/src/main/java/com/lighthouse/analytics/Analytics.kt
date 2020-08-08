package com.lighthouse.analytics

import com.lighthouse.analytics.events.base.Event
import com.lighthouse.analytics.exceptions.EventNotTrackedException
/**
 * The *Analytics* class is in charge of tracking any *Event* implementation.
 *
 * @property analyticsServices list of *AnalyticsServices* to trigger for every event
 *
 * @constructor create an instance of the *Analytics* class
 */
class Analytics(val settings: AnalyticsSettings, private vararg val analyticsServices: AnalyticsServices) {

    var exceptionHandler: ExceptionHandler? = null

    init {
        // init all AnalyticsServices
        analyticsServices.forEach { service ->
            if (service.init) {
                service.init()
            }
        }
    }
    
    fun track(vararg events: Event) {

        if (settings.isAnalyticsEnabled.not()) return
        events.forEach { event ->
            analyticsServices.forEach { service ->
                if (settings.enabledKits.isDisabled(service.kit)) return
                if (settings.enabledService.isDisabled(service.serviceName)) return
                try {
                    service.track(event)
                } catch (e: Exception) {
                    exceptionHandler?.onException(EventNotTrackedException(service, event, e))
                }
            }
        }
    }

    fun setKitEnabled(kit: AnalyticsKit, enabled: Boolean) {
        settings.enabledKits[kit] = enabled
    }

    fun setDispatcherEnabled(dispatcherName: String, enabled: Boolean) {
        settings.enabledService[dispatcherName] = enabled
    }

    interface ExceptionHandler {

        fun onException(e: Exception)

    }

}