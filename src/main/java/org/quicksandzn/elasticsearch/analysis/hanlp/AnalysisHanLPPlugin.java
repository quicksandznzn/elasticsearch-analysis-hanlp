package org.quicksandzn.elasticsearch.analysis.hanlp;

import java.util.HashMap;
import java.util.Map;
import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.AnalyzerProvider;
import org.elasticsearch.index.analysis.TokenizerFactory;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;
import org.quicksandzn.elasticsearch.analysis.hanlp.analyzer.HanLPAnalyzerProvider;
import org.quicksandzn.elasticsearch.analysis.hanlp.tokenizer.HanLPTokenizerFactory;

/**
 * @author quicksand - 2022/1/17
 */
public class AnalysisHanLPPlugin extends Plugin implements AnalysisPlugin {

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> getTokenizers() {
        Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> tokenizerFactoryMap = new HashMap<>();
        tokenizerFactoryMap.put("hanlp", HanLPTokenizerFactory::getHanLPTokenizerFactory);
        return tokenizerFactoryMap;
    }

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
        Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> analyzerProviderMap = new HashMap<>();
        analyzerProviderMap.put("hanlp", HanLPAnalyzerProvider::getHanLPAnalyzerProvider);
        return analyzerProviderMap;
    }
}
