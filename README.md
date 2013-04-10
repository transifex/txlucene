## Introduction

This library intervenes the normal search process and changes the score of the
matching documents.

By default, lucene will return all documents that match at least one term of
those specified in the search query. However, when it comes to TM engines, it
only makes sense to get results that have enough terms similar to the original
phrase; otherwise, both the levenshtein distance and the quality of the match
would be low.

For this reason, the library will assign a zero score to all matching documents
that have less than at half matching terms to the original phrase. We do this by
customizing the score formula by zeroing out the score in this case.


## How to use

* Compile the java file with:

    javac -Xlint:deprecation -classpath /path/lucene-4.2.1/core/src/java/ org/transifex/lucene/search/CoordSimilarity.java

    jar cvf txsearcher.jar -C src .

* Copy the jar file to the libs directory of the Solr installation.
* Add the jar in the library path for Solr, e.g.:

    &lt;lib dir="../../lib/" regex="tx.*\.jar" /&gt;

  See also http://wiki.apache.org/solr/SolrPlugins#How_to_Load_Plugins.

* Instruct Solr to use the `CoordSimilarity` class to calculate the
  similarities:

    &lt;similarity class="org.transifex.lucene.search.CoordSimilarity"/&gt;

  See also http://wiki.apache.org/solr/SchemaXml#Similarity.
