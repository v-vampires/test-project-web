package com.my.util;

import java.util.HashMap;
import java.util.Map;


/**
 * 定义一个缓存
 * @author Li.Yifan
 *
 * @param <K>
 * @param <V>
 */
public class DefaultCache<K, V> {
	
	private Map<K,DefaultCache.CacheObject<V>> map;
	
	private LinkedList<K> linkedList;
	
	private long cacheSzie;
	public DefaultCache(){
		map = new HashMap<K, DefaultCache.CacheObject<V>>();
		linkedList = new LinkedList<K>();
	}
	
	public void put(K k,V v){
		//如果存在，删除
		//
		DefaultCache.CacheObject<V> cacheObject = new DefaultCache.CacheObject<V>(v);
		map.put(k, cacheObject);
		LinkedList.Node<K> linkNode = linkedList.addFirst(k);
		cacheObject.listNodeRef = linkNode;
		cacheSzie+=cacheObject.size;
		
	}
	
	private static class CacheObject<V>{
		//需要缓存的对象
		public Object object;
		//对象的大小
		public int size;
		//添加到缓存的时间
		public long timestamp;
		
		public LinkedList.Node<?> listNodeRef;
		
		public CacheObject(Object object){
			this.object = object;
			this.timestamp = System.currentTimeMillis();
			//this.size = ???
		}
	}
}
