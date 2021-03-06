package org.reactome.web.elv.client.details.tabs.overview.model.widgets;

import com.google.gwt.user.client.ui.Widget;
import org.reactome.web.elv.client.common.data.model.ReactionLikeEvent;
import org.reactome.web.elv.client.details.tabs.overview.model.widgets.factory.PropertyType;
import org.reactome.web.elv.client.details.tabs.overview.model.widgets.factory.TableRowFactory;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class ReactionLikeEventTable extends EventTable {
    private ReactionLikeEvent reactionLikeEvent;

    public ReactionLikeEventTable(ReactionLikeEvent reactionLikeEvent) {
        super(reactionLikeEvent);
        this.reactionLikeEvent = reactionLikeEvent;
    }

    @Override
    protected Widget getTableRow(PropertyType propertyType) {
        String title = propertyType.getTitle();
        switch (propertyType){
            case INPUT:
                return TableRowFactory.getPhysicalEntityRow(title, this.reactionLikeEvent.getInputs());
            case CATALYST:
                return TableRowFactory.getCatalystActivityRow(title, this.reactionLikeEvent.getCatalystActivities());
            case OUTPUT:
                return TableRowFactory.getPhysicalEntityRow(title, this.reactionLikeEvent.getOutputs());
            case HAS_MEMBER:
                return TableRowFactory.getEventRow(title, this.reactionLikeEvent.getHasMember());
            case ENTITY_FUNCTIONAL_STATUS:
                return TableRowFactory.getEntityFunctionalStatusRow(title, this.reactionLikeEvent.getEntityFunctionalStatus());
            case ENTITY_ON_OTHER_CELL:
                return TableRowFactory.getPhysicalEntityRow(title, this.reactionLikeEvent.getEntityOnOtherCell());
//            case REQUIRED_INPUT:
//                return TableRowFactory.getPhysicalEntityRow(title, this.reactionLikeEvent.getRequiredInputComponent());
            /*case GO_MOLECULAR_FUNCTION:
            //GO MOLECULAR FUNCTION belongs to CATALYST :)
            break;*/
            case NORMAL_REACTION:
                return TableRowFactory.getNormalReactionLikeEventRow(title, this.reactionLikeEvent.getNormalReaction());
            default:
                return super.getTableRow(propertyType);
        }
    }
}
