package ru.otus;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;

public class GcListener implements NotificationListener {
    private GcAnalyser analyser;
    private GcLog log;

    public GcListener(GcAnalyser analyser,GcLog log) {
        this.analyser=analyser;
        this.log=log;
    }

    @Override
    public void handleNotification(Notification notification, Object handback) {
        if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
            var info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
            var gctype = info.getGcAction();
            var duration = info.getGcInfo().getDuration();
            var total = analyser.updatePause(gctype,duration);
            analyser.updateCount();
            var max = analyser.updateMax(gctype,duration);
            var range = analyser.updateTimeRange(duration);
            log.logGc(info,total,max,range);

        }
    }
}
