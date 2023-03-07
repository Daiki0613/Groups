import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SymmetricGroup extends Group<Cycle> {

  public SymmetricGroup(List<Cycle> elements) {
    super(elements);
  }

  @Override
  public Cycle multiply(Cycle a, Cycle b) {
    List<Integer> list = IntStream.range(0, a.getSize()).map(i -> a.getNext(b.getNext(i))).boxed()
        .collect(Collectors.toList());
    return findSame(new Cycle(list));
  }

  @Override
  public Cycle inverse(Cycle a) {
    List<Integer> list = IntStream.range(0, a.getSize()).map(i -> a.getPrev(i)).boxed()
        .collect(Collectors.toList());
    return findSame(new Cycle(list));
  }


}
