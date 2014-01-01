package com.test.util;


public class LinkedList<E> {
	
	
	static class Node<E> {
		public Node<E> previous;
		public Node<E> next;
		public E object;
		/**
		 * It maintains a timestamp of when a Cacheable object added to the
		 * cache.
		 */
		public long timestamp;
		/**
		 * Constructs of an self-reference node 
		 */
		public Node() {
			previous = next = this;
		}
		/**
		 * Removes this node from the linkedList.
		 * @return this node; next and previous references dropped.
		 */
		public Node<E> remove(){
			this.previous.next = this.next;
			this.next.previous = this.previous;
			this.previous = this.next = null;
			return this;
		}
		/**
		 * Inserts this node to the linkedList.
		 * @param next 
		 * @param previous
		 * @return This node, updated to reflect previous/next changes
		 */
		public Node<E> insert(Node<E> next,Node<E> previous){
			this.next = next;
			this.previous = previous;
			next.previous = previous.next = this;
			return this;
		}
	}
}
