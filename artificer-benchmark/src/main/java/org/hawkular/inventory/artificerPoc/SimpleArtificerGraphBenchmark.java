package org.hawkular.inventory.artificerPoc;

import org.oasis_open.docs.s_ramp.ns.s_ramp_v1.BaseArtifactType;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by jkremser on 1/21/15.
 */
@Fork(1)
@Warmup(iterations = 0, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.SingleShotTime)
public class SimpleArtificerGraphBenchmark extends AbstractArtificerBenchmark {

    @Override
    protected void run() throws Exception {
        testBasicApiWithSout();
//        testBasicBlueprintsApi();
//        testBasicBlueprintsApiForAllNodesFindSomethingComplex();
//        testBasicBlueprintsApiGraphQuerying();
//        testBasicBlueprintsApiForAllEdgesAddTheReAbstractTitanGraphBenchmarkverseOne();
    }

    @Benchmark
    public void testBasicApiWithSout() throws Exception {
        List<BaseArtifactType> artifactTypes = getQueryService().query("/s-ramp/ext/" + DEFAULT_ARTIFACT_TYPE);
        artifactTypes.forEach(node -> {
            System.out.println("\nNode " + node.getName());
            System.out.println("\nuuid " + node.getUuid());
            System.out.println("edges:");
            node.getRelationship().forEach(edgeType -> edgeType.getRelationshipTarget().forEach(edge -> System.out.println("--" + edgeType.getRelationshipType() + "--> " + edge.getValue())));
        });
    }

//    @Benchmark
//    public String testBasicBlueprintsApi() {
//        // we need to use/return the result of computation to avoid dead code elimination
//        final StringBuilder sb = new StringBuilder();
//        List<TitanInventoryNode> nodes = getInventoryGraph().nodes();
//        nodes.forEach(node -> {
//            Iterable<Edge> edges = node.getNode().getEdges(Direction.OUT);
//            edges.forEach(edge -> {
//                sb.append(edge.getLabel());
//                sb.append(edge.getVertex(Direction.IN).getProperty("name").toString());
//            });
//        });
//        return sb.toString();
//    }
//
//    @Benchmark
//    public String testBasicBlueprintsApiForAllNodesFindSomethingComplex() {
//        // we need to use/return the result of computation to avoid dead code elimination
//        final StringBuilder sb = new StringBuilder();
//        List<TitanInventoryNode> nodes = getInventoryGraph().nodes();
//        nodes.forEach(node -> {
//            System.out.println(node.getProperty("name"));
//            if (node.getNode().query().labels("isChildOf").count() > 0) {
//                System.out.println("yes: " + node.getProperty("name"));
//                String foo = StreamSupport.stream(node.getNode().getVertices(Direction.BOTH).spliterator(), false)
//                        .filter(vertex -> vertex.getProperty("name").toString().startsWith("rhq-m"))
//                        .map(vertex -> vertex.getProperty("name").toString())
//                        .sorted()
//                        .collect(Collectors.joining(", "));
//                if (!foo.isEmpty()) {
//                    sb.append(node.getProperty("name")).append(": ").append(foo).append("\n");
//                }
//            }
//        });
//        String ret = sb.toString();
//        System.out.println("result= " + ret);
//        return ret;
//    }
//
//    @Benchmark
//    public String testBasicBlueprintsApiForAllEdgesAddTheReverseOne() {
//        // we need to use/return the result of computation to avoid dead code elimination
//        final StringBuilder sb = new StringBuilder();
//        List<TitanInventoryEdge> edges = getInventoryGraph().edges();
//        String reversedRelations = StreamSupport.stream(edges.spliterator(), false)
//                .map(edge -> {
//                    TitanInventoryNode source = edge.source();
//                    TitanInventoryNode target = edge.target();
//                    // add edge as an explicit side effect of map
//                    TitanInventoryEdge reverseEdge = getInventoryGraph().addEdge(target, source, "reversed relation of " + edge.label());
//                    sb.append(reverseEdge.label());
//                    return reverseEdge.label();
//                })
//                .collect(Collectors.joining("\n"));
//
//        System.out.println(reversedRelations);
//        return sb.toString();
//    }
//
//    @Benchmark
//    public void testBasicBlueprintsApiGraphQuerying() {
//        StreamSupport.stream(getInventoryGraph().getGraph().query().has("name", "RHQ Metrics DS").vertices().spliterator(), false).findFirst().ifPresent(System.out::println);
//    }
}
