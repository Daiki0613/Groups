import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Cycle {

  private List<Integer> twoRow; // i gets mapped to twoRow[i]
  private String name;

  public Cycle(List<Integer> twoRow) {
    this(twoRow, "");
  }
  public Cycle(List<Integer> twoRow, String name) {
    this.twoRow = twoRow;
    this.name = name;
  }
  public Cycle(List<List<Integer>> cycle, int n, String name) {
    this.twoRow = getRowNotation(cycle, n);
    this.name = name;
  }
  public Cycle(List<List<Integer>> cycle, int n) {
    this(cycle, n, "");
  }

  private List<Integer> getRowNotation(List<List<Integer>> cycles, int n) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < n; i++) list.add(i);

    if (cycles.size() == 0) return list;
    for (List<Integer> cycle : cycles) {
      for (int i = 0; i < cycle.size()-1; i++) {
        list.set(cycle.get(i)-1, cycle.get(i+1)-1);
      }
      if (cycle.size() > 0) list.set(cycle.get(cycle.size()-1)-1, cycle.get(0)-1);
    }
    return list;
  }
  public int getNext(int i) {
    return twoRow.get(i);
  }

  public int getPrev(int i) {
    for (int j = 0; j < twoRow.size(); j++) {
      if (twoRow.get(j) == i) return j;
    }
    return 0;
  }

  public int getSize() {
    return twoRow.size();
  }

  public List<Integer> getTwoRow() {
    return twoRow;
  }

  public List<List<Integer>> getCycles() {
    boolean[] visited = new boolean[getSize()];
    List<List<Integer>> cycles = new ArrayList<>();

    for (int i = 0; i < visited.length; i++) {
      if (!visited[i]) {
        int cur = i;
        List<Integer> cycle = new ArrayList<>();
        while (!visited[cur]) {
          visited[cur] = true;
          cycle.add(cur);
          cur = getNext(cur);
        }
        cycles.add(cycle);
      }
    }

    List<List<Integer>> res = new ArrayList<>();
    for (List<Integer> cycle : cycles) {
      if (cycle.size() > 1) res.add(cycle);
    }
    return res;
  }
  @Override
  public String toString() {
    return name;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    if (twoRow.equals(((Cycle) obj).twoRow)) return true;
    return false;
  }
}
