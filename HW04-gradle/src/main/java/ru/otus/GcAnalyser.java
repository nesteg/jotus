package ru.otus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GcAnalyser {

    private final HashMap<String,Long> pause = new HashMap<>();
    private final HashMap<String,Long> maxs = new HashMap<>();
    private int count = 0;
    private final ArrayList<Integer> ranges = new ArrayList<>(Arrays.asList(100,500));
    private final ArrayList<Integer> timeRanges = new ArrayList<>(Arrays.asList(0,0));


    Long updatePause(String key,Long value) {
        var oldPause =  pause.getOrDefault(key, (long) 0);
        pause.put(key,Long.sum(oldPause,value));
        return pause.get(key);
    }

    int updateCount(){
        return count++;
    }

    long updateMax(String key,long value) {
        var max =  maxs.getOrDefault(key, (long) 0);
        if (value > max) maxs.put(key,value);;
        return maxs.get(key);
    }

    float[] updateTimeRange(long value) {
        float[] result = new float[ranges.size()];
        boolean check = true;
        for(int i=0;i < ranges.size();i++) {
            if(check) {
                if (value <= ranges.get(i)) {
                    var old = timeRanges.get(i);
                    timeRanges.set(i, ++old);
                    check = false;
                }
            }
            result[i] = (timeRanges.get(i)/ (float) count) * 100;
        }
        return result;
    }
}
