package com.netflix.metacat.common.partition.util;

import com.google.common.collect.Maps;
import com.netflix.metacat.common.partition.parser.PartitionParser;
import com.netflix.metacat.common.partition.visitor.PartitionParserEval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

public class FilterPartition {
    private static final Logger log = LoggerFactory.getLogger(FilterPartition.class);

    PartitionParser parser;
    Map<String, String> context = Maps.newLinkedHashMap();

    public boolean evaluatePartitionExpression(String partitionExpression, String name, String path) throws
            IOException {
        return evaluatePartitionExpression(partitionExpression, name, path, false, null);
    }
    public boolean evaluatePartitionExpression(String partitionExpression, String name, String path, boolean batchid, Map<String, String> values)  {
        if (partitionExpression != null) {
            try {
                if (parser == null) {
                    parser = new PartitionParser(new StringReader(partitionExpression));
                } else {
                    parser.ReInit(new StringReader(partitionExpression));
                }
                context.clear();
                if (batchid) {
                    PartitionUtil.getPartitionKeyValues(path, context);
                }
                PartitionUtil.getPartitionKeyValues(name, context);
                if( values != null){
                    context.putAll(values);
                }
                if(context.size() > 0) {
                    return (Boolean) parser.filter().jjtAccept(new PartitionParserEval(context), null);
                } else {
                    return false;
                }
            } catch(Exception e) {
                log.warn("Caught unexpected exception during evaluatePartitionExpression," + e);
                return false;
            }
        }
        return true;
    }
}
