package org.jgll.datadependent.exp;

import org.jgll.datadependent.env.Environment;

public abstract class Expression {
	
	public abstract Object interpret(Environment env);
	
	public boolean isBoolean() {
		return false;
	}
	
	static public abstract class Boolean extends Expression {
		
		public boolean isBoolean() {
			return true;
		}
		
		static public final Boolean TRUE = new Boolean() {

			public Object interpret(Environment env) {
				return true;
			}
		};
		
		static public final Boolean FALSE = new Boolean() {

			public Object interpret(Environment env) {
				return false;
			}
		};
		
	}
	
	public boolean isInteger() {
		return false;
	}
	
	static public class Integer extends Expression {

		private final java.lang.Integer value;
		
		Integer(java.lang.Integer value) {
			this.value = value;
		}
		
		public boolean isInteger() {
			return true;
		}
		
        public Object interpret(Environment env) {
			return value;
		}
		
	}
	
	public boolean isReal() {
		return false;
	}
	
	static public class Real extends Expression {
        
		private final java.lang.Float value;
		
		Real(java.lang.Float value) {
			this.value = value;
		}
		
		public boolean isReal() {
			return true;
		}
		
		public Object interpret(Environment env) {
			return value;
		}
		
	}
	
	public boolean isString() {
		return false;
	}
	
	static public class String extends Expression {
		
		private final java.lang.String value;
		
		String(java.lang.String value) {
			this.value = value;
		}
		
		public boolean isString() {
			return true;
		}

		public Object interpret(Environment env) {
			return value;
		}
	}
	
	static class Call extends Expression {
		
		static public final int PRINTLN = 0;
		
		private final java.lang.String fun;
		private final Expression[] arguments;
		
		Call(java.lang.String fun, Expression... arguments) {
			this.fun = fun;
			this.arguments = arguments;
		}
		
		public Object interpret(Environment env) {
			Object[] values = new Object[arguments.length];
			
			int i = 0;
			while(i < arguments.length) {
				values[i] = arguments[i].interpret(env);
				i++;
			}
			
			return null;
		}
		
	}
	

}
