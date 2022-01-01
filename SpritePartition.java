// we may partition a list of sprites into subsets corresponding to specific states of the characters
public class SpritePartition {
  public int beg, end;

  public SpritePartition (int beg, int end) {
    this.beg = beg;
    this.end = end;
  }

  public int size () {
    return end-beg+1;
  }
}
