// Generated from MML.g4 by ANTLR 4.9.2

package MML4J.main.parser.antlr;

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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, FN=7, FN_REC=8, NIL=9, 
		IF_ZERO=10, IF_EMPTY=11, ELSE=12, LET=13, IN=14, BIN_OP=15, BUILD_IN=16, 
		INTEGER=17, IDENT=18, IGNORED__=19;
	public static final int
		RULE_program = 0, RULE_expr = 1, RULE_args = 2;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "expr", "args"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'{'", "'}'", "'='", "','", "'fn'", "'fnrec'", "'nil'", 
			"'ifz'", "'ifem'", "'else'", "'let'", "'in'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "FN", "FN_REC", "NIL", "IF_ZERO", 
			"IF_EMPTY", "ELSE", "LET", "IN", "BIN_OP", "BUILD_IN", "INTEGER", "IDENT", 
			"IGNORED__"
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
			setState(6);
			((ProgContext)_localctx).body = expr(0);
			setState(7);
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
	public static class IntegerContext extends ExprContext {
		public TerminalNode INTEGER() { return getToken(MMLParser.INTEGER, 0); }
		public IntegerContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NilContext extends ExprContext {
		public TerminalNode NIL() { return getToken(MMLParser.NIL, 0); }
		public NilContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitNil(this);
			else return visitor.visitChildren(this);
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
	public static class IfEmptyContext extends ExprContext {
		public ExprContext cond;
		public ExprContext cons;
		public ExprContext altern;
		public TerminalNode IF_EMPTY() { return getToken(MMLParser.IF_EMPTY, 0); }
		public TerminalNode ELSE() { return getToken(MMLParser.ELSE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public IfEmptyContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitIfEmpty(this);
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
	public static class BinOpContext extends ExprContext {
		public ExprContext left;
		public Token op;
		public ExprContext right;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode BIN_OP() { return getToken(MMLParser.BIN_OP, 0); }
		public BinOpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitBinOp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfZeroContext extends ExprContext {
		public ExprContext cond;
		public ExprContext cons;
		public ExprContext altern;
		public TerminalNode IF_ZERO() { return getToken(MMLParser.IF_ZERO, 0); }
		public TerminalNode ELSE() { return getToken(MMLParser.ELSE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public IfZeroContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitIfZero(this);
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
	public static class LetInContext extends ExprContext {
		public Token name;
		public ExprContext value;
		public ExprContext body;
		public TerminalNode LET() { return getToken(MMLParser.LET, 0); }
		public TerminalNode IN() { return getToken(MMLParser.IN, 0); }
		public TerminalNode IDENT() { return getToken(MMLParser.IDENT, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public LetInContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitLetIn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BuildInContext extends ExprContext {
		public Token name;
		public ArgsContext arguments;
		public TerminalNode BUILD_IN() { return getToken(MMLParser.BUILD_IN, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public BuildInContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitBuildIn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RecAbstractionContext extends ExprContext {
		public Token param;
		public ExprContext body;
		public TerminalNode FN_REC() { return getToken(MMLParser.FN_REC, 0); }
		public TerminalNode IDENT() { return getToken(MMLParser.IDENT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public RecAbstractionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitRecAbstraction(this);
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
			setState(69);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENT:
				{
				_localctx = new VariableContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(10);
				match(IDENT);
				}
				break;
			case INTEGER:
				{
				_localctx = new IntegerContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(11);
				match(INTEGER);
				}
				break;
			case NIL:
				{
				_localctx = new NilContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(12);
				match(NIL);
				}
				break;
			case T__0:
				{
				_localctx = new PriorisedContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(13);
				match(T__0);
				setState(14);
				((PriorisedContext)_localctx).inside = expr(0);
				setState(15);
				match(T__1);
				}
				break;
			case BUILD_IN:
				{
				_localctx = new BuildInContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(17);
				((BuildInContext)_localctx).name = match(BUILD_IN);
				setState(18);
				match(T__0);
				setState(19);
				((BuildInContext)_localctx).arguments = args();
				setState(20);
				match(T__1);
				}
				break;
			case IF_ZERO:
				{
				_localctx = new IfZeroContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(22);
				match(IF_ZERO);
				setState(23);
				match(T__0);
				setState(24);
				((IfZeroContext)_localctx).cond = expr(0);
				setState(25);
				match(T__1);
				setState(26);
				match(T__2);
				setState(27);
				((IfZeroContext)_localctx).cons = expr(0);
				setState(28);
				match(T__3);
				setState(29);
				match(ELSE);
				setState(30);
				match(T__2);
				setState(31);
				((IfZeroContext)_localctx).altern = expr(0);
				setState(32);
				match(T__3);
				}
				break;
			case IF_EMPTY:
				{
				_localctx = new IfEmptyContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(34);
				match(IF_EMPTY);
				setState(35);
				match(T__0);
				setState(36);
				((IfEmptyContext)_localctx).cond = expr(0);
				setState(37);
				match(T__1);
				setState(38);
				match(T__2);
				setState(39);
				((IfEmptyContext)_localctx).cons = expr(0);
				setState(40);
				match(T__3);
				setState(41);
				match(ELSE);
				setState(42);
				match(T__2);
				setState(43);
				((IfEmptyContext)_localctx).altern = expr(0);
				setState(44);
				match(T__3);
				}
				break;
			case FN:
				{
				_localctx = new AbstractionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(46);
				match(FN);
				setState(47);
				match(T__0);
				setState(48);
				((AbstractionContext)_localctx).param = match(IDENT);
				setState(49);
				match(T__1);
				setState(50);
				match(T__2);
				setState(51);
				((AbstractionContext)_localctx).body = expr(0);
				setState(52);
				match(T__3);
				}
				break;
			case FN_REC:
				{
				_localctx = new RecAbstractionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(54);
				match(FN_REC);
				setState(55);
				match(T__0);
				setState(56);
				((RecAbstractionContext)_localctx).param = match(IDENT);
				setState(57);
				match(T__1);
				setState(58);
				match(T__2);
				setState(59);
				((RecAbstractionContext)_localctx).body = expr(0);
				setState(60);
				match(T__3);
				}
				break;
			case LET:
				{
				_localctx = new LetInContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(62);
				match(LET);
				setState(63);
				((LetInContext)_localctx).name = match(IDENT);
				setState(64);
				match(T__4);
				setState(65);
				((LetInContext)_localctx).value = expr(0);
				setState(66);
				match(IN);
				setState(67);
				((LetInContext)_localctx).body = expr(1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(81);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(79);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new BinOpContext(new ExprContext(_parentctx, _parentState));
						((BinOpContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(71);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(72);
						((BinOpContext)_localctx).op = match(BIN_OP);
						setState(73);
						((BinOpContext)_localctx).right = expr(9);
						}
						break;
					case 2:
						{
						_localctx = new ApplicationContext(new ExprContext(_parentctx, _parentState));
						((ApplicationContext)_localctx).func = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(74);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(75);
						match(T__0);
						setState(76);
						((ApplicationContext)_localctx).arg = expr(0);
						setState(77);
						match(T__1);
						}
						break;
					}
					} 
				}
				setState(83);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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

	public static class ArgsContext extends ParserRuleContext {
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
	 
		public ArgsContext() { }
		public void copyFrom(ArgsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SoleArgsContext extends ArgsContext {
		public ExprContext arg;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public SoleArgsContext(ArgsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitSoleArgs(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultiArgsContext extends ArgsContext {
		public ExprContext arg;
		public ArgsContext tail;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public MultiArgsContext(ArgsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitMultiArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_args);
		try {
			setState(89);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				_localctx = new SoleArgsContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(84);
				((SoleArgsContext)_localctx).arg = expr(0);
				}
				break;
			case 2:
				_localctx = new MultiArgsContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(85);
				((MultiArgsContext)_localctx).arg = expr(0);
				setState(86);
				match(T__5);
				setState(87);
				((MultiArgsContext)_localctx).tail = args();
				}
				break;
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
			return precpred(_ctx, 8);
		case 1:
			return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\25^\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3H\n\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\7\3R\n\3\f\3\16\3U\13\3\3\4\3\4\3\4\3\4\3\4\5"+
		"\4\\\n\4\3\4\2\3\4\5\2\4\6\2\2\2f\2\b\3\2\2\2\4G\3\2\2\2\6[\3\2\2\2\b"+
		"\t\5\4\3\2\t\n\7\2\2\3\n\3\3\2\2\2\13\f\b\3\1\2\fH\7\24\2\2\rH\7\23\2"+
		"\2\16H\7\13\2\2\17\20\7\3\2\2\20\21\5\4\3\2\21\22\7\4\2\2\22H\3\2\2\2"+
		"\23\24\7\22\2\2\24\25\7\3\2\2\25\26\5\6\4\2\26\27\7\4\2\2\27H\3\2\2\2"+
		"\30\31\7\f\2\2\31\32\7\3\2\2\32\33\5\4\3\2\33\34\7\4\2\2\34\35\7\5\2\2"+
		"\35\36\5\4\3\2\36\37\7\6\2\2\37 \7\16\2\2 !\7\5\2\2!\"\5\4\3\2\"#\7\6"+
		"\2\2#H\3\2\2\2$%\7\r\2\2%&\7\3\2\2&\'\5\4\3\2\'(\7\4\2\2()\7\5\2\2)*\5"+
		"\4\3\2*+\7\6\2\2+,\7\16\2\2,-\7\5\2\2-.\5\4\3\2./\7\6\2\2/H\3\2\2\2\60"+
		"\61\7\t\2\2\61\62\7\3\2\2\62\63\7\24\2\2\63\64\7\4\2\2\64\65\7\5\2\2\65"+
		"\66\5\4\3\2\66\67\7\6\2\2\67H\3\2\2\289\7\n\2\29:\7\3\2\2:;\7\24\2\2;"+
		"<\7\4\2\2<=\7\5\2\2=>\5\4\3\2>?\7\6\2\2?H\3\2\2\2@A\7\17\2\2AB\7\24\2"+
		"\2BC\7\7\2\2CD\5\4\3\2DE\7\20\2\2EF\5\4\3\3FH\3\2\2\2G\13\3\2\2\2G\r\3"+
		"\2\2\2G\16\3\2\2\2G\17\3\2\2\2G\23\3\2\2\2G\30\3\2\2\2G$\3\2\2\2G\60\3"+
		"\2\2\2G8\3\2\2\2G@\3\2\2\2HS\3\2\2\2IJ\f\n\2\2JK\7\21\2\2KR\5\4\3\13L"+
		"M\f\b\2\2MN\7\3\2\2NO\5\4\3\2OP\7\4\2\2PR\3\2\2\2QI\3\2\2\2QL\3\2\2\2"+
		"RU\3\2\2\2SQ\3\2\2\2ST\3\2\2\2T\5\3\2\2\2US\3\2\2\2V\\\5\4\3\2WX\5\4\3"+
		"\2XY\7\b\2\2YZ\5\6\4\2Z\\\3\2\2\2[V\3\2\2\2[W\3\2\2\2\\\7\3\2\2\2\6GQ"+
		"S[";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}