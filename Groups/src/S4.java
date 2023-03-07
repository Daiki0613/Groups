import java.util.Arrays;
import java.util.List;

public class S4 extends SymmetricGroup {

  public static void main(String[] args) {
    S4 s4 = new S4();
    List<List<Cycle>> list = s4.getConjugateClass();

    System.out.println(list.get(1).get(2).getCycles());
    System.out.println(list);
  }

  public S4(List<Cycle> elements) {
    super(elements);
  }

  public S4() {
    super(G);
  }

  public static List<Cycle> G = List.of(
      new Cycle(List.of(List.of()), 4, "g1"),

      new Cycle(List.of(List.of(1, 2)), 4, "g2-1"),
      new Cycle(List.of(List.of(1, 3)), 4, "g2-2"),
      new Cycle(List.of(List.of(1, 4)), 4, "g2-3"),
      new Cycle(List.of(List.of(2, 3)), 4, "g2-4"),
      new Cycle(List.of(List.of(2, 4)), 4, "g2-5"),
      new Cycle(List.of(List.of(3, 4)), 4, "g2-6"),


      new Cycle(List.of(List.of(1, 2), List.of(3, 4)), 4, "g22-1"),
      new Cycle(List.of(List.of(1, 3), List.of(2, 4)), 4, "g22-2"),
      new Cycle(List.of(List.of(1, 4), List.of(2, 3)), 4, "g22-3"),

      new Cycle(List.of(List.of(1, 2, 3)), 4, "g3-1"),
      new Cycle(List.of(List.of(1, 3, 2)), 4, "g3-2"),
      new Cycle(List.of(List.of(2, 3, 4)), 4, "g3-3"),
      new Cycle(List.of(List.of(2, 4, 3)), 4, "g3-4"),
      new Cycle(List.of(List.of(3, 4, 1)), 4, "g3-5"),
      new Cycle(List.of(List.of(3, 1, 4)), 4, "g3-6"),
      new Cycle(List.of(List.of(4, 1, 2)), 4, "g3-7"),
      new Cycle(List.of(List.of(4, 2, 1)), 4, "g3-8"),

      new Cycle(List.of(List.of(1, 2, 3, 4)), 4, "g4-1"),
      new Cycle(List.of(List.of(1, 2, 4, 3)), 4, "g4-2"),
      new Cycle(List.of(List.of(1, 3, 2, 4)), 4, "g4-3"),
      new Cycle(List.of(List.of(1, 3, 4, 2)), 4, "g4-4"),
      new Cycle(List.of(List.of(1, 4, 2, 3)), 4, "g4-5"),
      new Cycle(List.of(List.of(1, 4, 3, 2)), 4, "g4-6")
  );
}
