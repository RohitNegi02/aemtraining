
package aemtraining.core;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.framework.Constants;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate=true, service = WorkflowProcess.class, property = {"chooser.label=Sample Workflow process Chooser"})
public class MyProcessStep implements WorkflowProcess {
	 private static final Logger logger = LoggerFactory.getLogger(MyProcessStep.class);
	  
    private static final String TYPE_JCR_PATH = "JCR_PATH";
 
    public void execute(WorkItem item, WorkflowSession session, MetaDataMap args) throws WorkflowException {
    	WorkflowData workflowData = item.getWorkflowData();
        if (workflowData.getPayloadType().equals(TYPE_JCR_PATH)) {
            String path = workflowData.getPayload().toString();
  logger.info("##### Inside the SampleProcessStepChooserImpl GetParticipant #########"+path+workflowData.getPayloadType());
                    
            
        }
    }
 
    private boolean readArgument(MetaDataMap args) {
        String argument = args.get("PROCESS_ARGS", "false");
        return argument.equalsIgnoreCase("true");
    }
     
    protected void deactivate(){
        ;
    }
}