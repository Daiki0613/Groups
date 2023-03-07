import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class Group<T> {

  List<T> elements;

  public Group(List<T> elements) {
    this.elements = elements;
  }

  public abstract T multiply(T a, T b);

  public T multiply(T a, T b, T c) {
    return multiply(a, multiply(b, c));
  }

  public abstract T inverse(T a);

  public T conjugate(T a, T g) {
    return multiply(g, multiply(a, inverse(g)));
  }

  public T getIdentity() {
    return multiply(elements.get(0), inverse(elements.get(0)));
  }

  public boolean isGroup() {
    // check if its closed, and g*g^(-1) are all equal
    for (T e1 : elements) {
      for (T e2 : elements) {
        if (!exists(multiply(e1, e2))) return false;
      }
    }

    T id = getIdentity();
    for (T e : elements) {
      if (!id.equals(multiply(e, inverse(e)))) return false;
    }

    return true;
  }

  public boolean isSubGroup(Group<T> H) {
    // check if its closed, identity exists, and g*g^(-1) are all equal
    for (T e1 : H.elements) {
      for (T e2 : H.elements) {
        if (!H.exists(H.multiply(e1, e2))) return false;
      }
    }

    T id = H.getIdentity();
    if (!this.exists(id)) return false;

    for (T e : elements) {
      if (!id.equals(H.multiply(e, inverse(e)))) return false;
    }

    return true;
  }

  protected boolean exists(T e) {
    for (T t : elements) if (t.equals(e)) return true;
    return false;
  }

  protected T findSame(T e) {
    for (T t : elements) if (t.equals(e)) return t;
    return e;
  }

  private int findSameIndex(T e) {
    for (int i = 0; i < elements.size(); i++) if (elements.get(i).equals(e)) return i;
    return -1;
  }

  public List<T> getConjugateSubgroup(Group<T> H, T g) {
    Set<T> set = new HashSet<>();
    for (T h : H.elements) {
      set.add(conjugate(h, g));
    }
    return set.stream().toList();
  }

  public List<List<T>> getAllConjugateSubgroups(Group<T> H) {
    List<List<T>> list = new ArrayList<>();
    for (T g : elements) {
      list.add(getConjugateSubgroup(H, g));
    }
    return list;
  }

  public List<List<T>> getConjugateClass() {
    // O(n^2) implementation
    int n = elements.size();
    int index = 0;
    int[] category = IntStream.range(0, n).map(x -> -1).toArray();
    for (int i = 0; i < n; i++) {
      if (category[i] == -1) {
        category[i] = index;
        index++;
      }
      for (T e : elements) {
        int other = findSameIndex(conjugate(elements.get(i), e));
        category[other] = category[i];
      }
    }
    List<List<T>> classes = new ArrayList<>();
    for (int i = 0; i < index; i++) classes.add(new ArrayList<>());
    for (int i = 0; i < n; i++) {
      classes.get(category[i]).add(elements.get(i));
    }
    return classes;
  }

  public List<List<T>> getLeftCosets(Group<T> H) {
    return getCosets(H, true);
  }

  public List<List<T>> getRightCosets(Group<T> H) {
    return getCosets(H, false);
  }
  private List<List<T>> getCosets(Group<T> H, boolean left) {
    List<List<T>> res = new ArrayList<>();
    boolean[] visitedG = new boolean[elements.size()];

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < visitedG.length; i++) {
      if (!visitedG[i]) {
        List<T> list = new ArrayList<>();
        queue.add(i);
        while(!queue.isEmpty()) {
          int cur = queue.remove();
          T g = elements.get(cur);
          for (T h : H.elements) {
            T x;
            if (left) x = multiply(g, h);
            else x = multiply(h, g);

            int next = findSameIndex(x);
            if (!visitedG[next]) {
              list.add(x);
              visitedG[next] = true;
              queue.add(next);
            }
          }
        }
        res.add(list);
      }
    }

    return res;
  }

  public boolean isNormalSubgroup(Group<T> H) {
    List<List<T>> left = getLeftCosets(H);
    List<List<T>> right = getRightCosets(H);

    Set<Set<T>> leftSet = new HashSet<>();
    for (List<T> l : left) {
      leftSet.add(l.stream().collect(Collectors.toSet()));
    }

    for (List<T> r : right) {
      boolean res = false;
      Set<T> rset = r.stream().collect(Collectors.toSet());
      for (Set<T> set : leftSet) {
        if (set.equals(rset)) {
          res = true;
          break;
        }
      }
      if (!res) return false;
    }
    return true;
  }
}
