package org.reactome.web.elv.client.common.data.model;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.elv.client.common.data.factory.FactoryUtils;
import org.reactome.web.elv.client.common.data.factory.SchemaClass;
import org.reactome.web.elv.client.common.data.factory.ModelFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class Pathway extends Event {
    private String doi;
    private String isCanonical;
    private List<Event> hasEvent = new LinkedList<Event>();
    private Boolean hasDiagram;

    public Pathway(JSONObject jsonObject) {
        super(SchemaClass.PATHWAY, jsonObject);

        if(jsonObject.containsKey("doi")){
            this.doi = FactoryUtils.getStringValue(jsonObject, "doi");
        }

        if(jsonObject.containsKey("isCanonical")){
            this.isCanonical = FactoryUtils.getStringValue(jsonObject, "isCanonical");
        }

        for (JSONObject object : FactoryUtils.getObjectList(jsonObject, "hasEvent")) {
            this.hasEvent.add((Event) ModelFactory.getDatabaseObject(object));
        }

        if(jsonObject.containsKey("hasDiagram")){
            this.hasDiagram = FactoryUtils.getBooleanValue(jsonObject, "hasDiagram");
        }else{
            this.hasDiagram = false;
        }
    }

    public String getDoi() {
        return doi;
    }

    public String getCanonical() {
        return isCanonical;
    }

    public List<Event> getHasEvent() {
        return hasEvent;
    }

    public Boolean getHasDiagram() {
        return hasDiagram;
    }
}
