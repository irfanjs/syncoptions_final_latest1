package org.hudsonci.plugins;

import net.sf.json.JSONObject;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import hudson.Extension;
import hudson.ExtensionPoint;
import hudson.model.Describable;
import hudson.model.ParameterValue;
import hudson.model.Descriptor;
import hudson.model.Hudson;
import hudson.model.ParameterDefinition;

public class ChoiceSyncOptions extends ParameterDefinition {

    public static final String NORMAL_SYNC = "Normal Sync";
    public static final String DISABLE_SYNC = "Disable Sync";
    public static final String FORCE_SYNC = "Force Sync";
    public static final String CHANGE_SYNC = "Sync based on Change-list";
    public static final String LBL_SYNC = "Sync based on Label";
    
    public String label;
    
    @DataBoundConstructor
    public ChoiceSyncOptions(String name) {
        super("sync-parameter");
    }

    @Override
    public ParameterValue createValue(StaplerRequest req, JSONObject json) {
    	System.err.println("createvalue: " + label);
        ChoiceSyncParameterValue value = req.bindJSON(ChoiceSyncParameterValue.class, json);
        System.err.println("Createdvalue: " + json);
        System.err.println("Parameters: " + req.getParameterMap());
        value.setSyncOption(req.getParameter("syncOption"));
        return value;
    }
    
    public String[] getSyncOptions(){
        return new String[]{NORMAL_SYNC, DISABLE_SYNC, FORCE_SYNC};
    }
    
  //  public String gettxtbx(){
   //     return new String("label");
   // }
    
    @Override
    public ParameterValue createValue(StaplerRequest sr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Extension
    public static class DescriptorImpl extends ParameterDescriptor {

        @Override
        public String getDisplayName() {
            return "Enable Perforce sync options at runtime";
        }
    }
}
