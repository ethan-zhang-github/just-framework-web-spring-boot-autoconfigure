package pers.just1984.framework.web.autoconfigure.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-02 14:23
 */
public abstract class AbstractMapCache<K, V> implements Cache<V> {

    private ConcurrentHashMap<K, V> map;

    public AbstractMapCache() {
        init();
    }

    public V get(K key) {
        return map.get(key);
    }

    public void put(K key, V value) {
        map.put(key, value);
    }

    public boolean contains(K key) {
        return map.contains(key);
    }

    @Override
    public void init() {
        map = new ConcurrentHashMap<>(1 << 4);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public void refresh() {
        clear();
    }

}
