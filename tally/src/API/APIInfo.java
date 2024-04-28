package API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

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

    public ArrayList<ArrayList<Object>> getESPNMatchesAPI(String itemName) {

        //Create 2D Array
        ArrayList<ArrayList<Object>> apiItems = new ArrayList<ArrayList<Object>>();
        Object teamName;
        Object desc;
        Object date;
        Object stat;
   
        JSONObject obj = new JSONObject(this.response);
        JSONArray arr = obj.getJSONArray("events");

        int teamNum = 0;
        //Search in events jsonarray
        for(int i = 0; i < arr.length(); i++)
        {
            JSONObject obj2 = arr.getJSONObject(i);
            date = obj2.get("date");

            //Search in competitions jsonarray
            JSONArray arr2 = obj2.getJSONArray("competitions");
            for(int j = 0; j < arr2.length(); j++) 
            {
                JSONObject obj3 = arr2.getJSONObject(j);
                JSONObject obj31 = obj3.getJSONObject("status");
                JSONObject obj311 = obj31.getJSONObject("type");
                
                desc = obj311.get("description");
          
                //Search in competitors jsonarray
                JSONArray arr3 = obj3.getJSONArray("competitors");
                for(int k = 0; k < arr3.length(); k++) 
                {
                    apiItems.add(new ArrayList<Object>());
                    apiItems.get(teamNum).add(date);
                    apiItems.get(teamNum).add(desc);

                    obj3 = arr3.getJSONObject(k);
                    JSONObject obj4 = obj3.getJSONObject("team");
                    teamName = obj4.get("displayName");

                    apiItems.get(teamNum).add(teamName);

                    obj3 = arr3.getJSONObject(k);
                    JSONArray arr4 = obj3.getJSONArray("statistics");
                    //Search in statistics jsonarray
                    for(int l = 0; l < arr4.length(); l++) 
                    {
                        obj31 = arr4.getJSONObject(l);
                        stat = obj31.get("abbreviation");
                        apiItems.get(teamNum).add(stat);
                        stat = obj31.get("displayValue");
                        apiItems.get(teamNum).add(stat);
                    }
                    teamNum++;
                }
            }
        }
   
        return apiItems;
    }

    public Object [] getAPIItem(String itemName) {
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

        //for(int i = 0; i < arr.length(); i++)
        //{
        JSONObject obj = arr.getJSONObject(0);
        JSONArray inArray = obj.getJSONArray(itemList);
        
        /* 
        for(int j = 0; j < inArray.length(); j++)
        {
            JSONObject inArrayObj = inArray.getJSONObject(j);

            Object a = inArrayObj.get(itemName);   
            //itemVector.add(a);
            apiItems.add(a);
            //System.out.println(a);
            System.out.println(a);
        }
*/
        for(int j = inArray.length()-1; j >= 0; j--)
        {
            JSONObject inArrayObj = inArray.getJSONObject(j);

            Object a = inArrayObj.get(itemName);   
            apiItems.add(a);
        }

        //}
        //return itemVector;
        Object items [] = apiItems.toArray();
        //System.out.println(items[20]);
        return items;
    }
}
