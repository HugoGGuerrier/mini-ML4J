// Generated from MML.g4 by ANTLR 4.9.2

package mml4j.main.parser.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MMLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, FN=11, REC=12, IF_ZERO=13, IF_EMPTY=14, ELSE=15, LET=16, IN=17, 
		NIL=18, UNIT=19, UN_OP=20, BIN_OP=21, BUILD_IN=22, INTEGER=23, IDENT=24, 
		IGNORED__=25;
	public static final int
		RULE_program = 0, RULE_expr = 1, RULE_let_affect = 2, RULE_exprs = 3, 
		RULE_params = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "expr", "let_affect", "exprs", "params"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'['", "']'", "'{'", "'}'", "';'", "'='", "'and'", 
			"','", "'fn'", "'rec'", "'ifz'", "'ifem'", "'else'", "'let'", "'in'", 
			"'nil'", "'()'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "FN", 
			"REC", "IF_ZERO", "IF_EMPTY", "ELSE", "LET", "IN", "NIL", "UNIT", "UN_OP", 
			"BIN_OP", "BUILD_IN", "INTEGER", "IDENT", "IGNORED__"
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
			setState(10);
			((ProgContext)_localctx).body = expr(0);
			setState(11);
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
	public static class SeqSugarContext extends ExprContext {
		public ExprContext ignored;
		public ExprContext real;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public SeqSugarContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitSeqSugar(this);
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
	public static class ListSugarContext extends ExprContext {
		public ExprsContext inside;
		public ExprsContext exprs() {
			return getRuleContext(ExprsContext.class,0);
		}
		public ListSugarContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitListSugar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnitContext extends ExprContext {
		public TerminalNode UNIT() { return getToken(MMLParser.UNIT, 0); }
		public UnitContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitUnit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AbstractionContext extends ExprContext {
		public ParamsContext parameters;
		public ExprContext body;
		public TerminalNode FN() { return getToken(MMLParser.FN, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
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
	public static class BuildInContext extends ExprContext {
		public Token name;
		public ExprsContext arguments;
		public TerminalNode BUILD_IN() { return getToken(MMLParser.BUILD_IN, 0); }
		public ExprsContext exprs() {
			return getRuleContext(ExprsContext.class,0);
		}
		public BuildInContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitBuildIn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RecAbstractionContext extends ExprContext {
		public Token name;
		public Token param;
		public ExprContext body;
		public TerminalNode REC() { return getToken(MMLParser.REC, 0); }
		public List<TerminalNode> IDENT() { return getTokens(MMLParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(MMLParser.IDENT, i);
		}
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
	public static class UnOpContext extends ExprContext {
		public Token op;
		public ExprContext arg;
		public TerminalNode UN_OP() { return getToken(MMLParser.UN_OP, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public UnOpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitUnOp(this);
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
		public Let_affectContext affect;
		public ExprContext body;
		public TerminalNode LET() { return getToken(MMLParser.LET, 0); }
		public TerminalNode IN() { return getToken(MMLParser.IN, 0); }
		public Let_affectContext let_affect() {
			return getRuleContext(Let_affectContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public LetInContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitLetIn(this);
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
			setState(79);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENT:
				{
				_localctx = new VariableContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(14);
				match(IDENT);
				}
				break;
			case INTEGER:
				{
				_localctx = new IntegerContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(15);
				match(INTEGER);
				}
				break;
			case NIL:
				{
				_localctx = new NilContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(16);
				match(NIL);
				}
				break;
			case UNIT:
				{
				_localctx = new UnitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(17);
				match(UNIT);
				}
				break;
			case T__0:
				{
				_localctx = new PriorisedContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(18);
				match(T__0);
				setState(19);
				((PriorisedContext)_localctx).inside = expr(0);
				setState(20);
				match(T__1);
				}
				break;
			case T__2:
				{
				_localctx = new ListSugarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(22);
				match(T__2);
				setState(23);
				((ListSugarContext)_localctx).inside = exprs();
				setState(24);
				match(T__3);
				}
				break;
			case UN_OP:
				{
				_localctx = new UnOpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(26);
				((UnOpContext)_localctx).op = match(UN_OP);
				setState(27);
				((UnOpContext)_localctx).arg = expr(10);
				}
				break;
			case BUILD_IN:
				{
				_localctx = new BuildInContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(28);
				((BuildInContext)_localctx).name = match(BUILD_IN);
				setState(29);
				match(T__0);
				setState(30);
				((BuildInContext)_localctx).arguments = exprs();
				setState(31);
				match(T__1);
				}
				break;
			case IF_ZERO:
				{
				_localctx = new IfZeroContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(33);
				match(IF_ZERO);
				setState(34);
				match(T__0);
				setState(35);
				((IfZeroContext)_localctx).cond = expr(0);
				setState(36);
				match(T__1);
				setState(37);
				match(T__4);
				setState(38);
				((IfZeroContext)_localctx).cons = expr(0);
				setState(39);
				match(T__5);
				setState(40);
				match(ELSE);
				setState(41);
				match(T__4);
				setState(42);
				((IfZeroContext)_localctx).altern = expr(0);
				setState(43);
				match(T__5);
				}
				break;
			case IF_EMPTY:
				{
				_localctx = new IfEmptyContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(45);
				match(IF_EMPTY);
				setState(46);
				match(T__0);
				setState(47);
				((IfEmptyContext)_localctx).cond = expr(0);
				setState(48);
				match(T__1);
				setState(49);
				match(T__4);
				setState(50);
				((IfEmptyContext)_localctx).cons = expr(0);
				setState(51);
				match(T__5);
				setState(52);
				match(ELSE);
				setState(53);
				match(T__4);
				setState(54);
				((IfEmptyContext)_localctx).altern = expr(0);
				setState(55);
				match(T__5);
				}
				break;
			case FN:
				{
				_localctx = new AbstractionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(57);
				match(FN);
				setState(58);
				match(T__0);
				setState(59);
				((AbstractionContext)_localctx).parameters = params();
				setState(60);
				match(T__1);
				setState(61);
				match(T__4);
				setState(62);
				((AbstractionContext)_localctx).body = expr(0);
				setState(63);
				match(T__5);
				}
				break;
			case REC:
				{
				_localctx = new RecAbstractionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(65);
				match(REC);
				setState(66);
				((RecAbstractionContext)_localctx).name = match(IDENT);
				setState(67);
				match(T__0);
				setState(68);
				((RecAbstractionContext)_localctx).param = match(IDENT);
				setState(69);
				match(T__1);
				setState(70);
				match(T__4);
				setState(71);
				((RecAbstractionContext)_localctx).body = expr(0);
				setState(72);
				match(T__5);
				}
				break;
			case LET:
				{
				_localctx = new LetInContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(74);
				match(LET);
				setState(75);
				((LetInContext)_localctx).affect = let_affect();
				setState(76);
				match(IN);
				setState(77);
				((LetInContext)_localctx).body = expr(1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(94);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(92);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new BinOpContext(new ExprContext(_parentctx, _parentState));
						((BinOpContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(81);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(82);
						((BinOpContext)_localctx).op = match(BIN_OP);
						setState(83);
						((BinOpContext)_localctx).right = expr(10);
						}
						break;
					case 2:
						{
						_localctx = new ApplicationContext(new ExprContext(_parentctx, _parentState));
						((ApplicationContext)_localctx).func = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(84);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(85);
						match(T__0);
						setState(86);
						((ApplicationContext)_localctx).arg = expr(0);
						setState(87);
						match(T__1);
						}
						break;
					case 3:
						{
						_localctx = new SeqSugarContext(new ExprContext(_parentctx, _parentState));
						((SeqSugarContext)_localctx).ignored = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(89);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						{
						setState(90);
						match(T__6);
						setState(91);
						((SeqSugarContext)_localctx).real = expr(0);
						}
						}
						break;
					}
					} 
				}
				setState(96);
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

	public static class Let_affectContext extends ParserRuleContext {
		public Let_affectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_let_affect; }
	 
		public Let_affectContext() { }
		public void copyFrom(Let_affectContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MultipleAffectContext extends Let_affectContext {
		public Token name;
		public ExprContext value;
		public Let_affectContext next;
		public TerminalNode IDENT() { return getToken(MMLParser.IDENT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Let_affectContext let_affect() {
			return getRuleContext(Let_affectContext.class,0);
		}
		public MultipleAffectContext(Let_affectContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitMultipleAffect(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SingleAffectContext extends Let_affectContext {
		public Token name;
		public ExprContext value;
		public TerminalNode IDENT() { return getToken(MMLParser.IDENT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public SingleAffectContext(Let_affectContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitSingleAffect(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Let_affectContext let_affect() throws RecognitionException {
		Let_affectContext _localctx = new Let_affectContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_let_affect);
		try {
			setState(106);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				_localctx = new SingleAffectContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				((SingleAffectContext)_localctx).name = match(IDENT);
				setState(98);
				match(T__7);
				setState(99);
				((SingleAffectContext)_localctx).value = expr(0);
				}
				break;
			case 2:
				_localctx = new MultipleAffectContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(100);
				((MultipleAffectContext)_localctx).name = match(IDENT);
				setState(101);
				match(T__7);
				setState(102);
				((MultipleAffectContext)_localctx).value = expr(0);
				setState(103);
				match(T__8);
				setState(104);
				((MultipleAffectContext)_localctx).next = let_affect();
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

	public static class ExprsContext extends ParserRuleContext {
		public ExprsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprs; }
	 
		public ExprsContext() { }
		public void copyFrom(ExprsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SingleExprsContext extends ExprsContext {
		public ExprContext head;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public SingleExprsContext(ExprsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitSingleExprs(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultiExprsContext extends ExprsContext {
		public ExprContext head;
		public ExprsContext tail;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprsContext exprs() {
			return getRuleContext(ExprsContext.class,0);
		}
		public MultiExprsContext(ExprsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitMultiExprs(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VoidExprsContext extends ExprsContext {
		public VoidExprsContext(ExprsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitVoidExprs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprsContext exprs() throws RecognitionException {
		ExprsContext _localctx = new ExprsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_exprs);
		try {
			setState(114);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				_localctx = new VoidExprsContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				_localctx = new SingleExprsContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
				((SingleExprsContext)_localctx).head = expr(0);
				}
				break;
			case 3:
				_localctx = new MultiExprsContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(110);
				((MultiExprsContext)_localctx).head = expr(0);
				setState(111);
				match(T__9);
				setState(112);
				((MultiExprsContext)_localctx).tail = exprs();
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

	public static class ParamsContext extends ParserRuleContext {
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
	 
		public ParamsContext() { }
		public void copyFrom(ParamsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MultipleParamsContext extends ParamsContext {
		public Token head;
		public ParamsContext tail;
		public TerminalNode IDENT() { return getToken(MMLParser.IDENT, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public MultipleParamsContext(ParamsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitMultipleParams(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SingleParamsContext extends ParamsContext {
		public Token head;
		public TerminalNode IDENT() { return getToken(MMLParser.IDENT, 0); }
		public SingleParamsContext(ParamsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MMLVisitor ) return ((MMLVisitor<? extends T>)visitor).visitSingleParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_params);
		try {
			setState(120);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				_localctx = new SingleParamsContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(116);
				((SingleParamsContext)_localctx).head = match(IDENT);
				}
				break;
			case 2:
				_localctx = new MultipleParamsContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(117);
				((MultipleParamsContext)_localctx).head = match(IDENT);
				setState(118);
				match(T__9);
				setState(119);
				((MultipleParamsContext)_localctx).tail = params();
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
			return precpred(_ctx, 9);
		case 1:
			return precpred(_ctx, 3);
		case 2:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\33}\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\5\3R\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\7\3_\n\3\f\3\16\3b\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4"+
		"m\n\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5u\n\5\3\6\3\6\3\6\3\6\5\6{\n\6\3\6\2"+
		"\3\4\7\2\4\6\b\n\2\2\2\u008a\2\f\3\2\2\2\4Q\3\2\2\2\6l\3\2\2\2\bt\3\2"+
		"\2\2\nz\3\2\2\2\f\r\5\4\3\2\r\16\7\2\2\3\16\3\3\2\2\2\17\20\b\3\1\2\20"+
		"R\7\32\2\2\21R\7\31\2\2\22R\7\24\2\2\23R\7\25\2\2\24\25\7\3\2\2\25\26"+
		"\5\4\3\2\26\27\7\4\2\2\27R\3\2\2\2\30\31\7\5\2\2\31\32\5\b\5\2\32\33\7"+
		"\6\2\2\33R\3\2\2\2\34\35\7\26\2\2\35R\5\4\3\f\36\37\7\30\2\2\37 \7\3\2"+
		"\2 !\5\b\5\2!\"\7\4\2\2\"R\3\2\2\2#$\7\17\2\2$%\7\3\2\2%&\5\4\3\2&\'\7"+
		"\4\2\2\'(\7\7\2\2()\5\4\3\2)*\7\b\2\2*+\7\21\2\2+,\7\7\2\2,-\5\4\3\2-"+
		".\7\b\2\2.R\3\2\2\2/\60\7\20\2\2\60\61\7\3\2\2\61\62\5\4\3\2\62\63\7\4"+
		"\2\2\63\64\7\7\2\2\64\65\5\4\3\2\65\66\7\b\2\2\66\67\7\21\2\2\678\7\7"+
		"\2\289\5\4\3\29:\7\b\2\2:R\3\2\2\2;<\7\r\2\2<=\7\3\2\2=>\5\n\6\2>?\7\4"+
		"\2\2?@\7\7\2\2@A\5\4\3\2AB\7\b\2\2BR\3\2\2\2CD\7\16\2\2DE\7\32\2\2EF\7"+
		"\3\2\2FG\7\32\2\2GH\7\4\2\2HI\7\7\2\2IJ\5\4\3\2JK\7\b\2\2KR\3\2\2\2LM"+
		"\7\22\2\2MN\5\6\4\2NO\7\23\2\2OP\5\4\3\3PR\3\2\2\2Q\17\3\2\2\2Q\21\3\2"+
		"\2\2Q\22\3\2\2\2Q\23\3\2\2\2Q\24\3\2\2\2Q\30\3\2\2\2Q\34\3\2\2\2Q\36\3"+
		"\2\2\2Q#\3\2\2\2Q/\3\2\2\2Q;\3\2\2\2QC\3\2\2\2QL\3\2\2\2R`\3\2\2\2ST\f"+
		"\13\2\2TU\7\27\2\2U_\5\4\3\fVW\f\5\2\2WX\7\3\2\2XY\5\4\3\2YZ\7\4\2\2Z"+
		"_\3\2\2\2[\\\f\4\2\2\\]\7\t\2\2]_\5\4\3\2^S\3\2\2\2^V\3\2\2\2^[\3\2\2"+
		"\2_b\3\2\2\2`^\3\2\2\2`a\3\2\2\2a\5\3\2\2\2b`\3\2\2\2cd\7\32\2\2de\7\n"+
		"\2\2em\5\4\3\2fg\7\32\2\2gh\7\n\2\2hi\5\4\3\2ij\7\13\2\2jk\5\6\4\2km\3"+
		"\2\2\2lc\3\2\2\2lf\3\2\2\2m\7\3\2\2\2nu\3\2\2\2ou\5\4\3\2pq\5\4\3\2qr"+
		"\7\f\2\2rs\5\b\5\2su\3\2\2\2tn\3\2\2\2to\3\2\2\2tp\3\2\2\2u\t\3\2\2\2"+
		"v{\7\32\2\2wx\7\32\2\2xy\7\f\2\2y{\5\n\6\2zv\3\2\2\2zw\3\2\2\2{\13\3\2"+
		"\2\2\bQ^`ltz";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}