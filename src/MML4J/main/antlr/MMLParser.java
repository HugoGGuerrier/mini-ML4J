// Generated from MML.g4 by ANTLR 4.9.2

package MML4J.main.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MMLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, FN=5, IDENT=6, IGNORED__=7;
	public static final int
		RULE_program = 0, RULE_expr = 1;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "expr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'{'", "'}'", "'fn'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "FN", "IDENT", "IGNORED__"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MML.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MMLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	 
		public ProgramContext() { }
		public void copyFrom(ProgramContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ProgContext extends ProgramContext {
		public ExprContext body;
		public TerminalNode EOF() { return getToken(MMLParser.EOF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ProgContext(ProgramContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			_localctx = new ProgContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(4);
			((ProgContext)_localctx).body = expr(0);
			setState(5);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VariableContext extends ExprContext {
		public TerminalNode IDENT() { return getToken(MMLParser.IDENT, 0); }
		public VariableContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PriorisedContext extends ExprContext {
		public ExprContext inside;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PriorisedContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitPriorised(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AbstractionContext extends ExprContext {
		public Token param;
		public ExprContext body;
		public TerminalNode FN() { return getToken(MMLParser.FN, 0); }
		public TerminalNode IDENT() { return getToken(MMLParser.IDENT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AbstractionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitAbstraction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ApplicationContext extends ExprContext {
		public ExprContext func;
		public ExprContext arg;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ApplicationContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitApplication(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENT:
				{
				_localctx = new VariableContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(8);
				match(IDENT);
				}
				break;
			case T__0:
				{
				_localctx = new PriorisedContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(9);
				match(T__0);
				setState(10);
				((PriorisedContext)_localctx).inside = expr(0);
				setState(11);
				match(T__1);
				}
				break;
			case FN:
				{
				_localctx = new AbstractionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(13);
				match(FN);
				setState(14);
				match(T__0);
				setState(15);
				((AbstractionContext)_localctx).param = match(IDENT);
				setState(16);
				match(T__1);
				setState(17);
				match(T__2);
				setState(18);
				((AbstractionContext)_localctx).body = expr(0);
				setState(19);
				match(T__3);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(30);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ApplicationContext(new ExprContext(_parentctx, _parentState));
					((ApplicationContext)_localctx).func = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(23);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(24);
					match(T__0);
					setState(25);
					((ApplicationContext)_localctx).arg = expr(0);
					setState(26);
					match(T__1);
					}
					} 
				}
				setState(32);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\t$\4\2\t\2\4\3\t"+
		"\3\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\5\3\30\n\3\3\3\3\3\3\3\3\3\3\3\7\3\37\n\3\f\3\16\3\"\13\3\3\3\2\3\4\4"+
		"\2\4\2\2\2$\2\6\3\2\2\2\4\27\3\2\2\2\6\7\5\4\3\2\7\b\7\2\2\3\b\3\3\2\2"+
		"\2\t\n\b\3\1\2\n\30\7\b\2\2\13\f\7\3\2\2\f\r\5\4\3\2\r\16\7\4\2\2\16\30"+
		"\3\2\2\2\17\20\7\7\2\2\20\21\7\3\2\2\21\22\7\b\2\2\22\23\7\4\2\2\23\24"+
		"\7\5\2\2\24\25\5\4\3\2\25\26\7\6\2\2\26\30\3\2\2\2\27\t\3\2\2\2\27\13"+
		"\3\2\2\2\27\17\3\2\2\2\30 \3\2\2\2\31\32\f\4\2\2\32\33\7\3\2\2\33\34\5"+
		"\4\3\2\34\35\7\4\2\2\35\37\3\2\2\2\36\31\3\2\2\2\37\"\3\2\2\2 \36\3\2"+
		"\2\2 !\3\2\2\2!\5\3\2\2\2\" \3\2\2\2\4\27 ";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}