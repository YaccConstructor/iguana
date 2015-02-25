package org.jgll.util;

import org.jgll.grammar.GrammarGraph;
import org.jgll.parser.gss.GSSNode;
import org.jgll.regex.automaton.Automaton;
import org.jgll.regex.automaton.State;
import org.jgll.sppf.SPPFNode;
import org.jgll.util.collections.IntRangeTree;
import org.jgll.util.collections.RangeTree;
import org.jgll.util.trie.Trie;
import org.jgll.util.visualization.AutomatonToDot;
import org.jgll.util.visualization.GSSToDot;
import org.jgll.util.visualization.GrammarGraphToDot;
import org.jgll.util.visualization.GraphVizUtil;
import org.jgll.util.visualization.RangeTreeToDot;
import org.jgll.util.visualization.SPPFToDot;
import org.jgll.util.visualization.SPPFToDotUnpacked;
import org.jgll.util.visualization.ToDotWithoutIntermediateNodes;
import org.jgll.util.visualization.ToDotWithoutIntermeidateAndLists;
import org.jgll.util.visualization.TrieToDot;


public class Visualization {
	
	public static void generateSPPFGraphWithoutIntermeiateNodes(String outputDir, SPPFNode sppf, Input input) {
		SPPFToDot toDot = new ToDotWithoutIntermediateNodes(input);
		sppf.accept(toDot);
		GraphVizUtil.generateGraph(toDot.getString(), outputDir, "graph");
	}
	
	public static void generateSPPFGraph(String outputDir, SPPFNode sppf, Input input) {
		SPPFToDot toDot = new SPPFToDot(input);
		sppf.accept(toDot);
		GraphVizUtil.generateGraph(toDot.getString(), outputDir, "graph");
	}
	
	public static void generateSPPFWithNonterminalNodesOnly(String outputDir, SPPFNode sppf, Input input) {
		SPPFToDot toDot = new ToDotWithoutIntermeidateAndLists(input);
		sppf.accept(toDot);
		GraphVizUtil.generateGraph(toDot.getString(), outputDir, "graph");
	}
	
	public static void generateGSSGraph(String outputDir, Iterable<GSSNode> gssNodes) {
		GSSToDot toDot = new GSSToDot();
		toDot.execute(gssNodes);
		GraphVizUtil.generateGraph(toDot.getString(), outputDir, "gss");
	}
	
	public static void generateSPPFNodesUnPacked(String outputDir, SPPFNode node, Input input) {
		SPPFToDotUnpacked toDot = new SPPFToDotUnpacked(input);
		toDot.visit(node);
		int i = 0;
		for(String s : toDot.getResult()) {
			GraphVizUtil.generateGraph(s, outputDir, "sppf-" + ++i);
		}
	}
	
	public static void generateAutomatonGraph(String outputDir, Automaton automaton) {
		generateAutomatonGraph(outputDir, automaton.getStartState());
	}
	
	public static void generateAutomatonGraph(String outputDir, State startState) {
		String dot = AutomatonToDot.toDot(startState);
		GraphVizUtil.generateGraph(dot, outputDir, "automaton", GraphVizUtil.LEFT_TO_RIGHT);
	}
	
	public static <T> void generateRangeTree(String outputDir, RangeTree<T> t) {
		GraphVizUtil.generateGraph(RangeTreeToDot.toDot(t), outputDir, "rangeTree");
	}
	
	public static <T> void generateRangeTree(String outputDir, IntRangeTree t) {
		GraphVizUtil.generateGraph(RangeTreeToDot.toDot(t), outputDir, "rangeTree");
	}
	
	public static void generateGrammarGraph(String outputDir, GrammarGraph graph) {
		String dot = GrammarGraphToDot.toDot(graph);
		GraphVizUtil.generateGraph(dot, outputDir, "grammar", GraphVizUtil.LEFT_TO_RIGHT);
	}	
	
	public static <T> void generateTrieGraph(String outputDir, Trie<T> trie) {
		String dot = new TrieToDot<>(trie).toString();
		GraphVizUtil.generateGraph(dot, outputDir, "trie");
	}

}
