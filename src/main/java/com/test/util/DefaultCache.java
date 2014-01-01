package com.test.util;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultCache<K, V> implements Cache<K, V> {

	private static final Logger Log = LoggerFactory
			.getLogger(DefaultCache.class);

	protected Map<K, DefaultCache.CacheObject<V>> map;
	
	protected com.test.util.LinkedList<K> lastAccessedList;
	
	protected com.test.util.LinkedList<K> ageList;
	
	  /**
     * Maximum size in bytes that the cache can grow to.
     */
    private long maxCacheSize;

    /**
     * Maintains the current size of the cache in bytes.
     */
    private int cacheSize = 0;

    /**
     * Maximum length of time objects can exist in cache before expiring.
     */
    protected long maxLifetime;
    
    protected long cacheHits, cacheMisses = 0L;

    /**
     * The name of the cache.
     */
    private String name;
    
    public DefaultCache(String name, long maxSize, long maxLifetime) {
        this.name = name;
        this.maxCacheSize = maxSize;
        this.maxLifetime = maxLifetime;

        // Our primary data structure is a HashMap. The default capacity of 11
        // is too small in almost all cases, so we set it bigger.
        map = new HashMap<K, CacheObject<V>>(103);

        lastAccessedList = new com.test.util.LinkedList<K>();
        ageList = new com.test.util.LinkedList<K>();
    }
    
    public synchronized V put(K key, V value){
    	 // Delete an old entry if it exists.
        V v = remove(key);
    	return null;
    }
    
    public synchronized V remove(Object key) {
        DefaultCache.CacheObject<V> cacheObject = map.get(key);
        //If the object is not in cache, stop trying to remove it.
        if(cacheObject==null){
        	return null;
        }
        //remove from the hash map
        map.remove(key);
        //remove from the cache order list
        cacheObject.lastAccessedListNode.remove();
        cacheObject.ageListNode.remove();
        //remove references to linked list nodes
        cacheObject.lastAccessedListNode = null;
        cacheObject.ageListNode = null;
        cacheSize -= cacheObject.size;
        return cacheObject.object;
    }
    
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setName(String name) {
		// TODO Auto-generated method stub

	}

	public long getMaxCacheSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setMaxCacheSize(int maxSize) {
		// TODO Auto-generated method stub

	}

	public long getMaxLifetime() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setMaxLifetime(long maxLifetime) {
		// TODO Auto-generated method stub

	}

	public int getCacheSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getCacheHits() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getCacheMisses() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Warp for the object that put into cache. It's primary purpose is maintain
	 * references to the lined lists that maintain the create time of the object
	 * and the ordering of the most used objects.
	 * 
	 * @author Li.Yifan
	 * 
	 * @param <V>
	 */
	private static class CacheObject<V> {
		/**
		 * Underlying object warped by CacheObject.
		 */
		public V object;
		/**
		 * The size of Cacheable object. The size of the Cacheable object is
		 * only computed once when it is added to the cache. This makes the
		 * assumption that once objects are added to cache, they are mostly
		 * read-only and that their size does not change significantly over
		 * time.
		 */
		public int size;

		public com.test.util.LinkedList.Node<?> lastAccessedListNode;

		public com.test.util.LinkedList.Node<?> ageListNode;

		public int readCount = 0;

		public CacheObject(V object, int size) {
			this.object = object;
			this.size = size;
		}
	}

}
