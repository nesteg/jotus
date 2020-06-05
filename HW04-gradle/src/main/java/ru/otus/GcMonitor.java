package ru.otus;

import javax.management.NotificationListener;
import java.lang.management.GarbageCollectorMXBean;
import javax.management.NotificationEmitter;
import java.util.List;

public class GcMonitor {
    public void installListener(NotificationListener listener) {
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            var emitter = (NotificationEmitter) gcbean;
            emitter.addNotificationListener(listener, null, null);
        }
    }
}
