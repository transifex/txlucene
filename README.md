## How to use

* Compile the java file with:

    javac -Xlint:deprecation -classpath /path/lucene-4.0.0/core/src/java/ org/transifex/com/search/CoordSimilarity.java

    jar cvf txsearcher.jar -C src .

* Copy the jar file to the libs directory of the Solr installation.
* Add the jar in the library path for Solr, e.g.:

    &lt;lib dir="../../lib/" regex="tx.*\.jar" /&gt;

  See also http://wiki.apache.org/solr/SolrPlugins#How_to_Load_Plugins.

* Instruct Solr to use the `CoordSimilarity` class to calculate the
  similarities:

    &lt;similarity class="org.transifex.lucene.search.CoordSimilarity"/&gt;

  See also http://wiki.apache.org/solr/SchemaXml#Similarity.
