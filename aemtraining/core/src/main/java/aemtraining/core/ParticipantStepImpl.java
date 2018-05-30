package aemtraining.core;
 
import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.HistoryItem;
import com.adobe.granite.workflow.exec.ParticipantStepChooser;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.Workflow;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import java.util.List;
 
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
  
@Component(immediate=true, service = ParticipantStepChooser.class, property = {"chooser.label=Sample Workflow Participant Chooser"})
public class ParticipantStepImpl
  implements ParticipantStepChooser
{
  private static final Logger logger = LoggerFactory.getLogger(ParticipantStepImpl.class);
    
  public String getParticipant(WorkItem workItem, WorkflowSession wfSession, MetaDataMap metaDataMap)
    throws WorkflowException
  {
    logger.info("################ Inside the SampleProcessStepChooserImpl GetParticipant ##########################");
    String participant = "admin";
    Workflow wf = workItem.getWorkflow();
    List<HistoryItem> wfHistory = wfSession.getHistory(wf);
    if (!wfHistory.isEmpty()) {
      participant = "administrators";
    } else {
      participant = "admin";
    }
    logger.info("####### Participant : " + participant + " ##############");
    return participant;
  }
}