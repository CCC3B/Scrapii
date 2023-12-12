package com.personal;

import java.util.HashMap;
import java.util.Map;

final class UserAgent {
    
    String platform;
    String appID;
    String userName; 
    String version;
    
    private Map<String, String> authMap  = new  HashMap<String, String>();
   public UserAgent(String platform,String appID, String username, String version )
   {
       this.platform = platform;
       this.appID = appID;
       this.userName = username;
       this.version = version;
   }
   public Map<String,String> authenticator(String Token)
    {

        final Map<String,String> map = new HashMap<>();
        map.put("rdt_pass",System.getenv("rdit_pass"));
        map.put("rdt_user",System.getenv("rdit_user"));
        map.put("rdt_secret",System.getenv("rdit_secret"));
        map.put("rdt_ID",System.getenv("rdit_ID"));
        map.put("token",Token);

      return map;
    } 
   public String getPlatform() {
       return platform;
   }
   public String getAppID() {
       return appID;
   }
   public String getUserName() {
       return userName;
   }
   public String getVersion() {
       return version;
   }
   public void setPlatform(String platform) {
       this.platform = platform;
   }
   public void setAppID(String appID) {
       this.appID = appID;
   }
   public void setUserName(String userName) {
       this.userName = userName;
   }
   public void setVersion(String version) {
       this.version = version;
   }
   public String showUserAgent()
   {
       String usrag = platform+" : "+appID+" : "+version+" "+"(by /u/"+userName+")";
      return usrag;
      
   }
    
}
