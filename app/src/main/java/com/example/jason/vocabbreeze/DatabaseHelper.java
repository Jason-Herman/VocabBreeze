package com.example.jason.vocabbreeze;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "words.db";
    public static final String TABLE_NAME = "words_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "WORD";
    public static final String COL_3 = "DEFINITION";
    public static final String COL_4 = "INTERVAL";
    public static final String COL_5 = "TTS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,WORD TEXT,DEFINITION TEXT,INTERVAL INTEGER, TTS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String word, String definition, Integer interval, Integer TTS) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, word);
        contentValues.put(COL_3, definition);
        contentValues.put(COL_4, interval);
        contentValues.put(COL_5, TTS);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean populate() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[][] content = {{"abject", "awful"}, {"aberration", "oddity"}, {"abjure", "reject belief"},
                {"abnegation", "denial"}, {"abrogate", "repeal"}, {"abscond", "flee"}, {"abstruse", "complex"},
                {"accede", "yield"}, {"accost", "speak"}, {"accretion", "increase"}, {"acumen", "cleverness"},
                {"adamant", "firm"}, {"admonish", "scold"}, {"adumbrate", "summarize"}, {"adverse", "opposing"},
                {"advocate", "backer"}, {"affluent", "rich"}, {"aggrandize", "elevate"}, {"alacrity", "liveliness"},
                {"alias", "temporary name"}, {"ambivalent", "undecided"}, {"amenable", "cooperative"}, {"amorphous", "shapeless"}, {"anachronistic", "old-fashioned"},
                {"anathema", "an enemy"}, {"annex", "add"}, {"antediluvian", "ancient"}, {"antiseptic", "characterless"},
                {"apathetic", "uninterested"}, {"antithesis", "opposite"}, {"apocryphal", "fictitious"}, {"approbation", "approval"},
                {"arbitrary", "random"}, {"arboreal", "of trees"}, {"arcane", "mysterious"}, {"archetypal", "representative"},
                {"arrogate", "unjustly take"}, {"ascetic", "self-disciplined"}, {"aspersion", "criticism"}, {"assiduous", "diligent"},
                {"atrophy", "waste away"}, {"bane", "nuisance"}, {"bashful", "shy"}, {"beguile", "seduce"},
                {"bereft", "deprived of"}, {"blandishment", "smooth talk"}, {"bilk", "cheat"}, {"bombastic", "high-sounding"},
                {"cajole", "sweet-talk"}, {"callous", "insensitive"}, {"calumny", "slander"}, {"camaraderie", "friendship"},
                {"candor", "open honesty"}, {"capitulate", "surrender"}, {"carouse", "drink/party"}, {"carp", "complain"},
                {"caucus", "convention"}, {"cavort", "play around"}, {"circumlocution", "overly wordy"}, {"circumscribe", "confine"},
                {"circumvent", "get around"}, {"clamor", "loud noise"}, {"cleave", "split"}, {"cobbler", "shoe fixer"},
                {"cogent", "logical"}, {"cognizant", "aware"}, {"commensurate", "proportional"}, {"complement", "addition"},
                {"compunction", "guilt"}, {"concomitant", "accompanying"}, {"conduit", "channel"}, {"conflagration", "bad fire"},
                {"congruity", "harmony"}, {"connive", "conspire"}, {"consign", "deliver"}, {"constituent", "part of"},
                {"construe", "interpret"}, {"contusion", "bruise"}, {"contrite", "sorry"}, {"contentious", "disputed"},
                {"contravene", "break (law)"}, {"convivial", "friendly"}, {"corpulence", "obesity"}, {"covet", "crave"},
                {"cupidity", "greed"}, {"dearth", "shortage"}, {"debacle", "disaster"}, {"debauch", "corrupt"},
                {"debunk", "disprove"}, {"defunct", "expired"}, {"demagogue", "political agitator"}, {"denigrate", "insult"},
                {"derivative", "unoriginal"}, {"despot", "dictator"}, {"diaphanous", "silky (fabric)"}, {"didactic", "educational"},
                {"dirge", "sad song"}, {"disaffected", "rebellious"}, {"discomfit", "embarrass"}, {"disparate", "incomparable"},
                {"dispel", "get rid of"}, {"disrepute", "bad reputation"}, {"divisive", "disruptive"}, {"dogmatic", "narrow-minded"},
                {"dour", "unfriendly"}, {"duplicity", "dishonesty"}, {"duress", "coercion"}, {"eclectic", "broad-based"},
                {"edict", "commandment"}, {"ebullient", "cheerful"}, {"egregious", "awful"}, {"elegy", "sad song"},
                {"elicit", "draw out"}, {"embezzlement", "theft"}, {"emend", "correct"}, {"emollient", "skin softener"},
                {"empirical", "evidence-based"}, {"emulate", "imitate"}, {"enervate", "weaken"}, {"enfranchise", "set free"},
                {"engender", "give rise to"}, {"ephemeral", "short-lived"}, {"epistolary", "of letters"}, {"equanimity", "coolheadedness"},
                {"equivocal", "vague"}, {"espouse", "adopt (belief)"}, {"evanescent", "fading"}, {"evince", "reveal"},
                {"exacerbate", "worsen"}, {"exhort", "urge"}, {"execrable", "awful"}, {"exigent", "demanding"},
                {"expedient", "advantageous"}, {"expiate", "atone for"}, {"expunge", "erase"}, {"extraneous", "irrelevant"},
                {"extol", "praise"}, {"extant", "surviving"}, {"expurgate", "censor"}, {"fallacious", "false"},
                {"fatuous", "foolish"}, {"fetter", "restraints"}, {"flagrant", "shameless"}, {"foil", "prevent"},
                {"forbearance", "patience"}, {"fortuitous", "unexpected"}, {"fractious", "quarrelsome"}, {"garrulous", "chatty"},
                {"gourmand", "big eater"}, {"grandiloquent", "extravagant"}, {"gratuitous", "uncalled for"}, {"hapless", "unlucky"},
                {"hegemony", "authority"}, {"heterogeneous", "varied"}, {"iconoclast", "skeptic"}, {"idiosyncratic", "unique"},
                {"impecunious", "poor"}, {"impetuous", "careless"}, {"impinge", "impact"}, {"impute", "atrrbiute to"},
                {"inane", "silly"}, {"inchoate", "immature"}, {"incontrovertible", "undeniable"}, {"incumbent", "necessary"},
                {"inexorable", "unstoppable"}, {"inimical", "harmful"}, {"injunction", "command"}, {"inoculate", "vaccinate"},
                {"insidious", "treacherous"}, {"instigate", "start"}, {"insurgent", "rebel"}, {"interlocutor", "talker"},
                {"intimation", "hint"}, {"inure", "condition"}, {"invective", "insults"}, {"intransigent", "unyielding"},
                {"inveterate", "ingrained"}, {"irreverence", "disrespect"}, {"knell", "sad bell ring"}, {"laconic", "concise"},
                {"largesse", "generosity"}, {"legerdemain", "sleight of hand"}, {"libertarian", "freedom lover"}, {"licentious", "naughty"},
                {"linchpin", "vital element"}, {"litigant", "accused"}, {"maelstrom", "vortex"}, {"maudlin", "too sentimental"},
                {"maverick", "free spirit"}, {"mawkish", "too sentimental"}, {"maxim", "saying"}, {"mendacious", "lying"},
                {"modicum", "small amount"}, {"morass", "swamp"}, {"mores", "customs"}, {"munificent", "generous"},
                {"multifarious", "various"}, {"nadir", "low point"}, {"negligent", "careless"}, {"neophyte", "novice"},
                {"noisome", "smelly"}, {"noxious", "toxic"}, {"obdurate", "stubborn"}, {"obfuscate", "obscure"},
                {"obstreperous", "unruly"}, {"officious", "intrusive"}, {"onerous", "hard"}, {"ostensible", "supposed"},
                {"ostracism", "exclusion"}, {"palliate", "soothe"}, {"panacea", "cure-all"}, {"paradigm", "model"},
                {"pariah", "outcast"}, {"partisan", "follower"}, {"paucity", "shortage"}, {"pejorative", "insulting"},
                {"pellucid", "translucent"}, {"penchant", "preference"}, {"penurious", "poor"}, {"pert", "lively"},
                {"pernicious", "harmful"}, {"pertinacious", "persistent"}, {"phlegmatic", "composed"}, {"philanthropic", "charitable"},
                {"pithy", "concise"}, {"platitude", "cliche"}, {"plaudit", "praise"}, {"plenitude", "abundance"},
                {"plethora", "surplus"}, {"portent", "omen"}, {"potentate", "ruler"}, {"preclude", "prevent"},
                {"predilection", "preference"}, {"preponderance", "prevalence"}, {"presage", "foreshadow"}, {"probity", "integrity"},
                {"proclivity", "inclination"}, {"profligate", "wasteful"}, {"promulgate", "make known"}, {"proscribe", "forbid"},
                {"protean", "changeable"}, {"prurient", "lustful"}, {"puerile", "silly"}, {"pugnacious", "combative"},
                {"pulchritude", "beauty"}, {"punctilious", "particular"}, {"quaint", "charming"}, {"quixotic", "idealistic"},
                {"quandary", "dilemma"}, {"recalcitrant", "defiant"}, {"redoubtable", "formidable"}, {"relegate", "move down"},
                {"remiss", "negligent"}, {"reprieve", "pardon"}, {"reprobate", "rascal"}, {"rescind", "cancel"},
                {"requisition", "take over"}, {"rife", "widespread"}, {"sanctimonious", "self-righteous"}, {"sanguine", "optimistic"},
                {"scurrilous", "insulting"}, {"semaphore", "signal"}, {"serendipity", "good luck"}, {"sobriety", "clearheadedness"},
                {"solicitous", "attentive"}, {"solipsism", "self-absorption"}, {"spurious", "fake"}, {"staid", ""},
                {"stolid", "calm"}, {"subjugate", "conquer"}, {"surfeit", "excess"}, {"surreptitious", "secret"},
                {"swarthy", "tanned"}, {"tangential", "unrelated"}, {"tome", "big book"}, {"toady", "brown-noser"},
                {"torpid", "inactive"}, {"travesty", "poor substitute"}, {"trenchant", "sharp"}, {"trite", "overused"},
                {"truculent", "aggressive"}, {"turpitude", "wickedness"}, {"ubiquitous", "universal"}, {"umbrage", "offense"},
                {"upbraid", "scold"}, {"utilitarian", "practical"}, {"veracity", "truth"}, {"vestige", "trace"},
                {"vicissitude", "downturn"}, {"vilify", "criticize"}, {"virtuoso", "master"}, {"vitriolic", "spiteful"},
                {"vituperate", "criticize"}, {"vociferous", "forceful"}, {"wanton", "cruel"}, {"winsome", "attractive"},
                {"yoke", "attach"}, {"zephyr", "breeze"}, {"wily", "clever"}, {"tirade", "rant"}};

        for (String[] pair : content) {
            this.insertData(pair[0], pair[1], 1, 0);
        }
        //set initial interval to 1
//        this.insertData("Abject","Awful",1,2);
//        this.insertData("Aberration","Oddity",4,4);
//        this.insertData("Abjure","Reject",2,0);
//        this.insertData("Abnegation","Denial",4,0);
//        this.insertData("Abrogate","Repeal",4,0);
//        this.insertData("Abscond","Flee",4,0);
        return true;
    }

    public boolean updateData(String id, String word, String definition, Integer interval, Integer TTS) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, word);
        contentValues.put(COL_3, definition);
        contentValues.put(COL_4, interval);
        contentValues.put(COL_5, TTS);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        return true;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }

    public Integer clear() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, null, null);
    }

    public boolean decrement() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE words_table SET TTS = TTS - 1 WHERE TTS > 0");
        return true;
    }

    public Cursor getFeedbackWord(String word) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from words_table WHERE word LIKE '" + word + "'", null);
        return res;
    }

    public Cursor getWords() {
        SQLiteDatabase db = this.getWritableDatabase();
        //TODO: make a sql query that selects the top N results from table sorted on alphabetical and TTS = 0
        Cursor res = db.rawQuery("select * from words_table WHERE TTS = 0 ORDER BY interval DESC LIMIT 4", null);
        return res;
    }

    public boolean incInterval() {
        SQLiteDatabase db = this.getWritableDatabase();
        //TODO:operate on raw query results and double interval and set TTS to interval
        db.execSQL("UPDATE words_table SET interval = interval * 2 WHERE EXISTS (select * from words_table WHERE TTS = 0 ORDER BY interval DESC LIMIT 2)");
        return true;
    }
}
