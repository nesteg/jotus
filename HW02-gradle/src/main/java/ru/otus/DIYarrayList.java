package ru.otus;

import java.util.*;

public class DIYarrayList<E> extends AbstractList<E> implements List<E>{
   transient Object[] data;
   private int sz;
   final static private int default_capacity = 10;
   final static private Object[] empty_array = {};
   final static int MAX_ARRAY_SIZE = Integer.MAX_VALUE-8;
   public DIYarrayList(){
       sz = 0;
       data = new Object[default_capacity];
   }


   @Override
   public int size() {
       return sz;
   }

   @Override
   public boolean isEmpty() {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean contains(Object o) {
      throw new UnsupportedOperationException();
   }

   @Override
   public Iterator<E> iterator() {
      throw new UnsupportedOperationException();
   }

   @Override
   public Object[] toArray() {
      return Arrays.copyOfRange(data,0,sz);
   }

   @Override
   public <T> T[] toArray(T[] a) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean add(E e) {
      changeCapacity(sz+1);
      data[sz++] = e;
      return true;
   }

   @Override
   public boolean remove(Object o) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean containsAll(Collection<?> c)  {
     throw new UnsupportedOperationException();
   }

   @Override
   public boolean addAll(Collection<? extends E> c) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean addAll(int index, Collection<? extends E> c) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean removeAll(Collection<?> c) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean retainAll(Collection<?> c) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void clear() {
      throw new UnsupportedOperationException();
   }

   @Override
   @SuppressWarnings("unchecked")
   public E get(int index) {
       if (index > sz-1) throw new IndexOutOfBoundsException(
               String.format("Index %d out of bounds for length %d.", index, sz));
       return (E)data[index];

   }

   @Override
   public E set(int index, E element) {
      if (index > sz-1) throw new IndexOutOfBoundsException(
              String.format("Index %d out of bounds for length %d.", index, sz));
      data[index]=element;
      return element;
   }

   @Override
   public void add(int index, E element) {
      throw new UnsupportedOperationException();
   }

   @Override
   public E remove(int index) {
      throw new UnsupportedOperationException("Method remove not implements");
   }

   @Override
   public int indexOf(Object o) {
      throw new UnsupportedOperationException();
   }

   @Override
   public int lastIndexOf(Object o) {
      throw new UnsupportedOperationException();
   }

   @Override
   public ListIterator<E> listIterator() {

      ListIterator<E> lit = new ListIterator<>() {
         private int index=-1;
         @Override
         public boolean hasNext() {
            throw new UnsupportedOperationException();
         }

         @Override
         @SuppressWarnings("unchecked")
         public E next() {
            return (E)data[++index];
         }

         @Override
         public boolean hasPrevious() {
            throw new UnsupportedOperationException();
         }

         @Override
         public E previous() {
            throw new UnsupportedOperationException();
         }

         @Override
         public int nextIndex() {
            throw new UnsupportedOperationException();
         }

         @Override
         public int previousIndex() {
            throw new UnsupportedOperationException();
         }

         @Override
         public void remove() {
              throw new UnsupportedOperationException();
         }

         @Override
         public void set(E e) {
            data[index] = e;

         }

         @Override
         public void add(E e) {
            throw new UnsupportedOperationException();
         }
      };
      return lit;
   }

   @Override
   public ListIterator<E> listIterator(int index) {
      throw new UnsupportedOperationException();
   }

   @Override
   public List<E> subList(int fromIndex, int toIndex) {
      throw new UnsupportedOperationException();
   }

   private void changeCapacity(int mincap){
      if (data ==empty_array) {
         mincap = Math.max( default_capacity, mincap);
      }
      changeExplicitCapacity(mincap);
   }

   private void changeExplicitCapacity(int mincap) {
        modCount++;
      if (mincap - data.length > 0)
         grow(mincap);
   }

   private void grow(int mincap) {
      int oldcap = data.length;
      int newcap = oldcap + (oldcap >> 1);
      if (newcap - mincap < 0)
         newcap = mincap;
      if (newcap - MAX_ARRAY_SIZE > 0)
         newcap = hugeCapacity(mincap);
      data = Arrays.copyOf(data, newcap);
   }

   private int hugeCapacity(int mincap) {
      if (mincap < 0) // overflow
         throw new OutOfMemoryError();
      return (mincap > MAX_ARRAY_SIZE) ?
                          Integer.MAX_VALUE :
                          MAX_ARRAY_SIZE;
   }
}

