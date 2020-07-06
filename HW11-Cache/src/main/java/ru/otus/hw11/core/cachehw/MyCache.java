package ru.otus.hw11.core.cachehw;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author nesteg
 * created on 04.07.20.
 */
public class MyCache<K, V> implements HwCache<K, V> {
    private final Map<K,V> cache = new WeakHashMap<>();
    private final List<SoftReference<HwListener<K,V>>> listeners = new ArrayList<>();

    @Override
    public void put(K key, V value) {
        cache.put(key,value);
        notify(key,value,"put");
    }

    @Override
    public void remove(K key) {
        var value = cache.remove(key);
        notify(key,value,"remove");
    }

    @Override
    public V get(K key) {
        var value = cache.get(key);
        notify(key,value,"get");
        return value;
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        SoftReference<HwListener<K,V>> soft = new SoftReference<>(listener);
        listeners.add(soft);
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        var iterator = listeners.iterator();
        while(iterator.hasNext()){
            var softListener = iterator.next();
            if (softListener == null){
                iterator.remove();
            }else{
                if (softListener.get()==listener){
                    iterator.remove();
                }
            }
        }
    }

    private void notify(K key, V value, String action){
        var iterator = listeners.iterator();
        while(iterator.hasNext()){
            var softListener = iterator.next();
            if (softListener == null){
                iterator.remove();
            }else{
                var listener = softListener.get();
                if (listener !=null){
                    listener.notify(key,value,action);
                }
            }
        }
    }

}
