package BusinessLayer.Tiles.VisitorPattern;

public interface IVisited {
    /**
     * accept function as part of the visitor pattern
     * */
    public void accept(IVisitor visitor);
}
