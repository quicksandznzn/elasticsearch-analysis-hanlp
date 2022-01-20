package org.quicksandzn.elasticsearch.analysis.hanlp.tokenizer;

import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.seg.common.wrapper.SegmentWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.tartarus.snowball.ext.PorterStemmer;

/**
 * @author quicksand - 2022/1/17
 */
public class HanLPTokenizer extends Tokenizer {

    /**
     * 当前词
     */
    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
    /**
     * 偏移量
     */
    private final OffsetAttribute offsetAtt = addAttribute(OffsetAttribute.class);
    /**
     * 距离
     */
    private final PositionIncrementAttribute positionAttr = addAttribute(PositionIncrementAttribute.class);
    /**
     * 词性
     */
    private final TypeAttribute typeAtt = addAttribute(TypeAttribute.class);
    /**
     * 分词器
     */
    private final SegmentWrapper segment;
    /**
     * stemmer
     */
    private final PorterStemmer stemmer = new PorterStemmer();

    /**
     * 单文档当前所在的总offset，当reset（切换multi-value fields中的value）的时候不清零，在end（切换field）时清零
     */
    private int totalOffset = 0;

    public HanLPTokenizer(Segment segment) {
        this.segment = new SegmentWrapper(new BufferedReader(this.input), segment);
    }

    @Override
    public boolean incrementToken() throws IOException {
        clearAttributes();
        Term term = segment.next();
        if (term == null) {
            return false;
        }
        positionAttr.setPositionIncrement(0);
        termAtt.setEmpty().append(term.word);
        offsetAtt.setOffset(correctOffset(term.offset), correctOffset(term.offset + term.word.length()));
        typeAtt.setType(term.nature == null ? "null" : term.nature.toString());
        totalOffset += term.length();
        return true;
    }

    @Override
    public void end() throws IOException {
        super.end();
        offsetAtt.setOffset(totalOffset, totalOffset);
        totalOffset = 0;
    }

    /**
     * 必须重载的方法，否则在批量索引文件时将会导致文件索引失败
     */
    @Override
    public void reset() throws IOException {
        super.reset();
        segment.reset(new BufferedReader(this.input));
    }
}
