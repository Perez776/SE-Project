import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Vector;

import org.json.*;

public class APIInfo {
    //Vector<Object> itemList = new Vector<Object>();
    String urlString;
    String response = "";

    public APIInfo(String apiURL) {
        this.urlString = apiURL;
        
        try {
            URL url = new URL(urlString);//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization","Bearer "+"QSYyBGiMR0yaQibhrndvDA");
            //e.g. bearer token= eyJhbGciOiXXXzUxMiJ9.eyJzdWIiOiPyc2hhcm1hQHBsdW1zbGljZS5j
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);

            //String response = "";
            String line;

            while ((line = br.readLine()) != null) {
                this.response += line;
            }
            //System.out.println(response + "\n\n" );
            conn.disconnect();
        }
        catch (Exception e) {
           System.out.println("Exception in NetClientGet:- " + e);
        }
    }

    public String getAPIInfo() {
        return this.response;
    }


    public Object [] getESPNAPI(String arrName, String itemName) {
        //Vector<Object> itemVector = new Vector<Object>();
        ArrayList<Object> apiItems = new ArrayList<>();

        JSONObject obj = new JSONObject(this.response);
        System.out.println(obj.getString("header") + "\n\n" );
        JSONArray arr = obj.getJSONArray(arrName);
        //System.out.println(this.response + "\n\n" );
       for(int i = 0; i < arr.length(); i++)
       {
           JSONObject inarrObj = arr.getJSONObject(i);
           //JSONArray a = obj.getJSONArray("most_title_names");
           Object a = inarrObj.get(itemName);
           //JSONObject c = a.getJSONObject(0);
          // System.out.println(a);
           apiItems.add(a);
       }
   
        Object items [] = apiItems.toArray();
        return items;
    }


    //public Vector<Object> getAPIItem(String itemName) {
    public Object [] getAPIItem(String itemName) {
        //Vector<Object> itemVector = new Vector<Object>();
        ArrayList<Object> apiItems = new ArrayList<>();
        JSONArray arr = new JSONArray(this.response);
        //System.out.println(this.response + "\n\n" );
       // System.out.println(arr.length());

        for(int i = 0; i < arr.length(); i++)
        {
            JSONObject obj = arr.getJSONObject(i);
            //JSONArray a = obj.getJSONArray("most_title_names");
            Object a = obj.get(itemName);
            //JSONObject c = a.getJSONObject(0);
            //System.out.println(a);
            apiItems.add(a);
        }
        Object items [] = apiItems.toArray();
        return items;
    }


    //public Vector<Object> getAPIListItem(String itemList, String itemName) {
    public Object [] getAPIListItem(String itemList, String itemName) {
        //Vector<Object> itemVector = new Vector<Object>();
        ArrayList<Object> apiItems = new ArrayList<>();

        JSONArray arr = new JSONArray(response);

        for(int i = 0; i < arr.length(); i++)
        {
            JSONObject obj = arr.getJSONObject(i);
            JSONArray inArray = obj.getJSONArray(itemList);
            
            for(int j = 0; j < inArray.length(); j++)
            {
                JSONObject inArrayObj = inArray.getJSONObject(j);

                Object a = inArrayObj.get(itemName);   
                //itemVector.add(a);
                apiItems.add(a);
                //System.out.println(a);
            }
        }
        //return itemVector;
        Object items [] = apiItems.toArray();
        //System.out.println(items[20]);
        return items;
    }
}
