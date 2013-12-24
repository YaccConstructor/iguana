package org.jgll.regex;

import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class Sequence implements RegularExpression {

	private static final long serialVersionUID = 1L;

	private final List<RegularExpression> regularExpressions;
	
	private final NFA nfa;
	
	private final BitSet bitSet;
	
	public Sequence(List<RegularExpression> regularExpressions) {
		this.nfa = createNFA();
		this.regularExpressions = regularExpressions;
		this.bitSet = calculateFirstChars();
	}
	
	public Sequence(RegularExpression...regularExpressions) {
		this(Arrays.asList(regularExpressions));
	}
	
	public List<RegularExpression> getRegularExpressions() {
		return regularExpressions;
	}

	@Override
	public NFA toNFA() {
		return nfa;
	}
	
	private NFA createNFA() {
		State startState = new State();
		State finalState = new State(true);
		
		State currentState = startState;
		
		for(RegularExpression regexp : regularExpressions) {
			currentState.addTransition(Transition.emptyTransition(regexp.toNFA().getStartState()));
			currentState = regexp.toNFA().getEndState();
		}
		
		currentState.addTransition(Transition.emptyTransition(finalState));
		
		return new NFA(startState, finalState);
	}
	
	@Override
	public boolean isNullable() {
		for(RegularExpression regex : regularExpressions) {
			if(!regex.isNullable()) {
				return false;
			}
		}
		return true;
	}
	
	private BitSet calculateFirstChars() {
		BitSet set = new BitSet();
		for(RegularExpression s : regularExpressions) {
			set.or(s.asBitSet());
			
			if(!s.isNullable()) {
				break;
			}
		}
		return set;
	}

	@Override
	public BitSet asBitSet() {
		return bitSet;
	}

}
