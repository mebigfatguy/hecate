package com.savoirtech.hecate.cql3.entities;

import com.savoirtech.hecate.cql3.annotations.IdColumn;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class SimplePojo {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    @IdColumn
    private String id = UUID.randomUUID().toString();
    
    private String name;

    private int[] ints;

    private List<String> listOfStrings;

    private Set<String> setOfStrings;

    private Map<Integer, String> mapOfStrings;

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int[] getInts() {
        return ints;
    }

    public void setInts(int[] ints) {
        this.ints = ints;
    }

    public List<String> getListOfStrings() {
        return listOfStrings;
    }

    public void setListOfStrings(List<String> listOfStrings) {
        this.listOfStrings = listOfStrings;
    }

    public Map<Integer, String> getMapOfStrings() {
        return mapOfStrings;
    }

    public void setMapOfStrings(Map<Integer, String> mapOfStrings) {
        this.mapOfStrings = mapOfStrings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getSetOfStrings() {
        return setOfStrings;
    }

    public void setSetOfStrings(Set<String> setOfStrings) {
        this.setOfStrings = setOfStrings;
    }

//----------------------------------------------------------------------------------------------------------------------
// Canonical Methods
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SimplePojo that = (SimplePojo) o;

        if (!id.equals(that.id)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
