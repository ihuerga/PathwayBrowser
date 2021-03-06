package org.reactome.web.elv.client.common.data.model;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.elv.client.common.data.factory.SchemaClass;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class PsiMod extends ExternalOntology {

    public PsiMod(JSONObject jsonObject) {
        super(SchemaClass.PSI_MOD, jsonObject);
    }

}
