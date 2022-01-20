package org.quicksandzn.elasticsearch.analysis.hanlp.tokenizer;

import com.hankcs.hanlp.HanLP;
import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenizerFactory;
import org.quicksandzn.elasticsearch.analysis.hanlp.enums.HanLPEnum;

/**
 * @author quicksand - 2022/1/17
 */
public class HanLPTokenizerFactory extends AbstractTokenizerFactory {


    private final HanLPEnum hanLPEnum;
    private final Environment environment;

    public HanLPTokenizerFactory(IndexSettings indexSettings, String name,
        Settings settings, Environment environment, HanLPEnum hanLPEnum) {
        super(indexSettings, name, settings);
        this.hanLPEnum = hanLPEnum;
        this.environment = environment;
    }

    public static HanLPTokenizerFactory getHanLPTokenizerFactory(IndexSettings indexSettings, Environment environment,
        String name,
        Settings settings) {
        return new HanLPTokenizerFactory(indexSettings, name, settings, environment, HanLPEnum.HANLP);
    }

    @Override
    public Tokenizer create() {
        return new HanLPTokenizer(HanLP.newSegment());
    }
}
