package us.bpsm.treacle;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
class CompactTreeSpace implements TreeSpace {

     static final int
            NAME_BEG_OFF = 0,
            NAME_END_OFF = 1,
            CONT_BEG_OFF = 2,
            CONT_END_OFF = 3,
            CHILD_COUNT_OFF = 4,
            FIRST_CHILD_OFF = 5;

    AbstractIntegers index;
    StringBuilder contents = new StringBuilder();
    StringBuilder names = new StringBuilder();
    List<Tree> foreign = new ArrayList<Tree>(0);

    CompactTreeSpace(AbstractIntegers index) {
        this.index = index;
    }

    @Override
    public Tree newTree(CharSequence name, CharSequence content, Iterable<Tree> children) {
        int id = index.size();
        record(name, names, index);
        record(content, contents, index);
        int countIndex = index.size();
        index.add(0);
        int count = 0;
        for (Tree t: children) {
            count++;
            if (t instanceof CompactTree) {
                CompactTree ct = (CompactTree)t;
                if (ct.space == this) {
                    index.add(ct.id);
                    continue;
                }
            }
            index.add(-(foreign.size()+1));
            foreign.add(t);
        }
        index.set(countIndex, count);
        return new CompactTree(id, this);
    }

    private static boolean tradeTimeForSpace = false;

    private static void record(CharSequence name, StringBuilder text, AbstractIntegers index) {
        if (tradeTimeForSpace) {
            Matcher m = Pattern.compile(name.toString(), Pattern.LITERAL).matcher(text);
            if (m.find()) {
                index.add(m.start());
                index.add(m.end());
                return;
            }
        }
        text.append(name);
        index.add(text.length() - name.length());
        index.add(text.length());
    }
}
