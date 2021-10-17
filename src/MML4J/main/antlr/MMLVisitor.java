// Generated from MML.g4 by ANTLR 4.9.2

package MML4J.main.antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MMLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MMLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code Prog}
	 * labeled alternative in {@link MMLParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(MMLParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link MMLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(MMLParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Priorised}
	 * labeled alternative in {@link MMLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPriorised(MMLParser.PriorisedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Abstraction}
	 * labeled alternative in {@link MMLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbstraction(MMLParser.AbstractionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Application}
	 * labeled alternative in {@link MMLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApplication(MMLParser.ApplicationContext ctx);
}