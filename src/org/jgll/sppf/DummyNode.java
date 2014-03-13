package org.jgll.sppf;

import java.util.Collections;

import org.jgll.traversal.SPPFVisitor;

/**
 * 
 * @author Ali Afroozeh
 *
 */
public class DummyNode extends SPPFNode {
	
	private static DummyNode instance;
	
	public static DummyNode getInstance() {
		if(instance == null) {
			instance = new DummyNode();
		}
		return instance;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof DummyNode;
	}
	
	@Override
	public int hashCode() {
		return 16769023;
	}
	
	@Override
	public int getLeftExtent() {
		return -1;
	}

	@Override
	public int getRightExtent() {
		return -1;
	}
	
	@Override
	public int getId() {
		return -1;
	}

	@Override
	public SPPFNode getChildAt(int index) {
		return null;
	}

	@Override
	public int childrenCount() {
		return 0;
	}

	@Override
	public Iterable<SPPFNode> getChildren() {
		return Collections.emptyList();
	}
	
	@Override
	public String toString() {
		return "$";
	}

	@Override
	public boolean isAmbiguous() {
		return false;
	}

	@Override
	public void accept(SPPFVisitor visitAction) {
		// do nothing
	}

	@Override
	public SPPFNode getLastChild() {
		return null;
	}

	@Override
	public SPPFNode getFirstChild() {
		return null;
	}

	@Override
	public int getCountAmbiguousNodes() {
		return 0;
	}

}
