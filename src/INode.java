import java.util.stream.Stream;

interface INode {
    String getCode();
    String getRenderer();

    //Allows to turn nodes to stream.
    Stream<INode> toStream();
}
