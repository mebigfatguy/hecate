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

package com.savoirtech.hecate.cql3.mapping;

import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.savoirtech.hecate.cql3.HecateException;
import com.savoirtech.hecate.cql3.dao.GenericTableDao;
import com.savoirtech.hecate.cql3.dao.abstracts.GenericCqlDao;
import com.savoirtech.hecate.cql3.entities.CollectionTable;
import com.savoirtech.hecate.cql3.entities.SimpleTable;
import com.savoirtech.hecate.cql3.table.TableCreator;
import com.savoirtech.hecate.cql3.test.CassandraTestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.*;

public class FieldMappingIntegrationTest extends CassandraTestCase {
//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    @Test
    public void testCollectiontData() throws InterruptedException, HecateException {
        Metadata metadata = getCluster().getMetadata();
        System.out.printf("Connected to cluster: %s\n", metadata.getClusterName());
        for (Host host : metadata.getAllHosts()) {
            System.out.printf("Datatacenter: %s; Host: %s; Rack: %s\n", host.getDatacenter(), host.getAddress(), host.getRack());
        }

        Session session = getCluster().connect();

        ResultSet resultSet = null;

        System.out.println("Create statement " + TableCreator.createTable("hecate", "collectiontable", CollectionTable.class));

        try {
            resultSet = session.execute(TableCreator.createTable("hecate", "collectiontable", CollectionTable.class));
        }
        catch (HecateException e) {
            e.printStackTrace();
        }

        assertNotNull(resultSet);

        GenericTableDao dao = new GenericCqlDao(session, "hecate", "collectiontable", long.class, CollectionTable.class);
        CollectionTable pj = new CollectionTable();
        pj.setId(100l);
        pj.setName("BOB");
        pj.setMore("BUBBA");
        pj.setDate(new Date());
        pj.getIntegerList().add(1);
        pj.getStringList().add("HARRY!!");
        pj.getMap().put("A", "B");
        pj.getIntegers().add(1);
        pj.getStringSet().add("BOB");
        dao.save(pj);
        CollectionTable fC = (CollectionTable) dao.find(100l);

        assertNotNull(fC);

        assertEquals("BOB", fC.getName());
        assertTrue(fC.getIntegerList().size() == 1);
        assertTrue(fC.getStringList().contains("HARRY!!"));
        assertTrue(fC.getMap().get("A").equals("B"));

        //Start the insert.
    }

    @Test
    public void testInsertData() throws InterruptedException, HecateException {
        Metadata metadata = getCluster().getMetadata();
        System.out.printf("Connected to cluster: %s\n", metadata.getClusterName());
        for (Host host : metadata.getAllHosts()) {
            System.out.printf("Datatacenter: %s; Host: %s; Rack: %s\n", host.getDatacenter(), host.getAddress(), host.getRack());
        }

        Session session = getCluster().connect();

        ResultSet resultSet = null;

        System.out.println("Create statement " + TableCreator.createTable("hecate", "simpletable", SimpleTable.class));

        try {
            resultSet = session.execute(TableCreator.createTable("hecate", "simpletable", SimpleTable.class));
        }
        catch (HecateException e) {
            e.printStackTrace();
        }

        assertNotNull(resultSet);

        GenericTableDao dao = new GenericCqlDao(session, "hecate", "simpletable", long.class, SimpleTable.class);
        SimpleTable pj = new SimpleTable();
        pj.setId(100l);
        pj.setName("BOB");
        pj.setMore("BUBBA");
        pj.setDate(new Date());

        dao.save(pj);
        SimpleTable fC = (SimpleTable) dao.find(100l);

        assertNotNull(fC);

        assertEquals("BOB", fC.getName());

        //Start the insert.
    }

    @Test
    public void testSeriesCollectiontData() throws InterruptedException, HecateException {
        Metadata metadata = getCluster().getMetadata();
        System.out.printf("Connected to cluster: %s\n", metadata.getClusterName());
        for (Host host : metadata.getAllHosts()) {
            System.out.printf("Datatacenter: %s; Host: %s; Rack: %s\n", host.getDatacenter(), host.getAddress(), host.getRack());
        }

        Session session = getCluster().connect();

        ResultSet resultSet = null;

        System.out.println("Create statement " + TableCreator.createTable("hecate", "collectiontable", CollectionTable.class));

        try {
            resultSet = session.execute(TableCreator.createTable("hecate", "collectiontable", CollectionTable.class));
        }
        catch (HecateException e) {
            e.printStackTrace();
        }

        assertNotNull(resultSet);

        GenericTableDao dao = new GenericCqlDao(session, "hecate", "collectiontable", long.class, CollectionTable.class);
        CollectionTable pj = new CollectionTable();
        pj.setId(100l);
        pj.setName("BOB");
        pj.setMore("BUBBA");
        pj.setDate(new Date());
        pj.getIntegerList().add(1);
        pj.getStringList().add("HARRY!!");
        pj.getMap().put("A", "B");
        pj.getIntegers().add(1);
        pj.getStringSet().add("BOB");
        dao.save(pj);
        pj.setId(200l);
        dao.save(pj);
        CollectionTable fC = (CollectionTable) dao.find(100l);

        assertNotNull(fC);

        assertEquals("BOB", fC.getName());
        assertTrue(fC.getIntegerList().size() == 1);
        assertTrue(fC.getStringList().contains("HARRY!!"));
        assertTrue(fC.getMap().get("A").equals("B"));

        Set<CollectionTable> items = dao.findItems(Arrays.asList(new Long[]{100l, 200l}));
        assertNotNull(items);
        assertTrue(items.size() == 2);

        //Start the insert.
    }
}
