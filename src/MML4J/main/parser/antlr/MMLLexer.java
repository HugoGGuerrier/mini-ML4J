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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, FN=7, FN_REC=8, NIL=9, 
		IF_ZERO=10, IF_EMPTY=11, ELSE=12, LET=13, IN=14, BIN_OP=15, BUILD_IN=16, 
		INTEGER=17, IDENT=18, IGNORED__=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "FN", "FN_REC", "NIL", 
			"IF_ZERO", "IF_EMPTY", "ELSE", "LET", "IN", "BIN_OP", "BUILD_IN", "INTEGER", 
			"IDENT", "IGNORED__"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\25x\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\5\21f\n\21\3\22\5\22i\n\22\3\22\6\22l\n\22\r\22\16\22m\3\23\6\23"+
		"q\n\23\r\23\16\23r\3\24\3\24\3\24\3\24\2\2\25\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25\3"+
		"\2\6\4\2--//\3\2\62;\4\2C\\c|\5\2\13\f\17\17\"\"\2|\2\3\3\2\2\2\2\5\3"+
		"\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2"+
		"\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3"+
		"\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'"+
		"\3\2\2\2\3)\3\2\2\2\5+\3\2\2\2\7-\3\2\2\2\t/\3\2\2\2\13\61\3\2\2\2\r\63"+
		"\3\2\2\2\17\65\3\2\2\2\218\3\2\2\2\23>\3\2\2\2\25B\3\2\2\2\27F\3\2\2\2"+
		"\31K\3\2\2\2\33P\3\2\2\2\35T\3\2\2\2\37W\3\2\2\2!e\3\2\2\2#h\3\2\2\2%"+
		"p\3\2\2\2\'t\3\2\2\2)*\7*\2\2*\4\3\2\2\2+,\7+\2\2,\6\3\2\2\2-.\7}\2\2"+
		".\b\3\2\2\2/\60\7\177\2\2\60\n\3\2\2\2\61\62\7?\2\2\62\f\3\2\2\2\63\64"+
		"\7.\2\2\64\16\3\2\2\2\65\66\7h\2\2\66\67\7p\2\2\67\20\3\2\2\289\7h\2\2"+
		"9:\7p\2\2:;\7t\2\2;<\7g\2\2<=\7e\2\2=\22\3\2\2\2>?\7p\2\2?@\7k\2\2@A\7"+
		"n\2\2A\24\3\2\2\2BC\7k\2\2CD\7h\2\2DE\7|\2\2E\26\3\2\2\2FG\7k\2\2GH\7"+
		"h\2\2HI\7g\2\2IJ\7o\2\2J\30\3\2\2\2KL\7g\2\2LM\7n\2\2MN\7u\2\2NO\7g\2"+
		"\2O\32\3\2\2\2PQ\7n\2\2QR\7g\2\2RS\7v\2\2S\34\3\2\2\2TU\7k\2\2UV\7p\2"+
		"\2V\36\3\2\2\2WX\t\2\2\2X \3\2\2\2YZ\7e\2\2Z[\7q\2\2[\\\7p\2\2\\f\7u\2"+
		"\2]^\7j\2\2^_\7g\2\2_`\7c\2\2`f\7f\2\2ab\7v\2\2bc\7c\2\2cd\7k\2\2df\7"+
		"n\2\2eY\3\2\2\2e]\3\2\2\2ea\3\2\2\2f\"\3\2\2\2gi\7/\2\2hg\3\2\2\2hi\3"+
		"\2\2\2ik\3\2\2\2jl\t\3\2\2kj\3\2\2\2lm\3\2\2\2mk\3\2\2\2mn\3\2\2\2n$\3"+
		"\2\2\2oq\t\4\2\2po\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2\2s&\3\2\2\2tu\t"+
		"\5\2\2uv\3\2\2\2vw\b\24\2\2w(\3\2\2\2\7\2ehmr\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}