import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class MyStructure implements IMyStructure {
    private List<INode> nodes = new LinkedList<>();

    @Override
    public INode findByCode(String code) {
        if (code == null){
            throw new IllegalArgumentException("Code is null");
        } else return findByCriteria(p -> code.equals(p.getCode()));
    }

    @Override
    public INode findByRenderer(String renderer) {
        if (renderer == null){
            throw new IllegalArgumentException("Renderer is null");
        } else return findByCriteria(p -> renderer.equals(p.getRenderer()));
    }

    // private method that uses stream to find nodes based on given predicate.
    private INode findByCriteria (Predicate<INode> predicate){
        return nodes.stream()
                .flatMap(INode::toStream)
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    @Override
    public int count() {
        return (int) nodes.stream()
                .flatMap(INode::toStream)
                .count();
    }

    //public method to add new nodes to the list. Needed for tests.
    public void addNode (INode node){
        nodes.add(node);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyStructure that = (MyStructure) o;
        return Objects.equals(nodes, that.nodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes);
    }
}

