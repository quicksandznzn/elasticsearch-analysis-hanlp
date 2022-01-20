package org.quicksandzn.elasticsearch.analysis.hanlp.analyzer;

import com.hankcs.hanlp.HanLP;
import org.apache.lucene.analysis.Analyzer;
import org.quicksandzn.elasticsearch.analysis.hanlp.tokenizer.HanLPTokenizer;

/**
 * @author quicksand - 2022/1/17
 */
public final class HanLPAnalyzer extends Analyzer {

    public HanLPAnalyzer() {
    }

    @Override
    protected TokenStreamComponents createComponents(String s) {
        return new TokenStreamComponents(new HanLPTokenizer(HanLP.newSegment()));
    }
}
