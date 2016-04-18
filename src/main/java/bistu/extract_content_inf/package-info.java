/**
 * 抽取原始专利数据中的ID，Name，Abstraction，Claim，并将其存入sourcefile/All_Information_Content/下
 * RewriteContent 重写操作，对原始专利数据中的DTD规则替换掉，否则，使用dom读取xml文件会出错。courcefile/UnDtd_Content
 * ExtraPart 使用DOM读取xml，提取出ID，Name，Abstraction，Claim
 * Information 将ExtraPart读取的字段写入对应的文件。courcefile/ContentAllInformation
 */
/**
 * @author Joen
 *
 */
package bistu.extract_content_inf;