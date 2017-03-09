
/*
 * Carrot2 project.
 *
 * Copyright (C) 2002-2017, Dawid Weiss, Stanisław Osiński.
 * All rights reserved.
 *
 * Refer to the full license file "carrot2.LICENSE"
 * in the root folder of the repository checkout or at:
 * http://www.carrot2.org/carrot2.LICENSE
 */

package org.carrot2.examples.clustering;

import java.util.HashMap;
import java.util.Map;

import org.carrot2.clustering.lingo.LingoClusteringAlgorithm;
import org.carrot2.core.Controller;
import org.carrot2.core.ControllerFactory;
import org.carrot2.core.IDocumentSource;
import org.carrot2.core.ProcessingResult;
import org.carrot2.core.attribute.CommonAttributesDescriptor;
import org.carrot2.source.microsoft.v5.Bing5DocumentSource;
import org.carrot2.source.microsoft.v5.Bing5DocumentSourceDescriptor;

/**
 * This example shows how to set up and use a {@link Controller} that reuses instances of
 * processing component and caches processing results. This example assumes you are
 * familiar with {@link ClusteringDataFromDocumentSources} and
 * {@link ClusteringDocumentList} examples.
 */
public class UsingCachingController
{
    @SuppressWarnings(
    {
        "unused",
    })
    public static void main(String [] args)
    {
        // [[[start:using-caching-controller]]]
        /*
         * Create the caching controller. You need only one caching controller instance
         * per application life cycle. This controller instance will cache the results
         * fetched from any document source and also clusters generated by the Lingo
         * algorithm.
         */
        final Controller controller = ControllerFactory.createCachingPooling(
            IDocumentSource.class, LingoClusteringAlgorithm.class);

        /*
         * Before using the caching controller, you must initialize it. On initialization,
         * you can set default values for some attributes. In this example, we'll set the
         * default results number to 50 and the API key.
         */
        final Map<String, Object> globalAttributes = new HashMap<String, Object>();
        CommonAttributesDescriptor
            .attributeBuilder(globalAttributes)
                .results(50);
        Bing5DocumentSourceDescriptor
            .attributeBuilder(globalAttributes)
                .apiKey(BingKeyAccess.getKey()); // use your own ID here
        controller.init(globalAttributes);

        /*
         * The controller is now ready to perform queries. To show that the documents from
         * the document input are cached, we will perform the same query twice and measure
         * the time for each query.
         */
        ProcessingResult result;
        long start, duration;

        final Map<String, Object> attributes;
        attributes = new HashMap<String, Object>();
        CommonAttributesDescriptor.attributeBuilder(attributes).query("data mining");

        start = System.currentTimeMillis();
        result = controller.process(attributes, Bing5DocumentSource.class,
            LingoClusteringAlgorithm.class);
        duration = System.currentTimeMillis() - start;
        System.out.println(duration + " ms (empty cache)");

        start = System.currentTimeMillis();
        result = controller.process(attributes, Bing5DocumentSource.class,
            LingoClusteringAlgorithm.class);
        duration = System.currentTimeMillis() - start;
        System.out.println(duration + " ms (documents and clusters from cache)");
        // [[[end:using-caching-controller]]]
    }
}
