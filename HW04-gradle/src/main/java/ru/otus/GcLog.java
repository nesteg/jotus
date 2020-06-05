package ru.otus;

import com.sun.management.GarbageCollectionNotificationInfo;

public class GcLog {
        private  long startTime;
        public void logGc(GarbageCollectionNotificationInfo info, long total, long max, float[] range) {

            var gctype=info.getGcAction();
            var duration = info.getGcInfo().getDuration();
            if ("end of minor GC".equals(gctype)) {
                gctype = "Young Gen GC";
            } else if ("end of major GC".equals(gctype)) {
                gctype = "Old Gen GC";
            }
            System.out.println();
            System.out.println("elapsed time " + (System.currentTimeMillis() - startTime) + " " + info.getGcInfo().getId()+ " " + info.getGcName() +  " " + duration + " ms "
                    + "total=" + total + " ms " + "max= " + max + " ms " + "interval 0-100 ms = " + range[0]
                    + "% "+ "interval 100-500 ms = " + range[1] + "% ");
        }

        public void setStarttime(long value){
            startTime = value;
        }
 }
