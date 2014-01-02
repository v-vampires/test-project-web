package com.my.util;



public class LinkedList<E> {
	
	/**
	 * The root of the list keeps a reference to both the first and 
	 * last elements of the list.
	 */
	private Node<E> header;
	/**
	 * Creates a new linkedList
	 */
	public LinkedList(){
		header = new Node<E>();
	}
	/**
	 * Add a node to the beginning of the list.
	 * @param node
	 * @return
	 */
	public Node<E> addFirst(Node<E> node){
		return node.insert(header.next, header.previous);
	}
	
	public Node<E> addFirst(E object){
		return new Node<E>(object,header.next,header.previous);
	}
	

	public Node<E> getLast() {
		Node<E> node = header.previous;
		if(node==null){
			return null;
		}
		return node;
	}
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
			this.previous = this.next = this;
		}
		public Node(E object, Node<E> next, Node<E> previous) {
			this.object = object;
			if(next!=null && previous!=null){
				this.insert(next, previous);
			}
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
