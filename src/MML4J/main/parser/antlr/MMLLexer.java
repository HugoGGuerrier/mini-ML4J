// Generated from MML.g4 by ANTLR 4.9.2

package MML4J.main.parser.antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MMLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, FN=7, REC=8, NIL=9, IF_ZERO=10, 
		IF_EMPTY=11, ELSE=12, LET=13, IN=14, BIN_OP=15, BUILD_IN=16, INTEGER=17, 
		IDENT=18, IGNORED__=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "FN", "REC", "NIL", "IF_ZERO", 
			"IF_EMPTY", "ELSE", "LET", "IN", "BIN_OP", "BUILD_IN", "INTEGER", "IDENT", 
			"IGNORED__"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'{'", "'}'", "'='", "','", "'fn'", "'rec'", "'nil'", 
			"'ifz'", "'ifem'", "'else'", "'let'", "'in'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "FN", "REC", "NIL", "IF_ZERO", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\25v\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3"+
		"\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5"+
		"\21d\n\21\3\22\5\22g\n\22\3\22\6\22j\n\22\r\22\16\22k\3\23\6\23o\n\23"+
		"\r\23\16\23p\3\24\3\24\3\24\3\24\2\2\25\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25\3\2\6\4"+
		"\2--//\3\2\62;\4\2C\\c|\5\2\13\f\17\17\"\"\2z\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2"+
		"\2\2\3)\3\2\2\2\5+\3\2\2\2\7-\3\2\2\2\t/\3\2\2\2\13\61\3\2\2\2\r\63\3"+
		"\2\2\2\17\65\3\2\2\2\218\3\2\2\2\23<\3\2\2\2\25@\3\2\2\2\27D\3\2\2\2\31"+
		"I\3\2\2\2\33N\3\2\2\2\35R\3\2\2\2\37U\3\2\2\2!c\3\2\2\2#f\3\2\2\2%n\3"+
		"\2\2\2\'r\3\2\2\2)*\7*\2\2*\4\3\2\2\2+,\7+\2\2,\6\3\2\2\2-.\7}\2\2.\b"+
		"\3\2\2\2/\60\7\177\2\2\60\n\3\2\2\2\61\62\7?\2\2\62\f\3\2\2\2\63\64\7"+
		".\2\2\64\16\3\2\2\2\65\66\7h\2\2\66\67\7p\2\2\67\20\3\2\2\289\7t\2\29"+
		":\7g\2\2:;\7e\2\2;\22\3\2\2\2<=\7p\2\2=>\7k\2\2>?\7n\2\2?\24\3\2\2\2@"+
		"A\7k\2\2AB\7h\2\2BC\7|\2\2C\26\3\2\2\2DE\7k\2\2EF\7h\2\2FG\7g\2\2GH\7"+
		"o\2\2H\30\3\2\2\2IJ\7g\2\2JK\7n\2\2KL\7u\2\2LM\7g\2\2M\32\3\2\2\2NO\7"+
		"n\2\2OP\7g\2\2PQ\7v\2\2Q\34\3\2\2\2RS\7k\2\2ST\7p\2\2T\36\3\2\2\2UV\t"+
		"\2\2\2V \3\2\2\2WX\7e\2\2XY\7q\2\2YZ\7p\2\2Zd\7u\2\2[\\\7j\2\2\\]\7g\2"+
		"\2]^\7c\2\2^d\7f\2\2_`\7v\2\2`a\7c\2\2ab\7k\2\2bd\7n\2\2cW\3\2\2\2c[\3"+
		"\2\2\2c_\3\2\2\2d\"\3\2\2\2eg\7/\2\2fe\3\2\2\2fg\3\2\2\2gi\3\2\2\2hj\t"+
		"\3\2\2ih\3\2\2\2jk\3\2\2\2ki\3\2\2\2kl\3\2\2\2l$\3\2\2\2mo\t\4\2\2nm\3"+
		"\2\2\2op\3\2\2\2pn\3\2\2\2pq\3\2\2\2q&\3\2\2\2rs\t\5\2\2st\3\2\2\2tu\b"+
		"\24\2\2u(\3\2\2\2\7\2cfkp\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}