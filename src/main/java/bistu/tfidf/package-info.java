/**
 * 计算tf-idf值
 * @TFIDF_Main 包含了tfidf的所有顺序操作
 * @Patents_Maps 构建一个hashmap存储专利中的词和出现该词的文件数，方便之后的idf计算
 * @TF_IDF	计算每一个文件中的每一个词的tfidf值
 * @Write_TF_IDF 将计算出的tf，idf，tfidf写入文件
 * @Split_TF_IDF 将之前写入的文件进行分割，分别存储tf，idf，tfidf
 * @TFIDF_filter 过滤tfidf过低的词
 */
/**
 * @author Joen
 *
 */
package bistu.tfidf;