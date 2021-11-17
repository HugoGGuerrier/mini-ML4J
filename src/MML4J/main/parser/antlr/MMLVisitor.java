// Generated from MML.g4 by ANTLR 4.9.2

package MML4J.main.parser.antlr;

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
	 * Visit a parse tree produced by the {@code IfEmpty}
	 * labeled alternative in {@link MMLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfEmpty(MMLParser.IfEmptyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SeqSugar}
	 * labeled alternative in {@link MMLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeqSugar(MMLParser.SeqSugarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Priorised}
	 * labeled alternative in {@link MMLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPriorised(MMLParser.PriorisedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ListSugar}
	 * labeled alternative in {@link MMLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListSugar(MMLParser.ListSugarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Unit}
	 * labeled alternative in {@link MMLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnit(MMLParser.UnitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Abstraction}
	 * labeled alternative in {@link MMLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbstraction(MMLParser.AbstractionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BuildIn}
	 * labeled alternative in {@link MMLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBuildIn(MMLParser.BuildInContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RecAbstraction}
	 * labeled alternative in {@link MMLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecAbstraction(MMLParser.RecAbstractionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Integer}
	 * labeled alternative in {@link MMLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger(MMLParser.IntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Nil}
	 * labeled alternative in {@link MMLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNil(MMLParser.NilContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnOp}
	 * labeled alternative in {@link MMLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnOp(MMLParser.UnOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BinOp}
	 * labeled alternative in {@link MMLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinOp(MMLParser.BinOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfZero}
	 * labeled alternative in {@link MMLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfZero(MMLParser.IfZeroContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Application}
	 * labeled alternative in {@link MMLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApplication(MMLParser.ApplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LetIn}
	 * labeled alternative in {@link MMLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetIn(MMLParser.LetInContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SingleAffect}
	 * labeled alternative in {@link MMLParser#let_affect}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleAffect(MMLParser.SingleAffectContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultipleAffect}
	 * labeled alternative in {@link MMLParser#let_affect}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleAffect(MMLParser.MultipleAffectContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VoidExprs}
	 * labeled alternative in {@link MMLParser#exprs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidExprs(MMLParser.VoidExprsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SingleExprs}
	 * labeled alternative in {@link MMLParser#exprs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleExprs(MMLParser.SingleExprsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiExprs}
	 * labeled alternative in {@link MMLParser#exprs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiExprs(MMLParser.MultiExprsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SingleParams}
	 * labeled alternative in {@link MMLParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleParams(MMLParser.SingleParamsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultipleParams}
	 * labeled alternative in {@link MMLParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleParams(MMLParser.MultipleParamsContext ctx);
}