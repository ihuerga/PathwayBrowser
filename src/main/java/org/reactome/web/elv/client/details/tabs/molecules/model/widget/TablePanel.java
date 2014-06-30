package org.reactome.web.elv.client.details.tabs.molecules.model.widget;

import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import org.reactome.web.elv.client.common.widgets.disclosure.DisclosurePanelFactory;
import org.reactome.web.elv.client.details.tabs.molecules.model.data.Result;
import org.reactome.web.elv.client.details.tabs.molecules.model.type.PropertyType;

/**
 * @author Kerstin Hausmann <khaus@ebi.ac.uk>
 */
public class TablePanel extends Composite implements OpenHandler<DisclosurePanel> {
    private DisclosurePanel disclosurePanel;
    private PropertyType propertyType;
    private Result result;
    private int size;
    private MoleculesTable moleculesTable;
    private String displayText;

    public TablePanel(PropertyType category, int size, Result result) {
        this.propertyType = category;
        this.size = size;
        this.result = result;

        int toHighlight = result.getNumHighlight(propertyType);
        if(toHighlight == this.size){
            displayText = propertyType.getTitle() + " (" + this.size + ")";
        }else{
            displayText = propertyType.getTitle() + " (" + result.getNumHighlight(propertyType) + "/" + this.size + ")";
        }

        this.disclosurePanel = DisclosurePanelFactory.getAdvancedDisclosurePanel(displayText, null);

        moleculesTable = new MoleculesTable(result);
        setMoleculesData();

        this.disclosurePanel.addOpenHandler(this);
        this.initWidget(this.disclosurePanel);
    }

    @Override
    public void onOpen(OpenEvent<DisclosurePanel> event) {
        updateMoleculesData();
    }

    public void update(int size, Result result){
        this.result = result;
        this.size = size;
        if(this.disclosurePanel.isOpen()){
            updateMoleculesData();
        }

        int toHighlight = result.getNumHighlight(propertyType);
        if(toHighlight == this.size){
            displayText = propertyType.getTitle() + " (" + this.size + ")";
        }else{
            displayText = propertyType.getTitle() + " (" + result.getNumHighlight(propertyType) + "/" + this.size + ")";
        }
        this.disclosurePanel.getHeaderTextAccessor().setText(displayText);
    }

    private void setMoleculesData(){
        switch (propertyType){
            case CHEMICAL_COMPOUNDS:
                moleculesTable.setMoleculesData(result.getSortedChemicals());
                break;
            case PROTEINS:
                moleculesTable.setMoleculesData(result.getSortedProteins());
                break;
            case SEQUENCES:
                moleculesTable.setMoleculesData(result.getSortedSequences());
                break;
            default:
                moleculesTable.setMoleculesData(result.getSortedOthers());
                break;
        }
        this.disclosurePanel.setContent(moleculesTable.asWidget());
    }

    private void updateMoleculesData(){
        switch (propertyType){
            case CHEMICAL_COMPOUNDS:
                moleculesTable.updateMoleculesData(result.getSortedChemicals());
                break;
            case PROTEINS:
                moleculesTable.updateMoleculesData(result.getSortedProteins());
                break;
            case SEQUENCES:
                moleculesTable.updateMoleculesData(result.getSortedSequences());
                break;
            default:
                moleculesTable.updateMoleculesData(result.getSortedOthers());
                break;
        }
        this.disclosurePanel.setContent(moleculesTable.asWidget());
    }
}
