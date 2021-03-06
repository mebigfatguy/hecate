/*
 * Copyright 2014 Savoir Technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.savoirtech.hecate.core.abstractdao;

import com.savoirtech.hecate.core.config.CassandraKeyspaceConfigurator;
import com.savoirtech.hecate.core.indexing.CompositeColumnIdentifier;
import com.savoirtech.hecate.core.utils.ColumnIterator;
import com.savoirtech.hecate.core.utils.ColumnRecordIterator;
import me.prettyprint.cassandra.serializers.BytesArraySerializer;
import me.prettyprint.cassandra.serializers.SerializerTypeInferer;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.beans.AbstractComposite;
import me.prettyprint.hector.api.beans.Composite;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.query.SliceQuery;

public abstract class AbstractIteratingCompositedDao extends AbstractIteratingColumnFamilyDao<String, CompositeColumnIdentifier, String> {

    /**
     * Instantiates a new abstract column family dao.
     */
    public AbstractIteratingCompositedDao(String clusterName, CassandraKeyspaceConfigurator keyspaceConfigurator, Class<String> stringClass,
                                          Class<CompositeColumnIdentifier> nameClass, Class<String> valueClass, String columnFamilyName,
                                          String comparatorAlias) {
        super(clusterName, keyspaceConfigurator, stringClass, nameClass, valueClass, columnFamilyName, comparatorAlias);
    }

    @Override
    protected Composite marshalComposite(CompositeColumnIdentifier compositeKeys) {
        //Build a list of composite based on the order of the list of the recordIdentifier.
        int position = 0;
        Composite comp = new Composite();
        for (String key : compositeKeys.getMap().keySet()) {
            comp.addComponent(position, compositeKeys.getMap().get(key), AbstractComposite.ComponentEquality.EQUAL);
            position++;
        }
        return comp;
    }

    @Override
    public ColumnIterator<String, CompositeColumnIdentifier, String> find(String key, CompositeColumnIdentifier start, CompositeColumnIdentifier end,
                                                                          boolean reverse) {

        SliceQuery<String, Object, byte[]> query;

        if (fields != null) {

            //HFactory.createSliceQuery(keySpace, (Serializer<KeyType>) SerializerTypeInferer.getSerializer(keyTypeClass),
            //                SerializerTypeInferer.getSerializer(Composite.class), BytesArraySerializer.get());

            query = HFactory.createSliceQuery(keySpace, StringSerializer.get(), SerializerTypeInferer.getSerializer(Composite.class),
                BytesArraySerializer.get());
            query.setColumnFamily(columnFamilyName);
            query.setKey(key);

            Composite compStart = (start != null) ? marshalComposite(start) : null;
            Composite compEnd = (end != null) ? marshalComposite(end) : null;

            //return new ColumnRecordIterator(query, String.class, CompositeColumnIdentifier.class, compStart, compEnd, reverse);
            return new ColumnRecordIterator(query, CompositeColumnIdentifier.class, String.class, compStart, compEnd, reverse);
        } else {
            query = HFactory.createSliceQuery(keySpace, StringSerializer.get(), SerializerTypeInferer.getSerializer(String.class),
                BytesArraySerializer.get());
            query.setColumnFamily(columnFamilyName);
            query.setKey(key);
            //return new ColumnRecordIterator(query, String.class, CompositeColumnIdentifier.class, start, end, reverse);
            return new ColumnRecordIterator(query, CompositeColumnIdentifier.class, String.class, start, end, reverse);
        }
    }
}
