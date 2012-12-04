package org.transifex.lucene.search;

import org.apache.lucene.index.FieldInvertState;
import org.apache.lucene.index.Norm;
import org.apache.lucene.search.similarities.DefaultSimilarity;


public class CoordSimilarity extends DefaultSimilarity {

    @Override
    public float coord(int overlap, int maxOverlap) {
        // Zero out score for matches that have less than half of the
        // required terms. See also
        // http://lucene.apache.org/core/4_0_0/core/org/apache/lucene/search/similarities/TFIDFSimilarity.html
        float default_coord = super.coord(overlap, maxOverlap);
        if (maxOverlap >= 5 && default_coord < 0.5) {
            return 0.0f;
        }
        return default_coord;
    }

    @Override
    public float tf(float freq) {
        // We do not care about the score of the document.
        return 1.0f;
    }

    @Override
    public void computeNorm(FieldInvertState state, Norm norm) {
        // We do not care about normalizing the document, so use a
        // fair normalization. See
        // https://lucene.apache.org/core/4_0_0/core/org/apache/lucene/search/similarities/package-summary.html
        final int numTerms;
        if (discountOverlaps)
            numTerms = state.getLength() - state.getNumOverlap();
        else
            numTerms = state.getLength();
        norm.setByte(super.encodeNormValue(1.0f / numTerms));
    }

    @Override
    public String toString() {
        return "CoordSimilarity";
    }


}
