package org.quicksandzn.elasticsearch.analysis.hanlp.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractIndexAnalyzerProvider;
import org.quicksandzn.elasticsearch.analysis.hanlp.enums.HanLPEnum;

/**
 * @author quicksand - 2022/1/17
 */
public class HanLPAnalyzerProvider extends AbstractIndexAnalyzerProvider<Analyzer> {

    private final HanLPEnum hanLPEnum;
    private final Environment environment;
    private final Analyzer analyzer;

    public HanLPAnalyzerProvider(IndexSettings indexSettings, String name,
        Settings settings, Environment environment, HanLPEnum hanLPEnum) {
        super(indexSettings, name, settings);
        this.hanLPEnum = hanLPEnum;
        this.environment = environment;
        switch (hanLPEnum) {
            case HANLP:
                analyzer = new HanLPAnalyzer();
                break;
            default:
                analyzer = new HanLPAnalyzer();
        }

    }

    public static HanLPAnalyzerProvider getHanLPAnalyzerProvider(IndexSettings indexSettings, Environment env,
        String name,
        Settings settings) {
        return new HanLPAnalyzerProvider(indexSettings, name, settings, env, HanLPEnum.HANLP);
    }

    @Override
    public Analyzer get() {
        return this.analyzer;
    }

}
