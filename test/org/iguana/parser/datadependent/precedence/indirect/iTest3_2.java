package org.iguana.parser.datadependent.precedence.indirect;

import iguana.regex.Char;
import iguana.regex.Seq;
import iguana.utils.input.Input;
import org.iguana.grammar.Grammar;
import org.iguana.grammar.symbol.*;
import org.iguana.grammar.transformation.DesugarPrecedenceAndAssociativity;
import org.iguana.grammar.transformation.EBNFToBNF;
import org.iguana.parser.IguanaParser;
import org.iguana.parsetree.ParseTreeNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static junit.framework.TestCase.assertNotNull;
import static org.iguana.grammar.symbol.LayoutStrategy.NO_LAYOUT;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("unused")
public class iTest3_2 {

    @Test
    public void test() {
         Grammar grammar =

Grammar.builder()
.addEBNFr("E+",new HashSet<String>(Arrays.asList("$E","E")))
// $default$ ::=  {UNDEFINED,-1,NON_REC} PREC(1,1) 
.addRule(Rule.withHead(Nonterminal.builder("$default$").build()).setLayoutStrategy(NO_LAYOUT).setRecursion(Recursion.NON_REC).setiRecursion(Recursion.NON_REC).setLeftEnd("").setRightEnd("").setLeftEnds(new HashSet<String>(Arrays.asList())).setRightEnds(new HashSet<String>(Arrays.asList())).setAssociativity(Associativity.UNDEFINED).setPrecedence(-1).setPrecedenceLevel(PrecedenceLevel.from(1,1,-1,false,false,false,new Integer[]{},false,new Integer[]{})).build())
// E ::= E (+) E  {LEFT,1,LEFT_RIGHT_REC} PREC(1,1) 
.addRule(Rule.withHead(Nonterminal.builder("E").build()).addSymbol(Nonterminal.builder("E").build()).addSymbol(Terminal.builder(Seq.builder(Char.builder(43).build()).build()).build()).addSymbol(Nonterminal.builder("E").build()).setRecursion(Recursion.LEFT_RIGHT_REC).setiRecursion(Recursion.NON_REC).setLeftEnd("E").setRightEnd("E").setLeftEnds(new HashSet<String>(Arrays.asList("$E"))).setRightEnds(new HashSet<String>(Arrays.asList("$E","E+"))).setAssociativity(Associativity.LEFT).setPrecedence(1).setPrecedenceLevel(PrecedenceLevel.from(1,1,-1,false,false,false,new Integer[]{},false,new Integer[]{})).build())
// E ::= (f)  {UNDEFINED,-1,NON_REC} PREC(1,1) 
.addRule(Rule.withHead(Nonterminal.builder("E").build()).addSymbol(Terminal.builder(Seq.builder(Char.builder(102).build()).build()).build()).setRecursion(Recursion.NON_REC).setiRecursion(Recursion.NON_REC).setLeftEnd("$E").setRightEnd("$E").setLeftEnds(new HashSet<String>(Arrays.asList("$E"))).setRightEnds(new HashSet<String>(Arrays.asList("$E","E+"))).setAssociativity(Associativity.UNDEFINED).setPrecedence(-1).setPrecedenceLevel(PrecedenceLevel.from(1,1,-1,false,false,false,new Integer[]{},false,new Integer[]{})).build())
// E ::= (a)  {UNDEFINED,-1,NON_REC} PREC(1,1) 
.addRule(Rule.withHead(Nonterminal.builder("E").build()).addSymbol(Terminal.builder(Seq.builder(Char.builder(97).build()).build()).build()).setRecursion(Recursion.NON_REC).setiRecursion(Recursion.NON_REC).setLeftEnd("$E").setRightEnd("$E").setLeftEnds(new HashSet<String>(Arrays.asList("$E"))).setRightEnds(new HashSet<String>(Arrays.asList("$E","E+"))).setAssociativity(Associativity.UNDEFINED).setPrecedence(-1).setPrecedenceLevel(PrecedenceLevel.from(1,1,-1,false,false,false,new Integer[]{},false,new Integer[]{})).build())
// E ::= E E+  {UNDEFINED,2,LEFT_REC} PREC(2,2) appl
.addRule(Rule.withHead(Nonterminal.builder("E").build()).addSymbol(Nonterminal.builder("E").addExcept("appl").build()).addSymbol(Plus.builder(Nonterminal.builder("E").addExcept("appl").build()).build()).setRecursion(Recursion.LEFT_REC).setiRecursion(Recursion.iRIGHT_REC).setLeftEnd("E").setRightEnd("E+").setLeftEnds(new HashSet<String>(Arrays.asList("$E"))).setRightEnds(new HashSet<String>(Arrays.asList("$E","E+"))).setAssociativity(Associativity.UNDEFINED).setPrecedence(2).setPrecedenceLevel(PrecedenceLevel.from(2,2,2,false,true,false,new Integer[]{},false,new Integer[]{})).setLabel("appl").build())
// S ::= E  {UNDEFINED,-1,NON_REC} PREC(1,1) 
.addRule(Rule.withHead(Nonterminal.builder("S").build()).addSymbol(Nonterminal.builder("E").build()).setRecursion(Recursion.NON_REC).setiRecursion(Recursion.NON_REC).setLeftEnd("E").setRightEnd("E").setLeftEnds(new HashSet<String>(Arrays.asList("$E","E"))).setRightEnds(new HashSet<String>(Arrays.asList("$E","E","E+"))).setAssociativity(Associativity.UNDEFINED).setPrecedence(-1).setPrecedenceLevel(PrecedenceLevel.from(1,1,-1,false,false,false,new Integer[]{},false,new Integer[]{})).build())
.build();

         grammar = new EBNFToBNF().transform(grammar);

         DesugarPrecedenceAndAssociativity precedenceAndAssociativity = new DesugarPrecedenceAndAssociativity();
         precedenceAndAssociativity.setOP2();
         
		 grammar = precedenceAndAssociativity.transform(grammar);
         System.out.println(grammar.toString());

         Input input = Input.fromString("a+fa");

        IguanaParser parser = new IguanaParser(grammar);
        ParseTreeNode result = parser.getParserTree(input);

        assertNotNull(result);
        assertEquals(0, parser.getStatistics().getAmbiguousNodesCount());
    }
}
