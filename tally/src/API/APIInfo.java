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
            String line;

            while ((line = br.readLine()) != null) {
                this.response += line;
            }
            conn.disconnect();
        }
        catch (Exception e) {
           System.out.println("Exception in NetClientGet:- " + e);
        }
    }

    public String getNewsImg() {
        ArrayList<String> apiItems = new ArrayList<>();
        String link = "";

        JSONObject obj = new JSONObject(this.response);
        JSONArray leaguesArr = obj.getJSONArray("leagues");
        JSONObject leaguesObj = leaguesArr.getJSONObject(0);
        JSONArray logosArr = leaguesObj.getJSONArray("logos");
        JSONObject logosObj = logosArr.getJSONObject(0);
        link = logosObj.getString("href");

        return link;
    }


    public String getLeagueLogo() {
        ArrayList<String> apiItems = new ArrayList<>();
        String link = "";

        JSONObject obj = new JSONObject(this.response);
        JSONArray leaguesArr = obj.getJSONArray("leagues");
        JSONObject leaguesObj = leaguesArr.getJSONObject(0);
        JSONArray logosArr = leaguesObj.getJSONArray("logos");
        JSONObject logosObj = logosArr.getJSONObject(0);
        link = logosObj.getString("href");

        return link;
    }




    public ArrayList<ArrayList<String>> getESPNStandings() { 
        ArrayList<ArrayList<String>> apiItems = new ArrayList<>();

        JSONObject obj = new JSONObject(this.response);

        JSONArray childrenArr = obj.getJSONArray("children");

        int teamNum = 0;

        for(int i=0; i < childrenArr.length(); i++) {
            JSONObject childrenObj = childrenArr.getJSONObject(i);

            JSONObject standingsObj = childrenObj.getJSONObject("standings");

            JSONArray entriesArr = standingsObj.getJSONArray("entries");

            for(int j = 0; j < entriesArr.length(); j++) {

                apiItems.add(new ArrayList<String>());

                JSONObject entriesObj = entriesArr.getJSONObject(j);
                JSONObject teamObj = entriesObj.getJSONObject("team");

                String id = teamObj.getString("id");
                String teamName = teamObj.getString("name");
                apiItems.get(teamNum).add(id);
                apiItems.get(teamNum).add(teamName);

                JSONArray logoArr = teamObj.getJSONArray("logos");

                JSONObject logosObj = logoArr.getJSONObject(0);

                String logoImg = logosObj.getString("href");
                apiItems.get(teamNum).add(logoImg);
                
                JSONArray statsArr = entriesObj.getJSONArray("stats");

                for(int k = 0; k < statsArr.length(); k++) {
                    JSONObject statsObj = statsArr.getJSONObject(k);

                    String statName = statsObj.getString("name");
                    apiItems.get(teamNum).add(statName);
                    String statValue = statsObj.getString("displayValue");
                    apiItems.get(teamNum).add(statValue);
                }
                //System.out.println(apiItems.get(teamNum));
                teamNum++;
            }
        }

        return apiItems;
    }



    public String getAPIInfo() {
        return this.response;
    }


    public Object [][] getESPNNews(String arrName, String itemName) {
        //Vector<Object> itemVector = new Vector<Object>();
        ArrayList<ArrayList<Object>> apiItems = new ArrayList<>();
        //ArrayList<Object> apiItems = new ArrayList<>();

        JSONObject obj = new JSONObject(this.response);
       // System.out.println(obj.getString("header") + "\n\n" );
        JSONArray arr = obj.getJSONArray("articles");
        //System.out.println(this.response + "\n\n" );
       for(int i = 0; i < arr.length(); i++)
       {
            apiItems.add(new ArrayList<Object>());

            JSONObject inarrObj = arr.getJSONObject(i);
           
            Object a = inarrObj.get("description");

            JSONArray imgsArr = inarrObj.getJSONArray("images");

            JSONObject urlObject = imgsArr.getJSONObject(0);
        
            //JSONObject linksObj = inarrObj.getJSONObject("links");

            //JSONObject apiObj = linksObj.getJSONObject("api");

           // JSONObject newsObj = apiObj.getJSONObject("self");

            Object b = urlObject.get("url");

            apiItems.get(i).add(a);
            apiItems.get(i).add(b);
            //apiItems.add(a);
       }
       
        Object items [][] = new Object[arr.length()][2];
        for(int i = 0; i < items.length; i++) {
            items[i][0] = apiItems.get(i).get(0);
            items[i][1] = apiItems.get(i).get(1);
        }
        //Object items [] = apiItems.toArray();
        return items;
    }


    public ArrayList<ArrayList<String>> getESPNMatchesAPI(String itemName) {

        //Create 2D Array
        ArrayList<ArrayList<String>> apiItems = new ArrayList<ArrayList<String>>();
        String teamName;
        String desc;
        String date;
        String stat;
   
        JSONObject obj = new JSONObject(this.response);
        JSONArray arr = obj.getJSONArray("events");

        int teamNum = 0;
        //Search in events jsonarray
        for(int i = 0; i < arr.length(); i++)
        {
            JSONObject obj2 = arr.getJSONObject(i);
            date = obj2.getString("date");

            //Search in competitions jsonarray
            JSONArray arr2 = obj2.getJSONArray("competitions");
            for(int j = 0; j < arr2.length(); j++) 
            {
                JSONObject obj3 = arr2.getJSONObject(j);
                JSONObject obj31 = obj3.getJSONObject("status");
                JSONObject obj311 = obj31.getJSONObject("type");
                
                desc = obj311.getString("description");
          
                //Search in competitors jsonarray
                JSONArray arr3 = obj3.getJSONArray("competitors");

                for(int k = 0; k < arr3.length(); k++) 
                {
                    apiItems.add(new ArrayList<String>());
                    apiItems.get(teamNum).add(date);
                    apiItems.get(teamNum).add(desc);

                    obj3 = arr3.getJSONObject(k);

                    JSONObject obj4 = obj3.getJSONObject("team");
                    teamName = obj4.getString("displayName");
                    apiItems.get(teamNum).add(teamName);

                    Object f = obj3.get("score");
                    apiItems.get(teamNum).add(f.toString());

                    obj3 = arr3.getJSONObject(k);
                    JSONArray arr4 = obj3.getJSONArray("statistics");
                    //Search in statistics jsonarray
                    for(int l = 0; l < arr4.length(); l++) 
                    {
                        obj31 = arr4.getJSONObject(l);
                        stat = obj31.getString("name");
                        apiItems.get(teamNum).add(stat);
                        stat = obj31.getString("displayValue");
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
