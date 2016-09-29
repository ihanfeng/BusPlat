package com.jwetherell.algorithms.data_structures.test.common;

import com.jwetherell.algorithms.data_structures.interfaces.IMap;

public class MapTest {

    /**
     * In computer science, an associative array, map, symbol table, or dictionary is an abstract data 
     * type composed of a collection of (key, value) pairs, such that each possible key appears at most 
     * once in the collection.
     * 
     * http://en.wikipedia.org/wiki/Associative_array
     * 
     * @author Justin Wetherell <phishman3579@gmail.com>
     * 
     * @param map Map to test.
     * @param type Type of data in the map (Either String or Integer).
     * @param name Name used in debug.
     * @param data test data.
     * @param invalid Invalid data which isn't in the data-structure.
     * @return True if the map passes it's invariants tests.
     */
    @SuppressWarnings("unchecked")
    public static <K,V, T extends Comparable<T>> boolean testMap(IMap<K,V> map, Class<T> type, String name,
                                                                 Integer[] data, Integer _invalid) {
        for (int i = 0; i < data.length; i++) {
            Integer item = data[i];
            K k = null;
            V v = null;
            if (type.isAssignableFrom(Integer.class)) {
                k = (K)item;
                v = (V)Utils.parseT(item, type);
            } else if (type.isAssignableFrom(String.class)) {
                k = (K)Utils.parseT(item, type);
                v = (V)item;
            }
            V added = map.put(k,v);
            if ((!map.validate() || (map.size() != (i+1)))) {
                System.err.println(name+" YIKES!! " + item + " caused a size mismatch.");
                Utils.handleError(data,map);
                return false;
            }
            if ((added!=null || !map.contains(k))) {
                System.err.println(name+" YIKES!! " + item + " doesn't exists.");
                Utils.handleError(data,map);
                return false;
            }
        }

        K invalidKey = null;
        if (type.isAssignableFrom(Integer.class)) {
            invalidKey = (K)Utils.parseT(_invalid, type);
        } else if (type.isAssignableFrom(String.class)) {
            invalidKey = (K)Utils.parseT(_invalid, type);
        }
        boolean contains = map.contains(invalidKey);
        V removed = map.remove(invalidKey);
        if (contains || (removed!=null)) {
            System.err.println(name+" invalidity check. contains=" + contains + " removed=" + removed);
            Utils.handleError(_invalid,map);
            return false;
        }

        for (int i = 0; i < data.length; i++) {
            Integer item = data[i];
            K k = null;
            if (type.isAssignableFrom(Integer.class)) {
                k = (K)item;
            } else if (type.isAssignableFrom(String.class)) {
                k = (K)Utils.parseT(item, type);
            }
            removed = map.remove(k);
            if ((!map.validate() || (map.size() != (data.length-(i+1))))) {
                System.err.println(name+" YIKES!! " + item + " caused a size mismatch.");
                Utils.handleError(data,map);
                return false;
            }
            if (map.contains(k)) {
                System.err.println(name+" YIKES!! " + item + " still exists.");
                Utils.handleError(data,map);
                return false;
            }
        }

        // Add half, remove a quarter, add three-quarters, remove all
        int quarter = data.length/4;
        int half = data.length/2;
        for (int i = 0; i < half; i++) {
            Integer item = data[i];
            K k = null;
            V v = null;
            if (type.isAssignableFrom(Integer.class)) {
                k = (K)item;
                v = (V)Utils.parseT(item, type);
            } else if (type.isAssignableFrom(String.class)) {
                k = (K)Utils.parseT(item, type);
                v = (V)item;
            }
            V added = map.put(k,v);
            if ((!map.validate() || (map.size() != (i+1)))) {
                System.err.println(name+" YIKES!! " + item + " caused a size mismatch.");
                Utils.handleError(data,map);
                return false;
            }
            if ((added!=null || !map.contains(k))) {
                System.err.println(name+" YIKES!! " + item + " doesn't exists.");
                Utils.handleError(data,map);
                return false;
            }
        }
        for (int i = (half-1); i >= quarter; i--) {
            Integer item = data[i];
            K k = null;
            if (type.isAssignableFrom(Integer.class)) {
                k = (K)item;
            } else if (type.isAssignableFrom(String.class)) {
                k = (K)Utils.parseT(item, type);
            }
            removed = map.remove(k);
            if ((!map.validate() || (map.size() != i))) {
                System.err.println(name+" YIKES!! " + item + " caused a size mismatch.");
                Utils.handleError(data,map);
                return false;
            }
            if ((removed==null || map.contains(k))) {
                System.err.println(name+" YIKES!! " + item + " still exists.");
                Utils.handleError(data,map);
                return false;
            }
        }
        for (int i = quarter; i < data.length; i++) {
            Integer item = data[i];
            K k = null;
            V v = null;
            if (type.isAssignableFrom(Integer.class)) {
                k = (K)item;
                v = (V)Utils.parseT(item, type);
            } else if (type.isAssignableFrom(String.class)) {
                k = (K)Utils.parseT(item, type);
                v = (V)item;
            }
            V added = map.put(k,v);
            if ((!map.validate() || (map.size() != (i+1)))) {
                System.err.println(name+" YIKES!! " + item + " caused a size mismatch.");
                Utils.handleError(data,map);
                return false;
            }
            if ((added!=null || !map.contains(k))) {
                System.err.println(name+" YIKES!! " + item + " doesn't exists.");
                Utils.handleError(data,map);
                return false;
            }
        }
        for (int i = data.length-1; i >= 0; i--) {
            Integer item = data[i];
            K k = null;
            if (type.isAssignableFrom(Integer.class)) {
                k = (K)item;
            } else if (type.isAssignableFrom(String.class)) {
                k = (K)Utils.parseT(item, type);
            }
            removed = map.remove(k);
            if ((!map.validate() || (map.size() != i))) {
                System.err.println(name+" YIKES!! " + item + " caused a size mismatch.");
                Utils.handleError(data,map);
                return false;
            }
            if ((removed==null || map.contains(k))) {
                System.err.println(name+" YIKES!! " + item + " still exists.");
                Utils.handleError(data,map);
                return false;
            }
        }

        if ((map.size() != 0)) {
            System.err.println(name+" YIKES!! a size mismatch.");
            Utils.handleError(data,map);
            return false;
        }

        return true;
    }
}
