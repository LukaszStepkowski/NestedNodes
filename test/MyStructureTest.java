import org.junit.jupiter.api.*;

public class MyStructureTest {

    private static final String CODE1 = "code1";
    private static final String CODE2 = "code2";
    private static final String CODE3 = "code3";
    private static final String CODE4 = "code4";
    private static final String CODE5 = "code5";
    private static final String CODE6 = "code6";

    private static final String RENDERER1 = "renderer1";
    private static final String RENDERER2 = "renderer2";
    private static final String RENDERER3 = "renderer3";
    private static final String RENDERER4 = "renderer4";
    private static final String RENDERER5 = "renderer5";
    private static final String RENDERER6 = "renderer6";

    private static final Node NODE1 = new Node(CODE1, RENDERER1);
    private static final Node NODE2 = new Node(CODE2, RENDERER2);
    private static final CompositeNode COMPOSITE_NODE3 = new CompositeNode(CODE3, RENDERER3);
    private static final Node NODE4 = new Node(CODE4, RENDERER4);
    private static final CompositeNode COMPOSITE_NODE5 = new CompositeNode(CODE5, RENDERER5);
    private static final Node NODE6 = new Node(CODE6, RENDERER6);

    private static final int NODE_COUNT = 6;

    private MyStructure myStructure;

    @BeforeEach
    void setUp() {
        myStructure = new MyStructure();
        myStructure.addNode(NODE1);
        myStructure.addNode(NODE2);
        myStructure.addNode(COMPOSITE_NODE3);
        COMPOSITE_NODE3.addNode(NODE4);
        COMPOSITE_NODE3.addNode(COMPOSITE_NODE5);

        COMPOSITE_NODE5.addNode(NODE6);
    }

    @Test
    void shouldReturnNodeIfCodeMatches(){
        Assertions.assertEquals(NODE1, myStructure.findByCode(CODE1));
    }

    @Test
    void shouldReturnNodeIfRendererMatches(){
        Assertions.assertEquals(NODE2, myStructure.findByRenderer(RENDERER2));
    }

    @Test
    void shouldReturnCompositeNodeByCode(){
        Assertions.assertEquals(COMPOSITE_NODE3, myStructure.findByCode(CODE3));
    }

    @Test
    void shouldReturnNestedCompositeNodeByRenderer(){
        Assertions.assertEquals(COMPOSITE_NODE5, myStructure.findByRenderer(RENDERER5));
    }

    @Test
    void shouldReturnNestedNodeByCode(){
        Assertions.assertEquals(NODE4, myStructure.findByCode(CODE4));
    }

    @Test
    void shouldReturnNestedNodeByRenderer(){
        Assertions.assertEquals(NODE6, myStructure.findByRenderer(RENDERER6));
    }

    @Test
    void shouldReturnExceptionIfCodeIsNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> myStructure.findByCode(null));
    }

    @Test
    void shouldReturnExceptionIfRendererIsNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> myStructure.findByRenderer(null));
    }

    @Test
    void shouldReturnNullIfCodeIsWrong(){
        Assertions.assertNull(myStructure.findByCode("WrongCode"));
    }

    @Test
    void shouldReturnTheNumberOfNodes(){
        Assertions.assertEquals(NODE_COUNT, myStructure.count());
    }
}
