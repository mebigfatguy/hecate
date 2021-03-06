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

package com.savoirtech.hecate.core.dao;

import com.savoirtech.hecate.core.abstractdao.AbstractPojoObjectGraphDao;
import com.savoirtech.hecate.core.config.CassandraKeyspaceConfigurator;
import com.savoirtech.hecate.core.dao.ColumnFamilyDao;
import com.savoirtech.hecate.core.utils.DaoPool;

public class PojoObjectGraphDao<K, T> extends AbstractPojoObjectGraphDao<K, T> implements ColumnFamilyDao<K, T> {
    /**
     * Instantiates a new abstract column family dao.
     */
    public PojoObjectGraphDao(String clusterName, CassandraKeyspaceConfigurator keyspaceConfigurator, Class<K> keyClass, Class<T> typeClass,
                              String columnFamilyName, String comparatorAlias, DaoPool daoPool) {
        super(clusterName, keyspaceConfigurator, keyClass, typeClass, columnFamilyName, comparatorAlias, daoPool);
    }
}
