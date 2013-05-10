package org.hudsonci.plugins;

import hudson.EnvVars;
import hudson.model.AbstractBuild;
import hudson.model.FreeStyleProject;
import hudson.model.Hudson;
import hudson.model.ParameterValue;
import org.kohsuke.stapler.DataBoundConstructor;
import hudson.plugins.perforce.*;
import hudson.plugins.perforce.PerforceSCM.*;
import hudson.scm.SCM;
import hudson.plugins.perforce.PerforceRepositoryBrowser;


public class ChoiceSyncParameterValue extends ParameterValue {

    private String syncOption;

    @DataBoundConstructor
    public ChoiceSyncParameterValue(String name, String syncOption) {
        super(name);
        this.syncOption = syncOption;
    }

    public String getSyncOption() {
        return syncOption;
    }

    public void setSyncOption(String syncOption) {
        this.syncOption = syncOption;
    }

    @Override
    public void buildEnvVars(AbstractBuild<?, ?> build, EnvVars env) {
    	// Get the root project
    	FreeStyleProject project = (FreeStyleProject) build.getProject();
    	
    	// Get the SCM from the root project.
    	SCM scm = project.getScm();
    	
    	// Verify that the SCM attached to the project is actually a PerforceSCM. 
    	if (scm instanceof PerforceSCM) {
    		
    		// Use perforceSCM variable to alter the current SCM.
    		PerforceSCM perforceSCM = (PerforceSCM) scm;
	    	
	    	if (ChoiceSyncOptions.NORMAL_SYNC.equals(syncOption)) {
	    		perforceSCM.setForceSync(false);
	    		perforceSCM.setDisableSyncOnly(false);
	
	        }
	        if (ChoiceSyncOptions.DISABLE_SYNC.equals(syncOption)) {
	        	perforceSCM.setForceSync(false);
	        	perforceSCM.setDisableSyncOnly(true);
	
	        }
	        if (ChoiceSyncOptions.FORCE_SYNC.equals(syncOption)) {
	        		perforceSCM.setForceSync(true);
	        		perforceSCM.setDisableSyncOnly(false);
	        }
	      //  if (ChoiceSyncOptions.LBL_SYNC.equals(syncOption)) {
        	//       perforceSCM.setP4Label("Carrera-2013-05-04_11-43-42-697");
	        	//	perforceSCM.setForceSync(true);
        	//	perforceSCM.setDisableSyncOnly(false);
        //}
    	}	
    	
    }
}
