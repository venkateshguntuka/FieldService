package com.example.venkatesh.fieldservice;

/**
 * Created by venkatesh on 03-07-2016.
 */
import java.util.HashMap;
import java.util.Map;
public class ChatMessage {
    String empId;
    String email;
    String packageId;;
    String packageName;
    String status;
    public ChatMessage()
    {

    }
    public ChatMessage(String empId,String email,String packageId,String packageName,String status)
    {
        this.empId=empId;
        this.email=email;
        this.packageId=packageId;
        this.packageName=packageName;
        this.status=status;
    }
    public String getEmpId()
    {
        return empId;
    }
    public String getEmail()
    {
        return email;
    }
    public String getPackageId()
    {
        return packageId;
    }
    public String getPackageName()
    {
        return packageName;
    }
    public String getStatus()
    {
        return status;
    }
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("empId",empId);
        result.put("email", email);
        result.put("packageId",packageId);
        result.put("packageName",packageName);
        result.put("status",status);
        return result;
    }


}
