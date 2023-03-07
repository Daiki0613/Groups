import java.util.Arrays;
import java.util.List;

public class S3 extends SymmetricGroup {

  public static void main(String[] args) {
    S3 s3 = new S3();
    //List<List<Cycle>> list = s3.getConjugateClass();
    System.out.println(s3.getAllConjugateSubgroups(H5));

    System.out.println(s3.isSubGroup(H5));

    System.out.println(s3.getLeftCosets(H5));
    System.out.println(s3.getRightCosets(H5));

    System.out.println(s3.isNormalSubgroup(H5));

  }

  public S3(List<Cycle> elements) {
    super(elements);
  }

  public S3() {
    super(G);
  }
  public static Cycle g1 = new Cycle(List.of(0, 1, 2), "g1");
  public static Cycle g2 = new Cycle(List.of(1, 0, 2), "g2");
  public static Cycle g3 = new Cycle(List.of(0, 2, 1), "g3");
  public static Cycle g4 = new Cycle(List.of(2, 1, 0), "g4");
  public static Cycle g5 = new Cycle(List.of(1, 2, 0), "g5");
  public static Cycle g6 = new Cycle(List.of(2, 0, 1), "g6");

  public static List<Cycle> G = List.of(g1, g2, g3, g4, g5, g6);

  // All subgroups of G
  public static Group<Cycle> H1 = new S3(List.of(g1));
  public static Group<Cycle> H2 = new S3(List.of(g1, g2));
  public static Group<Cycle> H3 = new S3(List.of(g1, g3));
  public static Group<Cycle> H4 = new S3(List.of(g1, g4));
  public static Group<Cycle> H5 = new S3(List.of(g1, g5, g6));
  public static Group<Cycle> H6 = new S3(G);
}
