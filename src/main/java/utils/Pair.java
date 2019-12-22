package utils;

import java.util.Set;
import java.util.HashSet;

 public class Pair<T> {
     protected final Set<T> set;

     public Pair(T a, T b) {
          set = new HashSet<T>();
          set.add(a);
          set.add(b);
     }

     public boolean equals(Object o) {
          if (!(o instanceof Pair))
            return false;

           Pair<T> otherPair = (Pair<T>) o;

           return set.equals(otherPair.set);

     }

     public int hashCode() {
         return set.hashCode();
     }
}
