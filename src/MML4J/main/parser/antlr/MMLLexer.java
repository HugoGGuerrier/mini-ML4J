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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, FN=11, REC=12, IF_ZERO=13, IF_EMPTY=14, ELSE=15, LET=16, IN=17, 
		NIL=18, UNIT=19, UN_OP=20, BIN_OP=21, BUILD_IN=22, INTEGER=23, IDENT=24, 
		IGNORED__=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "FN", "REC", "IF_ZERO", "IF_EMPTY", "ELSE", "LET", "IN", "NIL", 
			"UNIT", "UN_OP", "BIN_OP", "BUILD_IN", "INTEGER", "IDENT", "IGNORED__"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\33\u0094\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3"+
		"\b\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\25"+
		"\3\25\3\26\3\26\3\26\5\26t\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\5\27\u0082\n\27\3\30\5\30\u0085\n\30\3\30\6\30\u0088"+
		"\n\30\r\30\16\30\u0089\3\31\6\31\u008d\n\31\r\31\16\31\u008e\3\32\3\32"+
		"\3\32\3\32\2\2\33\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\3\2"+
		"\7\4\2##BB\4\2--//\3\2\62;\5\2C\\aac|\5\2\13\f\17\17\"\"\2\u0099\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61"+
		"\3\2\2\2\2\63\3\2\2\2\3\65\3\2\2\2\5\67\3\2\2\2\79\3\2\2\2\t;\3\2\2\2"+
		"\13=\3\2\2\2\r?\3\2\2\2\17A\3\2\2\2\21C\3\2\2\2\23E\3\2\2\2\25I\3\2\2"+
		"\2\27K\3\2\2\2\31N\3\2\2\2\33R\3\2\2\2\35V\3\2\2\2\37[\3\2\2\2!`\3\2\2"+
		"\2#d\3\2\2\2%g\3\2\2\2\'k\3\2\2\2)n\3\2\2\2+s\3\2\2\2-\u0081\3\2\2\2/"+
		"\u0084\3\2\2\2\61\u008c\3\2\2\2\63\u0090\3\2\2\2\65\66\7*\2\2\66\4\3\2"+
		"\2\2\678\7+\2\28\6\3\2\2\29:\7]\2\2:\b\3\2\2\2;<\7_\2\2<\n\3\2\2\2=>\7"+
		"}\2\2>\f\3\2\2\2?@\7\177\2\2@\16\3\2\2\2AB\7=\2\2B\20\3\2\2\2CD\7?\2\2"+
		"D\22\3\2\2\2EF\7c\2\2FG\7p\2\2GH\7f\2\2H\24\3\2\2\2IJ\7.\2\2J\26\3\2\2"+
		"\2KL\7h\2\2LM\7p\2\2M\30\3\2\2\2NO\7t\2\2OP\7g\2\2PQ\7e\2\2Q\32\3\2\2"+
		"\2RS\7k\2\2ST\7h\2\2TU\7|\2\2U\34\3\2\2\2VW\7k\2\2WX\7h\2\2XY\7g\2\2Y"+
		"Z\7o\2\2Z\36\3\2\2\2[\\\7g\2\2\\]\7n\2\2]^\7u\2\2^_\7g\2\2_ \3\2\2\2`"+
		"a\7n\2\2ab\7g\2\2bc\7v\2\2c\"\3\2\2\2de\7k\2\2ef\7p\2\2f$\3\2\2\2gh\7"+
		"p\2\2hi\7k\2\2ij\7n\2\2j&\3\2\2\2kl\7*\2\2lm\7+\2\2m(\3\2\2\2no\t\2\2"+
		"\2o*\3\2\2\2pt\t\3\2\2qr\7<\2\2rt\7?\2\2sp\3\2\2\2sq\3\2\2\2t,\3\2\2\2"+
		"uv\7e\2\2vw\7q\2\2wx\7p\2\2x\u0082\7u\2\2yz\7j\2\2z{\7g\2\2{|\7c\2\2|"+
		"\u0082\7f\2\2}~\7v\2\2~\177\7c\2\2\177\u0080\7k\2\2\u0080\u0082\7n\2\2"+
		"\u0081u\3\2\2\2\u0081y\3\2\2\2\u0081}\3\2\2\2\u0082.\3\2\2\2\u0083\u0085"+
		"\7/\2\2\u0084\u0083\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0087\3\2\2\2\u0086"+
		"\u0088\t\4\2\2\u0087\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u0087\3\2"+
		"\2\2\u0089\u008a\3\2\2\2\u008a\60\3\2\2\2\u008b\u008d\t\5\2\2\u008c\u008b"+
		"\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f"+
		"\62\3\2\2\2\u0090\u0091\t\6\2\2\u0091\u0092\3\2\2\2\u0092\u0093\b\32\2"+
		"\2\u0093\64\3\2\2\2\b\2s\u0081\u0084\u0089\u008e\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}