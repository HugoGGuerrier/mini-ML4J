// Generated from MML.g4 by ANTLR 4.9.2

package MML4J.main.parser.antlr;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MMLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, FN=9, 
		REC=10, IF_ZERO=11, IF_EMPTY=12, ELSE=13, LET=14, IN=15, NIL=16, UNIT=17, 
		UN_OP=18, BIN_OP=19, BUILD_IN=20, INTEGER=21, IDENT=22, IGNORED__=23;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "FN", 
			"REC", "IF_ZERO", "IF_EMPTY", "ELSE", "LET", "IN", "NIL", "UNIT", "UN_OP", 
			"BIN_OP", "BUILD_IN", "INTEGER", "IDENT", "IGNORED__"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'['", "']'", "'{'", "'}'", "'='", "','", "'fn'", 
			"'rec'", "'ifz'", "'ifem'", "'else'", "'let'", "'in'", "'nil'", "'()'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "FN", "REC", "IF_ZERO", 
			"IF_EMPTY", "ELSE", "LET", "IN", "NIL", "UNIT", "UN_OP", "BIN_OP", "BUILD_IN", 
			"INTEGER", "IDENT", "IGNORED__"
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


	public MMLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MML.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\31\u008a\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2"+
		"\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3"+
		"\22\3\22\3\22\3\23\3\23\3\24\3\24\3\24\5\24j\n\24\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25x\n\25\3\26\5\26{\n\26\3"+
		"\26\6\26~\n\26\r\26\16\26\177\3\27\6\27\u0083\n\27\r\27\16\27\u0084\3"+
		"\30\3\30\3\30\3\30\2\2\31\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\3\2\7"+
		"\4\2##BB\4\2--//\3\2\62;\5\2C\\aac|\5\2\13\f\17\17\"\"\2\u008f\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3"+
		"\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\3\61\3"+
		"\2\2\2\5\63\3\2\2\2\7\65\3\2\2\2\t\67\3\2\2\2\139\3\2\2\2\r;\3\2\2\2\17"+
		"=\3\2\2\2\21?\3\2\2\2\23A\3\2\2\2\25D\3\2\2\2\27H\3\2\2\2\31L\3\2\2\2"+
		"\33Q\3\2\2\2\35V\3\2\2\2\37Z\3\2\2\2!]\3\2\2\2#a\3\2\2\2%d\3\2\2\2\'i"+
		"\3\2\2\2)w\3\2\2\2+z\3\2\2\2-\u0082\3\2\2\2/\u0086\3\2\2\2\61\62\7*\2"+
		"\2\62\4\3\2\2\2\63\64\7+\2\2\64\6\3\2\2\2\65\66\7]\2\2\66\b\3\2\2\2\67"+
		"8\7_\2\28\n\3\2\2\29:\7}\2\2:\f\3\2\2\2;<\7\177\2\2<\16\3\2\2\2=>\7?\2"+
		"\2>\20\3\2\2\2?@\7.\2\2@\22\3\2\2\2AB\7h\2\2BC\7p\2\2C\24\3\2\2\2DE\7"+
		"t\2\2EF\7g\2\2FG\7e\2\2G\26\3\2\2\2HI\7k\2\2IJ\7h\2\2JK\7|\2\2K\30\3\2"+
		"\2\2LM\7k\2\2MN\7h\2\2NO\7g\2\2OP\7o\2\2P\32\3\2\2\2QR\7g\2\2RS\7n\2\2"+
		"ST\7u\2\2TU\7g\2\2U\34\3\2\2\2VW\7n\2\2WX\7g\2\2XY\7v\2\2Y\36\3\2\2\2"+
		"Z[\7k\2\2[\\\7p\2\2\\ \3\2\2\2]^\7p\2\2^_\7k\2\2_`\7n\2\2`\"\3\2\2\2a"+
		"b\7*\2\2bc\7+\2\2c$\3\2\2\2de\t\2\2\2e&\3\2\2\2fj\t\3\2\2gh\7<\2\2hj\7"+
		"?\2\2if\3\2\2\2ig\3\2\2\2j(\3\2\2\2kl\7e\2\2lm\7q\2\2mn\7p\2\2nx\7u\2"+
		"\2op\7j\2\2pq\7g\2\2qr\7c\2\2rx\7f\2\2st\7v\2\2tu\7c\2\2uv\7k\2\2vx\7"+
		"n\2\2wk\3\2\2\2wo\3\2\2\2ws\3\2\2\2x*\3\2\2\2y{\7/\2\2zy\3\2\2\2z{\3\2"+
		"\2\2{}\3\2\2\2|~\t\4\2\2}|\3\2\2\2~\177\3\2\2\2\177}\3\2\2\2\177\u0080"+
		"\3\2\2\2\u0080,\3\2\2\2\u0081\u0083\t\5\2\2\u0082\u0081\3\2\2\2\u0083"+
		"\u0084\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085.\3\2\2\2"+
		"\u0086\u0087\t\6\2\2\u0087\u0088\3\2\2\2\u0088\u0089\b\30\2\2\u0089\60"+
		"\3\2\2\2\b\2iwz\177\u0084\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}